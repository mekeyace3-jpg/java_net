package net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//TCP 연결 => Socket 형태로 접속
//UDP 연결방식 (Server) => Socket형태가 없음
public class java_net8 {
	public static void main(String[] args) {
		String ip = "172.30.1.50";
		int port = 10003;
		try {
			//서버에서 사용하는 IP(TCP) 가져옴
			InetAddress ia = InetAddress.getByName(ip);
			
			//DatagramSocket => udp 내부 소켓
			DatagramSocket ds = new DatagramSocket(port);	//내부망 통신 PORT 오픈
			byte by[] = new byte[1024];
			
			//DatagramPacket => udp 송수신 패킷
			DatagramPacket dp = new DatagramPacket(by, by.length);
			System.out.println("서버 오픈....");
			ds.receive(dp);	//client에서 보낸 메세지를 서버에서 받는 역활
			
			InetAddress client_ip = dp.getAddress();	//상대방 접속 IP
			int client_port = dp.getPort();
			System.out.println(client_ip);
			System.out.println(client_port);
			
		} catch (Exception e) {
			System.out.println("UDP 포트 충돌로 인하여 서버가 중지 됩니다.");
		}
	}
}
