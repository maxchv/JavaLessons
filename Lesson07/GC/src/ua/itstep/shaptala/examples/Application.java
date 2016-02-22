package ua.itstep.shaptala.examples;

import java.util.Scanner;

public class Application {

	private static List objects = new List(512);
	private static String input;
	private static Scanner in = new Scanner(System.in);

	/**
	 * По умолчанию - создание объекта размером 1 Мб
	 */
	private static void createObjects() {
		createObjects(1);
	}

	/**
	 * Создание объекта
	 * 
	 * @param size
	 *            - размер объекта в Мб
	 */
	private static void createObjects(int size) {
		System.out.println("Creating objects...");
		if (!objects.full()) {
			objects.add(new byte[size * 1024 * 1024]);
		} else {
			System.out.println("List is full");
		}
	}

	private static void removeObjects() {
		if (!objects.empty()) {
			System.out.println("Removing objects...");
			objects.pop();
		} else {
			System.out.println("List is empty");
		}
	}

	private static void start() {
		boolean done = false;
		while (!done) {
			System.out.println("\n\nI have " + objects.count() + " objects in use, about " + (objects.count() * 10) + " MB." 
					+ "\nWhat would you like me to do?\n" 
					+ "1. Create some objects\n"
					+ "2. Remove some objects\n" 
					+ "3. Collect\n"
					+ "0. Quit");
			input = in.nextLine();
			switch (input) {
			case "0":
				done = true;
				break;
			case "1":
				createObjects();
				break;
			case "2":
				removeObjects();
				break;
			case "3":
				System.gc();
				break;
			default:
				break;
			}
		}
		System.out.println("Bye!");
	}

	public static void main(String[] args) {
		start();
	}

}

/**
 * Простой список
 * 
 * @author shaptala
 */
class List {
	private Object[] array;
	private int size;
	private int cur = -1;

	public List(int i) {
		size = i;
		cur = 0;
		array = new Object[size];
	}

	public int add(Object item) {
		if (!this.full()) {
			array[cur++] = item;
		}
		return cur;
	}

	public void pop() {
		if (!this.empty()) {
			array[cur--] = null;
		}
	}

	public int count() {
		return cur;
	}
	
	public int length() {
		return size;
	}

	public boolean empty() {
		return cur == 0;
	}

	public boolean full() {
		return cur == size;
	}
}
