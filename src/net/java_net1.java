package net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;

/*
네트워크 JAVA
Ipv4 : 32bit (0.0.0.0) 0 ~ 255  => 172.30.1.254
Ipv6 : 128bit 0:0:0:0:0:0:0:0 => 2001:0dba:aac9:0000:0067:8a2e:0000:7321
NetMask : 다른네트워크에 속하는지, 같은 네트워크에 속하는지 확인 정보 
255.0.0.0 - 소수 호스트 (매우 많은 호스트 수 0 ~ 255)
255.255.0.0 - 중간 호스트 수 (중간 호스트 수 0 ~ 255)
255.255.255.0 - 다수의 호스트 수 (적은 호스트 수 255)

192.168.0.1(공유기), 192.168.0.255(게이트웨이)

유동IP => 지속적으로 할당 IP가 변동 , 고정IP => 고정적인 IP할당

Port => 네트워크 => 포트는 중복시 무조건 통신 불능
0 ~ 1500 => 실제 서비스 되는 port가 많음
1501 ~ 9000 => 각 APP에 할당 되는 port가 많음
9001 ~ 10000 => 임시포트
10001 ~ 50000 => 테스트 포트, 사설포트

TCP => 외부망 => HTTP, HTTPS, FTP, Email => 속도 느림, 신뢰성 보장
UDP => 외부망, 내부망 => 온라인게임, 실시간 스트리밍 => 속도빠름, 신뢰성 미보장
*/
public class java_net1 {

	public static void main(String[] args) {
		try {
			/*
			InetAddress : 도메인, IP를 이용하여 통신정보를 확인할 수있음
			getByName : 접속할 도메인 또는 IP 주소명을 말합니다.
			*/
			//Inet4Address ia = (Inet4Address)Inet4Address.getByName("naver.com");
			InetAddress ia = Inet4Address.getByName("coupang.com");
			System.out.println(ia);
			System.out.println(ia.getHostAddress());	//IP를 출력하는 getter
			System.out.println(ia.getHostName());		//도메인을 출력하는 getter
			
			//getAllByName : 접속할 도메인이나 IP관련 모든 서버주소의 리스트를 가져옴 (배열)
			InetAddress all[] = Inet4Address.getAllByName("coupang.com");
			System.out.println(Arrays.toString(all)); //배열 리스트를 출력
			
			
		}catch (Exception e) {
			System.out.println("해당 통신이 불안정 합니다.");
		}
	}

}
