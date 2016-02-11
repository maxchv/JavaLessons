package ua.itstep;

import java.util.Random;

public class StringsEx {
	public static void main(String[] args) {
		System.out.println("String Examples");

		// ����� ��� ������������� ��������������� �����
		Random rnd = new Random();
		
		for(int i=0; i<10; i++) {
			//System.out.println((int)(100*Math.random()) + " ");
			System.out.println(rnd.nextInt(100));
		}
		// %d - ����� �����
		// %f - ����� � ��������� ������
		// %s - ������
		// %n - ������� ������		
		String template = "��� %d ������� �� Java";
		String lessonName = String.format(template, 2);
		System.out.println(lessonName);
		char firstLetter = lessonName.charAt(0);
		System.out.println(firstLetter);
		
		// �������� �� ����������
		String str = "��� �� JavaScript!";
		if(str.contains("Java")) {
			System.out.println("��� JAVA");
		}
				
		String[] files = { "data.txt", "data.xml", "Program.java", "data.data" };
		for(String file: files) { // ���� ���� foreach
			if(file.endsWith(".java")) {
				System.out.println("���� ���� Java");
			}
		}
		
		// ����������
		String first = "Java";
		String second = "java";
		System.out.println(first.equalsIgnoreCase(second));
		
		String str1 = "������"; 
		String str2 = new String(str1);		
		boolean isCat = (str1 == str2); // ��������� ������
		System.out.println(isCat );
		isCat = (str1.equals(str2));    // ��������� ��������
		System.out.println(isCat );

		
		String testString = "������"; 
		if (testString.compareTo("������") == 0) {
			System.out.println("������ �����"); 
		} 
		else { 
			System.out.println("������ �� �����. ����������" + 
							    testString.compareTo("������"));
		}

		// ��������� ����������� �����
		String email = "email@mail.com";
		if(email.matches("\\w+@\\w+\\.\\w+")) {
			System.out.println("��� ���������� email");
		}
	}
	
}
