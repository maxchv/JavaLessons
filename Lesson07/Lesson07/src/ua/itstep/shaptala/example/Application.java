package ua.itstep.shaptala.example;

import java.util.Scanner;

public class Application {

	static class List {
		Object[] obj;
		int size = -1;
		int cur = -1;
		
		List(int size) {
			cur = 0;
			this.size = size;
			obj = new Object[size];
		}
		
		int add(Object item) {
			if(!this.full()) {
				obj[cur++] = item;				
			}
			return cur;
		}

		void removeLast() {
			if(!this.empty()) {
				obj[cur--] = null;
			}
		}
		
		private boolean empty() {
			return cur == -1;
		}

		private boolean full() {
			return cur == size;
		}
	}
	
	static List obj = new List(512);
	
	public static void addObjects() {
		// 10 ��
		obj.add(new byte[10 * 1024 * 1024]);
	}
	
	public static void removeObjects() {
		obj.removeLast();
	}
	
	static Scanner in = new Scanner(System.in);
	
	public static void start() {
		boolean done = false;
		while(!done) {
			System.out.println("��������: " + obj.cur + " �������� �������� " 
		                                    + obj.cur * 10 + " ��");
			System.out.println("\n\n�������� �����:\n"
					+ "1. �������� ������\n"
					+ "2. �������� ������\n"
					+ "3. ������� GC\n"
					+ "0. �����\n"
					+ ">>> ");
			String line = in.nextLine();
			switch (line) {
			case "1":
				addObjects();
				break;
			case "2":
				removeObjects();
				break;
			case "3":
				System.gc();
				break;
			case "0":
				done = true;
				break;
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		start();
	}

}
