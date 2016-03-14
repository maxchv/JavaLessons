package ua.itstep.shaptala.demo5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MulticastSenderDemo {

	public static void main(String[] args) throws Exception {
		int mcPort = 12345;
		String mcIPStr = "230.1.1.1";
		DatagramSocket udpSocket = new DatagramSocket();
		boolean run = true;
		while (run) {
			InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
			byte[] msg = "Hello".getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length);
			packet.setAddress(mcIPAddress);
			packet.setPort(mcPort);
			udpSocket.send(packet);		
		}
		System.out.println("Sent a  multicast message.");
		System.out.println("Exiting application");
		udpSocket.close();
		
	}

}
