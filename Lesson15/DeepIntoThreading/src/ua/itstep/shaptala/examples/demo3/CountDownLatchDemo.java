package ua.itstep.shaptala.examples.demo3;

import java.util.concurrent.CountDownLatch;



public class CountDownLatchDemo {

	private static class DemoThread extends Thread {

		private final CountDownLatch latch;

		public DemoThread(CountDownLatch latch) {
			this.latch = latch;
		}
		
		@Override
		public void run() {
			try {
				runUnsafe();
			} catch(InterruptedException e) {
				System.out.println(getName() + " interrupted");
			}
		}

		private void runUnsafe() throws InterruptedException {
			// Initialisation phase
			Thread.sleep((long)Math.random() * 10000);
			
			System.out.println(getName() + " finished initialization");
			
			latch.countDown();
			latch.await();
			
			System.out.println(getName() + " entered main phase");
			
			// main phase
			Thread.sleep((long) Math.random() * 10000);
			System.out.println("End main phase for " + getName());
		}
	}

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		
		for(int i=0; i<10; i++) {
			new DemoThread(latch).start();
		}
	}

}
