package ua.itstep.shaptala.examples;

public class Demo1 {

	static class HelloRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("Hello from " + Thread.currentThread().getName());			
		}		
	}
	
	
	static class HelloThread extends Thread {
		@Override
		public void run() {
			System.out.println("Hello from " + getName());
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			//new HelloThread().start();
			//new Thread(new HelloRunnable()).start();
			new Thread(() -> {
				System.out.println("Hello from" + Thread.currentThread().getName()); 
				try {
					Thread.sleep(10000L);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}).start();
		}
		System.out.println("Hello from main thread");
	}

}
