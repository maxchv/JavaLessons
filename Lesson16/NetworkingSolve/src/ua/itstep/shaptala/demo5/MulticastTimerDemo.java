package ua.itstep.shaptala.demo5;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastTimerDemo {

	public static void main(String[] args) throws Exception {
		
		MulticastSocket mcSocket = new MulticastSocket(MulticastSenderDemo.MCADDRESS.getPort());		
		
		mcSocket.joinGroup(MulticastSenderDemo.MCADDRESS.getAddress());
		
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		System.out.println("Waiting for a  multicast message...");
		mcSocket.receive(packet);
		
		String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.println("[Multicast  Receiver] Received:" + msg);

		mcSocket.leaveGroup(MulticastSenderDemo.MCADDRESS.getAddress());
		mcSocket.close();
	}

}
