package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP - 메세지 송수신 (Server)
public class udp_server {
	public static void main(String[] args) {
		new chat_server();
	}
}
class chat_server{
	private final String ip = "172.30.1.50";
	private final int port = 9090;
	private DatagramSocket ds = null;
	private DatagramPacket dp = null;
	private InetAddress ia = null;
	private String msg = "";
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	
	public chat_server() {
		try {
			this.ia = InetAddress.getByName(this.ip);
			this.ds = new DatagramSocket(this.port);
			this.udp();
		}catch (Exception e) {
			System.out.println("포트 충돌로 인하여 서버가 가동 되지 않습니다.");
		}
	}
	private void udp() {
		try {
			while(true) {
				byte server_byte[] = new byte[1024];
				this.dp = new DatagramPacket(server_byte, server_byte.length);
				System.out.println("상담원이 접속하였습니다.");
				this.ds.receive(this.dp);
				this.msg = new String(this.dp.getData());
				System.out.println(this.msg);
				
				/* 클라이언트에게 송신 받은 메세지를 출력 */
				System.out.println("상담자에게 보낼 메세지를 입력하세요 : ");
				InetAddress ia2 = this.dp.getAddress();	//상대방 IP
				int port2 = this.dp.getPort();	//상대방 port
				
				this.isr = new InputStreamReader(System.in);	//Scanner없이
				this.br = new BufferedReader(this.isr);	//클라이언트 보낼 메세지를 메모리에 등록
				this.msg = this.br.readLine();
				byte client_msg[] = this.msg.getBytes();
				//해당 DatagramPacket client 정보 : (byte변수,byte길이,client_ip,client_port)
				this.dp = new DatagramPacket(client_msg, client_msg.length, ia2, port2);
				this.ds.send(this.dp);
			}
			
		} catch (Exception e) {
			System.out.println("UDP 서버 오픈 오류발생!!");
		}
		
	}
	
	
	
}