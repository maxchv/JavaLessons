package ua.itstep.shaptala.demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import sun.misc.Regexp;
import ua.itstep.shaptala.demo1.SimpleHttpServerDemo.HomeHandler;

public class SimpleHttpServerDemo {
	public static class HomeHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			String response = "This is the response";			
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}

	}

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8111), 0);
			server.createContext("/", new HomeHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
