package ua.itstep.shaptala.examples;

public class ThreadDemo1 {

	// TODO: implements Runnable
	
	// TODO: extends Thread
	
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			// TODO: Run threads here
			System.out.println("Runnable right here " + Thread.currentThread().getName());
			
		}
		System.out.println("Hello from main thread");
	}
}
