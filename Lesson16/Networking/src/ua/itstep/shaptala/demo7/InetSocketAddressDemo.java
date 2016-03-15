package ua.itstep.shaptala.demo7;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetSocketAddressDemo {

	public static void main(String[] args) throws UnknownHostException {
		InetSocketAddress addr = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(),8080);
		System.out.println(addr.getPort());
	}

}
