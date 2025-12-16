package net;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP - 메세지 수신 (Server) - 키오스크 메뉴 오더 파트 (주방)
public class java_net14 {
	public static void main(String[] args) {
		new java_net14().test();
	}
	private final String ip = "172.30.1.50";
	private final int port = 9090;
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private byte msg[] = null;
	
	public void test() {
		try {
			this.ia = InetAddress.getByName(this.ip);
			this.ds = new DatagramSocket(this.port);

			//while(true) {
				byte server_byte[] = new byte[4098];
				this.dp = new DatagramPacket(server_byte, server_byte.length);
				System.out.println("메뉴 오더 준비중...");
				this.ds.receive(this.dp);
				
				this.msg = this.dp.getData();
				int msglength = this.dp.getLength(); 
				
				Object menus = this.arrdata(this.msg, msglength);
				System.out.println(menus);
			//}
			
		}catch (Exception e) {
			System.out.println("포트 충돌로 인하여 서버가 가동 되지 않습니다.");
		}
	}
	public Object arrdata(byte[] by, int length) {
		try {
			ByteArrayInputStream os = new ByteArrayInputStream(by,0,length);
			ObjectInputStream oos = new ObjectInputStream(os);
			return oos.readObject();
		} catch (Exception e) {
			System.out.println("전송된 배열값이 잘못 되었습니다.");
			return null;
		}
	}
	
}
