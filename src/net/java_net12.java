package net;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//TCP - 통신 Multi Server + Thread
public class java_net12 {
	public static void main(String[] args) {
		int op[] = {11000,11001,11002};	//서버 포트 리스트 배열
		int w = 0;
		while(w < op.length) {
			java_net12_server js = new java_net12_server("172.30.1.50",op[w]);
			js.start();		//Thread run()메소드를 가동
			w++;
		}
	}
}
//부모 Thread 클래스를 상속 받아서 처리하는 상황
class java_net12_server extends Thread {
	String ip = null;
	int port = 0;
	Socket sk = null;
	ServerSocket ss = null;
	
	public java_net12_server(String serverip, int serverport) {
		this.ip = serverip;
		this.port = serverport;
	}
	//Thread별로 각 port를 오픈하여 접속을 할 수 있도록 적용을 한 상황임
	@Override
	public void run() {
		try {
			this.ss = new ServerSocket(this.port);
			while(true) {
					this.sk = this.ss.accept();
					System.out.println(this.port + "서버오픈!!");
					
					//클라이언트 접속 정보를 확인하기 위한 사항
					InetAddress ia = this.sk.getInetAddress();
					String client_ip = ia.getHostAddress();
					int client_port = this.sk.getPort();
					System.out.println("클라이언트 정보 : " + client_ip);
					System.out.println("클라이언트 포트 : " + client_port);
			}
			 
		} catch (Exception e) {
			System.out.println("중복된 port로 인하여 서버가 실행 되지 않습니다.");
		}
	}
	
}
