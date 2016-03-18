package ua.itstep.shaptala;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSender {
	final static int PORT = 10001; 
	final static String MCADDRESS = "239.1.1.1";
	final static String BADDRESS = "255.255.255.255";
	public static void main(String[] args) {
		try(DatagramSocket sender = new DatagramSocket()){
			System.out.println(sender.getBroadcast());
			Long i = 0l;
			while(true) {
				byte[] bytes = i.toString().getBytes();
				DatagramPacket packet = new DatagramPacket(bytes, bytes.length, 
														   InetAddress.getByName(BADDRESS), PORT);
				//System.out.println(packet);
				//System.out.println(sender.getLocalPort());
				sender.send(packet);
				i++;
				Thread.sleep(100);
			}
		} catch (SocketException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
}
