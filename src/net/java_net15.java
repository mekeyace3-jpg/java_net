package net;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

//UDP - 메세지 송신 (Client) - 키오스크 메뉴 오더 파트 (테이블 손님)
public class java_net15 {
	public static void main(String[] args) {
		new java_net15().test();
	}
	private final String ip = "172.30.1.54";	//서버 ip
	private final int port = 9090;	//서버 port (송,수신)
	private final int myport = 51224;	//client 사용할 PC port (송,수신)
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private String msg = "";	//client (송수신) 메세지를 담는 변수
	private ArrayList<String> datalist = null;
	
	public byte[] arr_byte(Object obj) {
		try {
			//Array 클래스 배열을 Byte 형태로 변환
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			//Object 형태로 변환
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(obj);	//ArrayList 배열값을 -> ObjectStream 변환
			oos.flush();
			
			return os.toByteArray(); 		//byte 배열로 반환해서 사용하는 형태			
		}catch (Exception e) {
			System.out.println("해당 배열 클래스 오류발생!!");
			return null;
		}
	}
	
	
	
	public void test() {
		try {
			this.ia = InetAddress.getByName(this.ip); //서버 아이피
			this.ds = new DatagramSocket(this.myport); //서버에게 자신의 접속 UDP port 정보
			//클래스 배열에 메뉴를 선택한 사항 정보를 저장
			this.datalist = new ArrayList<String>();
			this.datalist.add("메뉴1");
			this.datalist.add("메뉴2");
			this.datalist.add("메뉴3");
			while(true) {
				//클래스 배열 -> byte로 변환 전송
				byte by[] = this.arr_byte(this.datalist);
				//서버에게 전송할 내용을 Packet 생성
				this.dp = new DatagramPacket(by,by.length,this.ia, this.port);
				this.ds.send(this.dp);
				System.out.println("상담내용 전송완료!!");
			}
		}catch (Exception e) {
			e.getMessage();
			System.out.println("서버 접속 오류 발생!!");
		}
	}
}
