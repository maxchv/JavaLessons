package socketDemo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int port = 10000; 
	public static void main(String[] args) {
		try(
				ServerSocket server = new ServerSocket(port);
		){
			System.out.println("Server ready");
			Socket socketClient = server.accept();
			
			//clinetInfo(socketClient);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));			
			PrintWriter out = new PrintWriter(new BufferedOutputStream(socketClient.getOutputStream()), true);
			
			out.println("Type 'Bye' to exit");
			
			String line;
			while((line=in.readLine()) != null) {
				if(line.equals("Bye")) {
					break;
				}
				System.out.println("client >>> "+line);								
				out.println(line.toUpperCase());
			}			
			
			in.close();
			out.close();
			server.close();
			System.out.println("Server is shutdown");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	private static void clinetInfo(Socket socketClient) {
		System.out.println(socketClient);
		System.out.println("LocalAddress: "+socketClient.getLocalAddress());
		System.out.println("Port: "+socketClient.getPort());
		System.out.println("InetAddress: "+socketClient.getInetAddress());
		System.out.println("LocalSocketAddress: "+socketClient.getLocalSocketAddress());
		System.out.println("RemoteSocketAddress: "+socketClient.getRemoteSocketAddress());
	}

}
