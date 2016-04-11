package ua.itstep.shaptala.demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServerDemo {
	public static class HomeHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			System.out.println("Request from " + t.getRemoteAddress());
			System.out.println("Local address " + t.getLocalAddress());
			System.out.println("Request URI " + t.getRequestURI());
			String html = "<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<title>Hello Web</title>"
					+ "<meta charset='utf-8'/>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Здравствуй веб</h1>"
					+ "<p>Переход на <a href='/home'>Домашнюю страницу</a></p>"
					+ "</body>"
					+ "</html>";	
			String html404 = "<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<title>Hello Web</title>"
					+ "<meta charset='utf-8'/>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Здравствуй веб</h1>"
					+ "<p>Переход на <a href='/home'>Домашнюю страницу</a></p>"
					+ "</body>"
					+ "</html>";
			String response = "";
			if(t.getRequestURI().toString().endsWith("home"))
			{
				t.sendResponseHeaders(404, html.getBytes("utf-8").length);
			}
			else
			{
				t.sendResponseHeaders(200, html.getBytes("utf-8").length);
			}
			try(OutputStream os = t.getResponseBody()){
				os.write(html.getBytes("utf-8"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8211), 0);
			server.createContext("/", new HomeHandler());
			server.createContext("/home", new HomeHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
