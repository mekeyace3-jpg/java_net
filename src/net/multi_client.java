package net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

//TCP 통신이며 양방향 통신 Client & Thread
public class multi_client {
	public static void main(String[] args) {
		new multi_client().start();
	}
	public void start() {
		String Serverip = "172.30.1.54";
		int Serverport = 20002;
		Socket socket = null;
		
		InputStream is = null;
		InputStreamReader ir = null;
		BufferedReader in = null;
		Scanner sc = null;
		try {
			socket = new Socket(Serverip,Serverport);
			System.out.println("[채팅 서버에 입장 합니다.]");
			sc = new Scanner(System.in);
			System.out.println("대화명을 입력하세요 : ");
			String name = sc.nextLine();
			Thread th = new mc(socket,name);
			th.start();
			
			//서버에서 오는 메세지를 수신 역활
			is = socket.getInputStream();
			ir = new InputStreamReader(is);
			in = new BufferedReader(ir);
			
			while(in != null) {
				String msg = in.readLine();
				System.out.println(msg);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				socket.close();
				sc.close();
			} catch (Exception e2) {
				System.out.println("정상적인 종료가 아닙니다.");
			}
		}
	}
}

class mc extends Thread {
	Socket socket = null;
	String name = "";
	Scanner sc2 = null;
	
	public mc(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			//대화명을 전송하는 메세지 출력 (한번만 작동)
			PrintStream out = new PrintStream(this.socket.getOutputStream());
			out.println(this.name);
			out.flush();
			
			//서버 대화 메세지를 전송하는 역활
			this.sc2 = new Scanner(System.in);
			while(true) {
				System.out.println("메세지를 입력하세요 : ");
				String outmsg = this.sc2.nextLine();
				out.println(outmsg);
				out.flush();
				if("종료".equals(outmsg)) {
					this.sc2.close();
					this.socket.close();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
