package ua.itstep.shaptala.demo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class EchoServerMultiThreadDemo {
	static final String host = "localhost";
	static final int port = 10000;
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			while(true) {
				new ThreadClient(serverSocket.accept()).start();
				Thread.sleep(100);
			}		    
		}
	}
}

class ThreadClient extends Thread {
	Socket clientSocket;
	ThreadClient(Socket client) {
		this.clientSocket = client;
	}
	
	@Override
	public void run() {
		try(
		PrintWriter out =
		        new PrintWriter(clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(clientSocket.getInputStream()));
		) {
			System.out.println("Connected at: " + clientSocket.getRemoteSocketAddress());
			String inputLine, outputLine;
			out.println("This is simple echo server. Type Bye to exit.");
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println("["+LocalTime.now() + "] " + inputLine);
		        outputLine = inputLine;
		        out.println(outputLine);
		        if (outputLine.equals("Bye") || isInterrupted()) {
		            break;
		        }		        
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
