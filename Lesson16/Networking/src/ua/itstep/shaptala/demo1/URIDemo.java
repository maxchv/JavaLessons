package ua.itstep.shaptala.demo1;

import java.net.URI;
import java.net.URISyntaxException;


public class URIDemo {

	public static void main(String[] args) throws URISyntaxException {
		// Синтаксис URI
		// [scheme:] специфичная часть схемы [фрагмент #] 
		String[] uris = { "mailto:java-net@java.sun.com", // Непрозрачный URI
				          "news:comp.lang.java",
				          "urn:isbn:096139210x",
				          "http://user@java.sun.com:80/j2se/1.3/?q=q#mark", // Иерархический URI
				          // [scheme:] [полномочия //] [путь] [запрос ?] [фрагмент #]
				          // Компонент полномочий иерархического URI: [пользователь-info@] узел [порт :]
				          "docs/guide/collections/designfaq.html#28",
				          "../../../demo/jfc/SwingSet2/src/SwingSet2.java",
				          "file:///~/calendar"
		};

		for(String u : uris) {
			URI uri = new URI(u);
			System.out.println("-----   "+u+"   -----");
			System.out.format("%20s: %s\n", "Absolute", uri.isAbsolute());
			System.out.format("%20s: %s\n", "Schema", uri.getScheme());
			System.out.format("%20s: %s\n","SchemeSpecificPart", uri.getSchemeSpecificPart());
			System.out.format("%20s: %s\n", "Fragment", uri.getFragment());
			System.out.format("%20s: %s\n", "UserInfo", uri.getUserInfo());
			System.out.format("%20s: %s\n", "Authority", uri.getAuthority());
			System.out.format("%20s: %s\n", "Query", uri.getQuery());
			System.out.format("%20s: %s\n", "Host", uri.getHost());
			System.out.format("%20s: %s\n", "Port", uri.getPort());
			System.out.format("%20s: %s\n", "Path", uri.getPath());
			System.out.println("\n\n");
		}
		// Разрешение относительного URI 
		URI base = new URI("http://java.sun.com/j2se/1.3/docs/guide/../");
		URI resolved = base.resolve("../../../demo/jfc/SwingSet2/src/SwingSet2.java");
		// http://java.sun.com/demo/jfc/SwingSet2/src/SwingSet2.java
		System.out.println(resolved);

		// Относительный путь
		URI relativized = base.relativize(new URI("http://java.sun.com/j2se/1.3/docs/guide/collections/designfaq.html#28"));
		System.out.println(relativized);
	}

}
