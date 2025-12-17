package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class server_udp {
	public static void main(String[] args) {
		try {
			new menu_order();
		}catch (Exception e) {
			System.out.println("해당 서버의 UDP 포트 충돌이 발생 하였습니다.");
		}
	}
}

class menu_order{
	private final String ip = "172.30.1.50";
	private final int port = 8082;
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private String msg = "";
	private InetAddress guest = null;
	
	
	public menu_order() throws Exception {
		this.ia = InetAddress.getByName(this.ip);
		this.ds = new DatagramSocket(this.port);
		try {
			
			byte server_byte[] = new byte[4096]; //=>저장 용량
			this.dp = new DatagramPacket(server_byte, server_byte.length);
			this.ds.receive(this.dp);
			//Client에서 byte에 대한 길이 값이 정하지 않은 상황에서 전송된 byte 길이를 확인하기 위한 사항
			int word_ea = this.dp.getLength();		
			
			this.msg = new String(this.dp.getData(),0,word_ea);
			System.out.println("주문한 메뉴는" + this.msg +"를 신청하셨습니다.");
			
			this.guest = this.dp.getAddress();
			int port2 = this.dp.getPort();	
			System.out.println("접속 IP : " + this.guest.getHostAddress());
			System.out.println("접속 PORT : " + port2);
			
			this.ds.close();
		}catch (Exception e) {
			e.getMessage();
			System.out.println("데이터 처리 오류 발생!!");
		}
	}
	
}