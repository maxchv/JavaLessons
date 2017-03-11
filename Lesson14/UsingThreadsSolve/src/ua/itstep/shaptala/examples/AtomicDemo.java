package ua.itstep.shaptala.examples;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

	static class Increment {
		//private int value = 0;
		private AtomicInteger value = new AtomicInteger(0);
				
		//synchronized void increment() {
		void increment() {
			//value++;
			value.incrementAndGet();
		}
		
		//synchronized void decrement() {
		void decrement() {
			//value--;
			value.decrementAndGet();
		}

		public int getValue() {
			//return value;
			return value.get();
		}

		public void setValue(int value) {
			//this.value = value;
			this.value.set(value);
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
