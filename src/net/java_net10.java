package net;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//TCP - Server (I/O) => 파일처리
public class java_net10 {
	public static void main(String[] args) {
		new java_net10_server("172.30.1.50",10008);
	}
}
class java_net10_server{
	String ip = null;
	int port = 0;
	
	Socket sk = null;
	ServerSocket ss = null;
	InputStream is = null;
	OutputStream os = null;
	
	public java_net10_server(String serverip, int serverport) {
		this.ip = serverip;
		this.port = serverport;
		this.ftp_server();
	}
	public void ftp_server() {
		try {
			this.ss = new ServerSocket(this.port);
			this.sk = this.ss.accept();
			System.out.println("****************서버 가동중*******************");
			String url = "d:\\java_net\\upload\\";	//클라이언트가 전송한 파일 저장소
			
			//클라이언트가 전송한 파일을 수신 역활
			this.is = this.sk.getInputStream();
			byte data[] = new byte[2097152];  //최대 업로드는 2MB 이하
			
			//클라이언트가 전송한 파일을 저장
			this.os = new FileOutputStream(url + "data.jpg");
			int filesize = 0;
			while((filesize = this.is.read(data))!= -1) {
				this.os.write(data,0,filesize);
				this.os.flush();
			}
			System.out.println("정상적으로 전송파일을 저장 하였습니다.");
			this.os.close();
			this.is.close();
			this.sk.close();
		} catch (Exception e) {
			e.getMessage(); //=> bind error
			System.out.println("서버 포트가 충돌 되었습니다.");
		}
	}
	
}