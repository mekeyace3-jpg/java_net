package net;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StringBufferInputStream;
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

			
			byte server_byte[] = new byte[4098];
			this.dp = new DatagramPacket(server_byte, server_byte.length);
			System.out.println("메뉴 오더 준비중...");
			this.ds.receive(this.dp);
				
			this.msg = this.dp.getData();
			int msglength = this.dp.getLength(); 
			
			//byte -> ArrayList 해석 후 -> Object
			Object menus = this.arrdata(this.msg, msglength);
			System.out.println(menus); 
		
			
		}catch (Exception e) {
			System.out.println("포트 충돌로 인하여 서버가 가동 되지 않습니다.");
		}
	}
	
	//byte[] => Object(자료형) => 변환된 내용 출력
	
	public Object arrdata(byte[] by, int length) {
		try {
			/*
			  ByteArrayInputStream => Stream + Buffered 
			  
			  ByteArrayInputStream => 네트워크 전용 I/O
			  File I/O, 일반 Stream I/O =>  ByteArrayInputStream (JVM Memory)
			 */  
			ByteArrayInputStream os = new ByteArrayInputStream(by,0,length);
			/*
			  단독사용시 불가능함
			  ObjectInputStream (여러가지 타입을 제공하는 Stream-필터) : FileInputStream 같은 형태
			  Object => int, String, boolean, float 
			 */
			ObjectInputStream oos = new ObjectInputStream(os);
			/*
			String word = "d:\\java_io\\123.jpg";
			InputStream fs = new FileInputStream(word);
			ObjectInputStream oos2 = new ObjectInputStream(fs);
			Object result = oos2.readObject();
			*/
			return oos.readObject();	//
		} catch (Exception e) {
			System.out.println("전송된 배열값이 잘못 되었습니다.");
			return null;
		}
	}
	
}
