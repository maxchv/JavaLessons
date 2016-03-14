package ua.itstep.shaptala.demo4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

public class NetworkTimerDemo {

	public static final int PORT = 10001;

	public static void main(String[] args) throws Exception {
		byte[] buf;
		System.out.println("timer is started");
		try (DatagramSocket socket = new DatagramSocket()) {
			while (true) {
				buf = LocalTime.now().toString().getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), PORT);				
				socket.send(packet);
				Thread.sleep(500);
			}
		}
	}

}
