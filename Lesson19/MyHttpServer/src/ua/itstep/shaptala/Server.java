package ua.itstep.shaptala;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.*;

public class Server {
	static class Root implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
			String html = "<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<title>Hello</title>"
					+ "<meta charset='utf-8'/>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Hello Web</h1>"
					+ "<p>URI: " + t.getRequestURI() + "</p>"
					+ "</body>"
					+ "</html>";
			t.sendResponseHeaders(200, html.getBytes("utf-8").length);
			try(OutputStream os = t.getResponseBody()) {
				os.write(html.getBytes("utf-8"));
			}
		}
	}

	public static void main(String[] args)  {
		 try {
			HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
			server.createContext("/", new Root());
			server.setExecutor(Executors.newFixedThreadPool(1));
			server.start();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}

}
