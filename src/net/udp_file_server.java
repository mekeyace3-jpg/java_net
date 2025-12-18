package net;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//UDP 통신으로 파일 송수신 - Server
public class udp_file_server {
	public static void main(String[] args) throws Exception {
		new file_server(9005);
	}
}
class file_server{
	DatagramPacket dp = null;
	DatagramSocket ds = null;
	FileOutputStream fos = null;
	String result;
	File f = null;
	int port = 0;
	
	public file_server(int port) throws Exception {
		this.port = port;
		this.save_files();
	}
	public void save_files() throws Exception {
		try {
			this.result = "d:\\java_net\\download\\file.webm";
			this.f = new File(this.result);
			System.out.println("FTP 서비스가 오픈 되었습니다.");
			this.ds = new DatagramSocket(this.port);
			/*
			 1024 고정 : txt, hwp
			 일반문서 및 이미지 5KB (5120): jpg, png (10 MB)
			 동영상 : 1MB(1048576) ~ 3MB => byte
			*/
			byte by[] = new byte[2048]; 
			this.fos = new FileOutputStream(this.f);
			for(;;){
				this.dp = new DatagramPacket(by, by.length);
				this.ds.receive(this.dp); //Client 수신
				
				int bylen = this.dp.getLength();
				System.out.println(bylen);
				if(bylen < by.length) {
					System.out.println("파일 업로드 완료됨!!");
					break;
				}
				
				this.fos.write(by,0,bylen);
			}
			this.fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UDP 서버 포트 충돌 발생!!");
		} 
	}
	
}