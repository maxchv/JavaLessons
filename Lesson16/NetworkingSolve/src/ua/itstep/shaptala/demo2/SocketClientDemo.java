package ua.itstep.shaptala.demo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

public class SocketClientDemo {
	public static void main(String[] args) throws Exception {		
		try (			    
				Socket echoSocket = 
					new Socket(EchoServerDemo.host, EchoServerDemo.port);
			    PrintWriter out =
			        new PrintWriter(echoSocket.getOutputStream(), true);
			    BufferedReader in =
			        new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			    BufferedReader stdIn =
			        new BufferedReader(new InputStreamReader(System.in))
			) {
			String userInput = in.readLine();
			System.out.println(userInput);
			while (true) {
				System.out.print(">>> ");
				userInput = stdIn.readLine();
				if(userInput == null || userInput.equals("Bye")) {
					break;
				}				
			    out.println(userInput);
			    System.out.println("["+LocalTime.now()+"] " + in.readLine());
			}
		}
	}

}
