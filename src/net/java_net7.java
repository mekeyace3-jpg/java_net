package net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//문자 통신 서버(Client) - TCP + PORT + SOCKET
public class java_net7 {
	static Socket sk = null;
	
	public static void main(String[] args) {
		String ip = "172.30.1.44";
		int port = 10000;
		System.out.println("클라이언트 서버 접속시도!!");
		Scanner sc = new Scanner(System.in);
		try {
			while(true) {
				sk = new Socket(ip,port);
				//클라이언트 -> 서버로 메세지 전송
				System.out.println("메세지를 입력하세요 : ");
				String message = sc.nextLine();
				OutputStream os = sk.getOutputStream();
				byte m[] = message.getBytes();
				os.write(m);
				
				//서버 -> 클라이언트 메세지 출력
				InputStream is = sk.getInputStream();
				byte msg[] = new byte[2048];
				is.read(msg);
				String result = new String(msg);
				System.out.println("서버에서 보낸 메세지 :" + result);
				
				os.flush();
				os.close();
				is.close();
			}
		} catch (Exception e) {
			System.out.println("서버 접속 지연오류 발생!!");
		} finally {
			sc.close();
		}
		
	}
}