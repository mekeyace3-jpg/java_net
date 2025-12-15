package net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP 연결방식 (Client)
public class java_net9 {

	public static void main(String[] args) {
			String ip = "172.30.1.44";
			int port = 10003;
			try {
				InetAddress ia = InetAddress.getByName(ip);	//서버 아이피 정보를 가져옴(UDP 포트)
				DatagramSocket ds = new DatagramSocket(port);

				while(true) {
					byte b[] = new byte[1024];
					DatagramPacket dp = new DatagramPacket(b, b.length, ia, port);
					ds.send(dp);	//메세지 전송
					System.out.println("서버에 해당 정보 발송완료!!");
					
					byte b2[] = new byte[1024];
					dp = new DatagramPacket(b2, b2.length);
					ds.receive(dp);
					System.out.println(new String(dp.getData()));
				}
				
			}catch (Exception e) {
				System.out.println("서버 접속에 실패 하였습니다.");
			}
	}

}
