package ua.itstep.shaptala.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class EchoServerDemo {
	static final String host = "localhost";
	static final int port = 10000;
	public static void main(String[] args) throws Exception {
		try ( 
			    ServerSocket serverSocket = new ServerSocket(port);
			    Socket clientSocket = serverSocket.accept();
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
		        if (outputLine.equals("Bye")) {
		            break;
		        }
		    }
		}
	}

}
