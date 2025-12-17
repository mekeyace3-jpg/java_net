package net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//TCP 통신이며 양방향 통신 Server & Thread
public class multi_server {
	public static void main(String[] args) {
		multi_server msr = new multi_server();
		msr.start();
	}
	
	public void start() {	//서버의 소켓을 유지하기 위한 메소드
			ServerSocket serverSocket = null;
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(20002);	//port 오픈
				System.out.println("[채팅 시작]");				
				while(true) {
						socket = serverSocket.accept();	//소켓 유지
						ms m = new ms(socket);	//Thread에 해당 소켓 전달
						m.start();	//Thread 가동
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
}

class ms extends Thread {
	
	static List<PrintWriter> list = new ArrayList<PrintWriter>();
	
	InputStream is = null;
	OutputStream os = null;
	Socket socket = null;
	
	BufferedReader in = null;
	PrintWriter out = null;
	
	public ms(Socket socket) {
		this.socket = socket;
		try {
			//client로 송신
			this.out = new PrintWriter(this.socket.getOutputStream());
			//client로 수신
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			list.add(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {	//client에서 메세지 정보 출력
			String name = "";	//client가 로그인한 사용자 이름
			try {
				
				name = this.in.readLine();
				System.out.println("["+name+"]님 환영합니다.");
				sendall("["+name+"]님 환영합니다.");
				
				while(this.in != null) {
					String msg = in.readLine();
					System.out.println(name +"님 : "+ msg);
					sendall(name +"님 : "+ msg);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("해당 사용자가 로그인 에러 발생!!");
			}
	}
	
	//모든 서버에 접속된 사용자에게 메세지 발송
	private void sendall(String s) {
		for(PrintWriter out : list) {
			out.println(s);
			out.flush();
		}
	}
	
	
}