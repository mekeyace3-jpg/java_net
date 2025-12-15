package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

//네트워크 URL 정보현황
public class java_net5 {

	public static void main(String[] args) {
		String url = "https://www.dsa.or.kr/apply.html?html=apply/nongchon3-3.html#sub-tab";
		try {
			URL u = new URL(url);
			URLConnection con = u.openConnection();
			System.out.println(u.getProtocol()); //http, https, ftp
			System.out.println(u.getPort()); //도메인:포트번호 가져옴
			System.out.print(u.getHost());	//실제 도메인명
			System.out.println(u.getFile()); //실행 파일 (경로 + 파라미터값)
			System.out.println(u.getQuery()); //파라미터값만 가져옴
			
			InputStream is = u.openStream(); //url 경로에만 사용하는 Stream
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			OutputStream fs = new FileOutputStream("d:\\java_net\\download\\index.html");
			OutputStreamWriter osw = new OutputStreamWriter(fs,"UTF8");
			BufferedWriter fw = new BufferedWriter(osw);
			
			String code = "";
			while((code = br.readLine()) != null) {
				fw.write(code);
			}
			fw.flush();
			fw.close();
			br.close();
						
		} catch (Exception e) {
			System.out.println("해당 URL 정보가 올바르지 않습니다.");
		}
	}

}
