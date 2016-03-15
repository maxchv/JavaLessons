package socketDemo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try (
				Socket client = new Socket("localhost", Server.port);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()), true);
			) {					
			
			String line;
			
			line=in.readLine();
			System.out.println("server >>> " + line);
			
			while(true) {				
				System.out.print("client >>> ");
				line = reader.readLine();
				out.println(line);
				if(line.equals("Bye")) {
					break;
				}
				String answer = in.readLine();
				System.out.println("server >>> "+answer);
			}
			System.out.println("Client closed connection");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
