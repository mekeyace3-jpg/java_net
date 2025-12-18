package net;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//UDP 통신으로 파일 송수신 - Client
public class udp_file_client {
	public static void main(String[] args) {
		new file_client("172.30.1.54",23001);
	}
}

class file_client{
	Scanner sc = null;
	String server = null;
	int port = 0;
	File f = null;
	String url = "";
	DatagramPacket dp = null;
	DatagramSocket ds = null;
	FileInputStream fis = null;
		
	public file_client(String server, int port) {
		this.server = server;
		this.port = port;
		this.sc = new Scanner(System.in);
		System.out.println("업로드할 이미지 경로를 입력하세요 : ");
		this.url = this.sc.nextLine();
		
		this.f = new File(this.url);
		long check = this.f.length();  //전송할 파일 크기
		
		this.sc.close();
		this.stream_service(check);	//서버로 송신 메소드
	}
	public void stream_service(long ck) {
		try {
			//서버 IP를 로드해서 연결
			InetAddress server = InetAddress.getByName(this.server);
			this.fis = new FileInputStream(this.url);

			//파일의 전체 크기를 가져와서 int로 계산하여 실제 byte 자료형이 적용
			//int b = (int)(ck / 2048);
			
			byte[] by = new byte[2048];
			System.out.println("파일 전송 시작!!");
			this.ds = new DatagramSocket();
			
			int w;
			while((w = this.fis.read(by))!= -1) {
				this.dp = new DatagramPacket(by,w,server,this.port);
				this.ds.send(this.dp);
				Thread.sleep(10);
			}
			
			System.out.println("파일전송이 모두 완료 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("해당 서버에 접근이 불가능 합니다.");
		}
	}
	
}