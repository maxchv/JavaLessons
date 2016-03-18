package ua.itstep.shaptala;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiverDemo {

	public static void main(String[] args) {
		try(MulticastSocket mcreceiver = new MulticastSocket(UDPSender.PORT)){
			mcreceiver.joinGroup(InetAddress.getByName(UDPSender.MCADDRESS));
			
			DatagramPacket packet = new DatagramPacket(new byte[256], 256);
			mcreceiver.receive(packet);
			System.out.println(new String(packet.getData(), packet.getOffset(), packet.getLength()));
			
			mcreceiver.leaveGroup(InetAddress.getByName(UDPSender.MCADDRESS));
			
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

}
