package net;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class file_udp_client {

	public static void main(String[] args) {
			Thread th1 = new file_post();
			th1.start();
			Thread th2 = new file_get();
			th2.start();
	}
}
class file_post extends Thread{
	Scanner sc = null;
	private final String server_ip = "172.30.1.72";
	private final int port = 8001;
	private int myport = 9000;
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private InputStream is = null;
	private BufferedInputStream bis = null;	
	public file_post() {
		try {
			this.sc = new Scanner(System.in);
			this.ia = InetAddress.getByName(this.server_ip);
			this.ds = new DatagramSocket(this.myport);
		} catch (Exception e) {
			System.out.println("전송할 포트가 충돌 됩니다.");
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("파일 경로 및 파일명을 입력해 주세요 : ");
				String url = this.sc.nextLine();
				this.is = new FileInputStream(url);
				this.bis = new BufferedInputStream(this.is);
				byte[] by = new byte[1500];
				System.out.println("파일 전송 시작");
				int w = 0;
				while((w = this.bis.read(by)) != -1) {
					this.dp = new DatagramPacket(by, w, this.ia, this.port);
					this.ds.send(this.dp);
					Thread.sleep(5);
				}
				this.bis.close();
				System.out.println("파일 전송이 모두 완료 되었습니다.");
			}
		} catch (Exception e) {
			System.out.println("클라이언트 포트 전송 오류 발생!!");
		}
	}
	
}

class file_get extends Thread{
	private final int port = 9001;		//자신의 포트 (서버에서 데이터 받는 전용포트)
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private File f = null;
	public file_get() {
		try {
			this.ds = new DatagramSocket(this.port);
		} catch (Exception e) {
			System.out.println("클라이언트 받는 포트가 충돌 되었습니다.");
		}
	}
	//서버에서 전송한 파일을 받는 Thread
	@Override
	public void run() {
		
		//Server 패킷과 동일한 byte
		byte server_byte[] = new byte[2048];
		
		//더블 반복문(큰반복문 - Thread가 자동으로 활성화)
		while(true) {
			//파일명을 년월일시분초 자동 생성 되도록 설정
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String url = "d:\\java_net\\download\\"+sdf.format(today)+".jpg";
			
			
			this.f = new File(url);
			FileOutputStream fos = null;
			
			try {
				System.out.println("새로운 파일 수신 대기중...");
				fos = new FileOutputStream(this.f);	//새로운 파일이 생성되면서 대기
				
				//작은 반복문으로 서버에서 데이터가 전송될 경우 반복문 체크해서 저장
				while(true) {
					this.dp = new DatagramPacket(server_byte, server_byte.length);
					this.ds.receive(this.dp);
					
					int len = this.dp.getLength();
					if(len < server_byte.length) {						
						System.out.println("파일 다운로드 완료 되었습니다.");
						break;
					}
					fos.write(server_byte,0,len); //자신의 PC에 파일을 저장
				}
				fos.close(); //파일 저장 스트림 종료
			} catch (Exception e) {
				System.out.println("저장 받는 파일 경로 및 파일이 올바르지 않습니다.");
			} 
		}
		
		
	}
}