package ua.itstep;

import java.util.Date;
import java.util.StringTokenizer;

public class UsingStringBuffer {
	

	public static void main(String[] args) {
		StringBuffer buff = new StringBuffer();
		System.out.println(buff.capacity());
		System.out.println(buff.length());
		buff.append("Это данные очень длинные");
		
		buff.delete(4, 10); // удаляем слово данные
		System.out.println(buff);
		
		buff.insert(4, "<новые данные>");
		System.out.println(buff);
		
		buff.replace(0, 3, "This");
		System.out.println(buff);
				
		buff.setLength(0);
		buff.trimToSize();
		System.out.println(buff.capacity());
		System.out.println(buff.length());
		
		String str = buff.toString();
		
		StringBuilder builder = new StringBuilder();
		
		String text = " \t		Технология Java позволяет работать и играть в безопасной вычислительной среде. Обновление до последней версии Java повысит безопасность вашей системы, так как в состав более старых версий не входят последние обновления системы безопасности.";
		
		String[] words = text.split("\\s+");
		System.out.println(words[0]);
		
		StringTokenizer token = new StringTokenizer(text);
		while(token.hasMoreTokens()) {
			System.out.println(token.nextToken());
			break;
		}
		
	}
}
