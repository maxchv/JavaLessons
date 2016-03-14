package ua.itstep.shaptala.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {

	static final String url = "http://itstep.dp.ua";
	static URL itstep;
	static {
		try {
			itstep= new URL(url);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) throws Exception {
		// getUrlInfo();
		// readingDirectlyFromUrl();
		//readingUsingConnection();
	}

	private static void readingUsingConnection() throws IOException, UnsupportedEncodingException {
		HttpURLConnection conn = (HttpURLConnection) itstep.openConnection();
      		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		}
	}

	private static void readingDirectlyFromUrl() throws MalformedURLException, IOException {		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(itstep.openStream()))) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		}
	}

	private static void getUrlInfo() throws MalformedURLException {
		URL aURL = new URL("http://example.com:80/docs/books/tutorial" + "/index.html?name=networking#DOWNLOADING");

		System.out.println("protocol = " + aURL.getProtocol());
		System.out.println("authority = " + aURL.getAuthority());
		System.out.println("host = " + aURL.getHost());
		System.out.println("port = " + aURL.getPort());
		System.out.println("path = " + aURL.getPath());
		System.out.println("query = " + aURL.getQuery());
		System.out.println("filename = " + aURL.getFile());
		System.out.println("ref = " + aURL.getRef());
	}

}
