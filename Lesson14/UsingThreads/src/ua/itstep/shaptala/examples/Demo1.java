package ua.itstep.shaptala.examples;

public class Demo1 {

	static class HelloRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("Hello from runnable " + Thread.currentThread().getName() + " " + 
								Thread.currentThread().getId());
		}
		
	}
	
	static class HelloThread extends Thread {
		@Override
		public void run() {
			System.out.println("Hello from thread " + getName() + " " + getId());
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			//new HelloThread().start();
			
			//new Thread(new HelloRunnable()).start();
			
//			new Thread() {
//				@Override
//				public void run() {
//					System.out.println("run right here " + getName());
//				};
//			}.start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("Runnable right here " + Thread.currentThread().getName());
				}
			}).start();
		}
		System.out.println("Hello from main thread");
	}
}
