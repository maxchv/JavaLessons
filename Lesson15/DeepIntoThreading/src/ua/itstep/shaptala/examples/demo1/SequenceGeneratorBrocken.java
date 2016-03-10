package ua.itstep.shaptala.examples.demo1;

import java.util.ArrayList;
import java.util.List;

public class SequenceGeneratorBrocken {
	private static volatile int counter = 0;
	
	public static int nextInt() { return counter++; }
	
	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = new ArrayList<>();
		for(int i=0; i<10; i++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					for(int j=0; j<1000; j++) {
						nextInt();
					}
				}
			});
			thread.start();
			threads.add(thread);
		}
		for(Thread thread: threads) {
			thread.join();
		}
		
		System.out.println("Counter final value: " + counter);
	}

}
