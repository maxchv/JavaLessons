package ua.itstep.shaptala.examples;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1433692675110109523L;

	public enum Sex {
		MALE, FEMALE
	}

	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;

	Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
		name = nameArg;
		birthday = birthdayArg;
		gender = genderArg;
		emailAddress = emailArg;
	}

	public int getAge() {
		return birthday.until(LocalDate.now()).getYears();
	}

	public void printPerson() {
		System.out.println(name + ", " + this.getAge());
	}
	
	@Override
	public String toString() {	
		return name + ", " + this.getAge();
	}

	public Sex getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}

	public static List<Person> createRoster() {

		List<Person> roster = new ArrayList<>();
		roster.add(new Person("Fred", LocalDate.of(1980, 6, 20), Person.Sex.MALE, "fred@example.com"));
		roster.add(new Person("Jane", LocalDate.of(1990, 7, 15), Person.Sex.FEMALE, "jane@example.com"));
		roster.add(new Person("George", LocalDate.of(1991, 8, 13), Person.Sex.MALE, "george@example.com"));
		roster.add(new Person("Bob", LocalDate.of(2000, 9, 12), Person.Sex.MALE, "bob@example.com"));

		return roster;
	}

	@Override
	public int compareTo(Person o) {		
		return getAge() - o.getAge();
	}
}
