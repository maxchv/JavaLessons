package ua.itstep.shaptala.demo8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleTranslate {

	public static void main(String[] args) {
		try {
			//URI google = new URI("https://translate.google.com/");
			URL url = new URL("https://translate.yandex.ru/?text=test&lang=en-ru");//google.resolve("#en/ru/hello").toURL();
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");			
			  
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf8"));			
			
			String inputLine;
			StringBuffer buffer = new StringBuffer(); 
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				buffer.append(inputLine + "\n");		
			}
			
			PrintWriter w = new PrintWriter("out.txt");
			w.println(buffer.toString());
			
			String test = "<div id=\"translation\" class=\"textinput textlayer translation state-fadeaway\" tabindex=\"1\"><span class=\"translation-chunk\" data-align=\0:4\">тест</span></div>";
			Pattern re = Pattern.compile("<div id=\"translation\"[^>]+><span[^>]+>([^<]+)<");
			Matcher m = re.matcher(buffer.toString());
			System.out.println(m.matches());
			if(m.find()) {
				//System.out.println(m);
				System.out.println(m.group(1));
			}
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
