package net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Socket 관련사항 => PORT 오픈
/*
  OSI 7 Layer (통신 7계층)
  1계층 : 물리통신 (랜선연결, 무선통신 - wifi)
  2계층 : 링크통신 (IP할당)
  3계층 : 장치식별 (통신의 최적화)
  4계층 : TCP, UDP => PORT
  5계층 : Session => 통신유지상태 (예시 : 로그인)
  6계층 : 암호화, 복호화
  7계층 : 웹사이트의 결제, 장바구니 등 화면 구역 영역 
 */
public class java_net2 {
//Server - Socket
	public static void main(String[] args) {
		try {
			//ServerSocket : 서버컴퓨터에 필요한 정보를 오픈하는 클래스
			ServerSocket ss = new ServerSocket();
			//InetSocketAddress : 해당 PORT를 이용하여 통신을 오픈하는 형태
			InetSocketAddress ia = new InetSocketAddress("172.30.1.50",10000);
			//bind = add,append : 정적(자식 생성 후 호출), 동적(부모에게 접근 형태)
			ss.bind(ia);
			System.out.println("연결 오픈중 입니다....");
			Socket sc = ss.accept();	//소켓을 활성화하는 작업코드
			
			//Client 접속 환경을 체크 
			//getRemoteSocketAddress() : 상대방의 대한 IP정보 및 접속환경을 확인하는 메소드
			InetSocketAddress isa = (InetSocketAddress)sc.getRemoteSocketAddress();
			//Client 접속 정보를 확인(IP)
			System.out.println("Client 확인 : " + isa.getAddress().getHostAddress());
			
			ss.close();
		}catch (Exception e) {
			System.out.println("해당 포트가 충돌 발생 하였습니다.");
		}
	}

}
