package ua.itstep.shaptala.examples;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo6 {

	static class Increment {
		private long value = 0;
				
		synchronized void increment() {
			value++;
		}
		
		synchronized void decrement() {
			value--;
		}

		public long getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Increment increment = new Increment();
		
		Thread incrementThread = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<100_000; i++) {
					increment.increment();
				}
			}
		});
		
		Thread decrementThread = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<100_000; i++) {
					increment.decrement();
				}
			}
		});
		
		incrementThread.start();
		decrementThread.start();
		
		incrementThread.join();
		decrementThread.join();
		
		System.out.println(increment.getValue());
		
	}

}
