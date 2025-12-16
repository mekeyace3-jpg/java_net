package net;

import java.net.Socket;
import java.util.Scanner;

//TCP - 통신 Client
public class java_net13 {
	public static void main(String[] args) {
		  new java_net13_client("172.30.1.54",11002);
	}
}
class java_net13_client {
	Socket sk = null;
	String serverip = "";
	int serverport = 0;
	Scanner sc = new Scanner(System.in);
	public java_net13_client(String ip, int port) {
		this.serverip = ip;
		this.serverport = port;
		this.clients();
	}
	public void clients() {
		try {
			System.out.println("서버 접속 시도...");
			while(true) {
				this.sk = new Socket(this.serverip,this.serverport);
				System.out.println("정상적으로 서버 접속 완료!!");
				System.out.println("서버 종료 하시겠습니까?");
				String msg = this.sc.nextLine();
				if(msg.equals("종료")) {
					break;
				}
			}
			this.sk.close();
			
		} catch (Exception e) {
			System.out.println("서버 접속 오류 발생!!");
		}
	}
}
