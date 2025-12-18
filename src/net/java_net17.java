package net;

import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP 양방향 통신 - Client
public class java_net17 {
	public static void main(String[] args) {
		
		//서버에서 보낸 메세지를 처리하는 전용 Thread
		Runnable rb1 = new server_receive();
		new Thread(rb1).start();
		/*
		Thread th1 = new Thread(rb1);
		th1.start();
		*/
		//클라이언트가 서버로 보내는 전용 Thread
		Runnable rb2 = new server_send();
		new Thread(rb2).start();
		
	}
}

//서버에서 수신된 메세지를 받는 역활
class server_receive implements Runnable{	
	private final String ip = "172.30.1.72"; //서버 IP
	private final int port = 20000;		//자신의 포트 (서버에서 데이터 받는 전용포트)
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private String msg = "";
	public server_receive() {
		try {
			this.ds = new DatagramSocket(this.port);	//자신의 포트를 오픈
		} catch (Exception e) {
			System.out.println("클라이언트 포트 오류발생!!");
		}
	}
	@Override
	public void run() {
			try {
				while(true) {
					byte server_byte[] = new byte[1024];	//받는 데이터의 양을 설정
					this.dp = new DatagramPacket(server_byte, server_byte.length);
					this.ds.receive(this.dp);	//서버에서 보낸 내용을 받음
					int len = this.dp.getLength();
					this.msg = new String(this.dp.getData(),0,len);
					System.out.println("서버에서 보낸 메세지 :" + this.msg);	//서버의 메세지를 출력
				}
			} catch (Exception e) {
				System.out.println("클라이언트 받는 포트 연결 실패!!");
			}
	}
}
class server_send implements Runnable{	//클라이언트가 서버에게 송신
	private final String ip = "172.30.1.50";
	private final int myport = 20000;  //
	private int port = 10000;	//
	
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private String msg = "";
	private InetAddress ia = null;
	private InputStreamReader isr = null;
	@Override
	public void run() {
				
	}
}
