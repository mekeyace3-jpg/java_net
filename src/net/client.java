package net;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		new menu_client("172.30.1.54");
	}
}

class menu_client{
	String ip = null;
	int port = 0;
	Socket sk = null;
	InputStream is = null;
	OutputStream os = null;
	Scanner sc = null;
	String menus = null;
	
	public menu_client(String server) {
		this.ip = server;
		this.sc = new Scanner(System.in);
		this.menu_select();
	}
	public void menu_select() {
		System.out.println("다음 메뉴를 선택하여 통신을 사용합니다.[1. 채팅, 2. 파일전송] : ");
		this.menus = this.sc.nextLine();
		if(this.menus.equals("1")) {
			this.port = 10000;
			this.menu_chat(this.port);
		}
		else {
			this.port = 10001;
			this.menu_file(this.port);
		}
	}
	
	public void menu_chat(int p) {	//채팅 메소드
		System.out.println("전송할 메세지를 입력하세요 : ");
		this.menus = this.sc.nextLine();
		try {
			this.sk = new Socket(this.ip,p);
			this.os = this.sk.getOutputStream();
			byte msg[] = this.menus.getBytes();
			this.os.write(msg);
			this.os.flush();
			
		}catch (Exception e) {
			System.out.println("서버에 연결하지 못하였습니다.");
		}finally {
			try {
				this.os.close();
				this.sk.close();
				this.sc.close();
			}
			catch(Exception e) {
				e.getMessage();
				System.out.println("정상적으로 종료하지 못하였습니다.");
			}
		}
	}
	
	public void menu_file(int p) {	//파일 전송 메소드
		try {
			System.out.println("전송할 파일 경로 및 파일명을 입력하세요 :  ");
			this.menus = this.sc.nextLine();	
			this.sk = new Socket(this.ip,p);
			this.is = new FileInputStream(this.menus);
			BufferedInputStream bs = new BufferedInputStream(this.is);
			byte img[] = new byte[bs.available()];
			bs.read(img);
			
			this.os = this.sk.getOutputStream();
			this.os.write(img);
			this.os.flush();
			
		}catch (Exception e) {
			System.out.println("서버에 연결하지 못하였습니다.");
		}finally {
			try {
				this.is.close();
				this.os.close();
				this.sk.close();
				this.sc.close();
			}catch (Exception e) {
				System.out.println("정상적으로 종료하지 못하였습니다.");
			}
		}
	}
	
}
