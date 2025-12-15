package net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

//외부서버에 있는 이미지를 다운로드 받는 통신
//https는 정상적인 작동이 불가능
public class java_net4 {

	public static void main(String[] args) {
		try {
			String url = "d:\\java_net\\download\\";	//저장 위치
			Scanner sc = new Scanner(System.in);
			System.out.println("웹에서 가져올 이미지 주소를 입력하세요 : ");
			String web = sc.nextLine();
			
			//network IO => File
			URL u = new URL(web);	//사용자가 입력한 url 정보를 가져옴
			URLConnection con = u.openConnection();	//해당 서버로 접근함
			
			int imgsize = con.getContentLength();	//파일 사이즈
			long date = con.getDate();	//접속해서 해당 파일을 가져온 날짜
			System.out.println(date);			
			System.out.println(imgsize);	//접속한 이미지의 파일 사이즈
			
			InputStream is = u.openStream();	//openStream : 해당 외부 경로의 파일을 byte로 읽어들임
			BufferedInputStream bs = new BufferedInputStream(is);
			byte b[] = new byte[bs.available() / 2048];	//해당 파일을 읽어들이는 사이즈 크기
			
			//자신의 PC 경로에 해당 파일을 다운로드 받는 형태
			OutputStream os = new FileOutputStream(url + "333.jpg");
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int size = 0;
			while((size = bs.read(b)) != -1) {
				bos.write(b,0,size);
			}			
			bos.flush();
			bos.close();
			bos.close();			
			sc.close();
			System.out.println("해당 서버에 파일을 정상적으로 다운로드 완료 하였습니다.");
		} catch (Exception e) {
			System.out.println("해당 서버의 정보가 올바르지 않습니다.");
		}

	}

}
