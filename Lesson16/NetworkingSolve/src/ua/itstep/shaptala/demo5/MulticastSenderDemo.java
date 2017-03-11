package ua.itstep.shaptala.demo5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MulticastSenderDemo {
	static final InetSocketAddress MCADDRESS = new InetSocketAddress("239.1.1.1", 12345);	
	
	public static void main(String[] args) throws Exception {
			
		DatagramSocket udpSocket = new DatagramSocket();		
		
		boolean run = true;
		while (run) {			
			byte[] msg = "Hello".getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length);
			packet.setSocketAddress(MCADDRESS);			
			udpSocket.send(packet);		
		}
		System.out.println("Sent a multicast message.");
		System.out.println("Exiting application");
		udpSocket.close();		
	}

}
