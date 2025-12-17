package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP - 메세지 송수신 (Client)
public class udp_client {
	public static void main(String[] args) {
			new chat_client();
	}
}

class chat_client{
	private final String ip = "172.30.1.54";	//서버 ip
	private final int port = 9090;	//서버 port (송,수신)
	private final int myport = 51224;	//client 사용할 PC port (송,수신)
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private String msg = "";	//client (송수신) 메세지를 담는 변수
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	
	public chat_client() {
			this.udp();
	}
	//서버에 UDP 접속 후 메세지를 발송하는 메소드
	public void udp() {
		try {
			this.ia = InetAddress.getByName(this.ip); //서버 아이피
			this.ds = new DatagramSocket(this.myport); //서버에게 자신의 접속 UDP port 정보
			while(true) {
				System.out.println("상담 내용을 입력하세요 : ");
				this.isr = new InputStreamReader(System.in);
				this.br = new BufferedReader(this.isr);
				this.msg = this.br.readLine();
				
				byte by[] = this.msg.getBytes();
				//서버에게 전송할 내용을 Packet 생성
				this.dp = new DatagramPacket(by,by.length,this.ia, this.port);
				this.ds.send(this.dp);
				System.out.println("상담내용 전송완료!!");
				
				//서버에서 수신된 메세지를 출력
				byte server[] = new byte[1024];
				this.dp = new DatagramPacket(server, server.length);
				this.ds.receive(this.dp);
				this.msg = new String(this.dp.getData());
				System.out.println("상담원 : " + this.msg);
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println("서버 접속 오류 발생!!");
		}
	}
	
}
