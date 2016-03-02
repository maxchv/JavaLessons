package ua.itstep.shaptala.examples;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class App {

	static class Person {
		String name;
		LocalDate birthDate;
		String email;
		
		Person(String name, LocalDate birthDate, String email) {
			this.name = name;
			this.birthDate = birthDate;
			this.email = email;
		}
		
		@Override
		public String toString() {		
			return name + " " + email;
		}
	}
	
	public static void main(String[] args) {
		//usingDateTime();		
		usingMap();		
	}

	private static void usingMap() {
		Map<String, Person> people = new TreeMap<>();
		// ���������� ������
		people.put("������", new Person("�������", LocalDate.of(1999, Month.SEPTEMBER, 1), "nikola@gmail.com"));
		people.put("���������", new Person("����", LocalDate.of(1995, Month.MAY, 12), "milashka@gmail.com"));
		people.put("������", new Person("����", LocalDate.of(1996, Month.JUNE, 22), "vano@gmail.com"));
		
		// ������� ���� �������� ��� ����������� ��������� ������
		for(Person person: people.values()) {
			System.out.println(person);
		}
		
		// ������� ���� ������
		for(String nikname: people.keySet()) {
			System.out.println(nikname+": "+people.get(nikname));
		}
		
		// ��������
		Person Ivanov = people.remove("������");
		
		// ������� ���� entry
		for(Entry<String, Person> entry: people.entrySet()) {
			System.out.println(">>>" + entry.getKey() + "<<< " + entry.getValue());
		}		
		
		//�������� �� �����
		if(!people.containsKey("������")) {
			System.out.println("������ ������� ������");
		}
		// �������� �� ��������
		System.out.println(Ivanov);
		if(!people.containsValue(Ivanov)) {
			System.out.println("������ ���� ������");
		}
		
		Person noname = people.getOrDefault("Noname", new Person("Noname", LocalDate.now(), "noname@gmail.com") );
		System.out.println(noname);

		// �������� ��� ��������� ������ ��� �� ������� � �����
		// people.put("������", noname);
		// ��������� ������ ���� ������� � ������ "������" ���������� � �����
		people.putIfAbsent("������", noname);
		
		System.out.println("size:" + people.size());
		System.out.println(people);
	}

	private static void usingDateTime() {
		// ���������� ���������
		Date d = new Date(); 
		SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println(sformat.format(d));
		
		// ����� Time API �������� � ������ 8
		// ��� ������ � ������
		LocalDate birthDate = LocalDate.of(1985, Month.JULY, 15);
		LocalDate dateNow = LocalDate.now();
		Period period = Period.between(dateNow, birthDate) ;//dateNow.until(birthDate);
		System.out.println(period.getYears());
		
		// �����
		LocalTime lt = LocalTime.now();
		System.out.println(lt);
		
		// ����/�����
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt.format(DateTimeFormatter.ISO_TIME));
		
		
		// �������� ������������� �������
		Instant start = Instant.now();
		for(int i=0; i<1000000; i++) {
			Object[] o = new Object[1024];
		}
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end).getNano());
	}

}
