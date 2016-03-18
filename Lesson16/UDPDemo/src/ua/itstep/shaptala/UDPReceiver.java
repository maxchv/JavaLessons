package ua.itstep.shaptala;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiver {
	public static void main(String[] args) {		
		for(int port=9998; port<10005; port++) {
			new ThreadReceiver(port).start();
		}
	}
}

class ThreadReceiver extends Thread{
	int port;
	
	
	public ThreadReceiver(int port) {
		System.out.println("I am linening to " + port);
		this.port = port;
	}

	@Override
	public void run() {
		try(DatagramSocket receiver = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"))){
			receiver.setBroadcast(true);

			while(true) {				
				DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
				while(true) {
					receiver.receive(packet);		
					System.out.format("port: %d from: ", port);
					System.out.print(packet.getSocketAddress());
					System.out.println(": " + new String(packet.getData(), packet.getOffset(), packet.getLength()));
					//Thread.sleep(100);
				}
			}
		} catch (SocketException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} 
	}
}
