package net;

import java.net.InetSocketAddress;
import java.net.Socket;

//Client - Socket
public class java_net3 {

	public static void main(String[] args) {
		try {
			//InetSocketAddress : 서버 정보를 입력하는 클래스
			InetSocketAddress ia2 = new InetSocketAddress("172.30.1.44",10000);
			//10000 포트를 이용하여 소켓 통신을 시작함
			Socket sk = new Socket();
			sk.connect(ia2);	//connect : 해당 서버에 Client가 접속하는 메소드
			System.out.println("연결확인");
			sk.close();
		}catch (Exception e) {
			System.out.println("서버쪽에 통신이 불능상태 입니다.");
		}
	}

}
