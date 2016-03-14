package ua.itstep.shaptala.demo7;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressDemo {

	public static void main(String[] args) throws Exception {
		InetAddress localhost = InetAddress.getLocalHost();		
		intetAddressInfo(localhost);
		InetAddress[] addrs = InetAddress.getAllByName("www.nba.com");
		for(InetAddress addr: addrs) {
			intetAddressInfo(addr);
		}
	}

	private static void intetAddressInfo(InetAddress addr) throws IOException {
		System.out.println("===================");
		System.out.println(addr);
		System.out.println("===================");
		System.out.println("Host name: " + addr.getHostName());
		System.out.println("Address: " + addr.getHostAddress());
		System.out.println("Canonical Host: "+addr.getCanonicalHostName());
		System.out.println("Is Multicast? "+addr.isMulticastAddress());
		System.out.println("Is Reachable? "+addr.isReachable(100));
		System.out.println("Is Loopback? "+addr.isLoopbackAddress());
	}

}
