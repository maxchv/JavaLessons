package ua.itstep;

import java.util.Random;

public class StringsEx {
	public static void main(String[] args) {
		System.out.println("String Examples");

		// Класс для генерирования псевдослучайных чисел
		Random rnd = new Random();
		
		for(int i=0; i<10; i++) {
			//System.out.println((int)(100*Math.random()) + " ");
			System.out.println(rnd.nextInt(100));
		}
		// %d - целое число
		// %f - число с плавающей точкой
		// %s - строка
		// %n - перевод строки		
		String template = "Это %d занятие по Java";
		String lessonName = String.format(template, 2);
		System.out.println(lessonName);
		char firstLetter = lessonName.charAt(0);
		System.out.println(firstLetter);
		
		// Проверка на содержимое
		String str = "Это не JavaScript!";
		if(str.contains("Java")) {
			System.out.println("Это JAVA");
		}
				
		String[] files = { "data.txt", "data.xml", "Program.java", "data.data" };
		for(String file: files) { // цикл типа foreach
			if(file.endsWith(".java")) {
				System.out.println("Есть файл Java");
			}
		}
		
		// Сравенения
		String first = "Java";
		String second = "java";
		System.out.println(first.equalsIgnoreCase(second));
		
		String str1 = "Мурзик"; 
		String str2 = new String(str1);		
		boolean isCat = (str1 == str2); // Сравнение ссылок
		System.out.println(isCat );
		isCat = (str1.equals(str2));    // Сравнение значений
		System.out.println(isCat );

		
		String testString = "Котёнок"; 
		if (testString.compareTo("котёнок") == 0) {
			System.out.println("Строки равны"); 
		} 
		else { 
			System.out.println("Строки не равны. Возвращено" + 
							    testString.compareTo("котёнок"));
		}

		// валидация электронной почны
		String email = "email@mail.com";
		if(email.matches("\\w+@\\w+\\.\\w+")) {
			System.out.println("Это правильный email");
		}
	}
	
}
