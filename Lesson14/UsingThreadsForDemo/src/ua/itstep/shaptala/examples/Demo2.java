package ua.itstep.shaptala.examples;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		Thread worker = new WorkedThread();

		Thread sleeper = new SleeperThread();
//		демонезируем
		
		System.out.println("Starting threads");
		
		worker.start();
		sleeper.start();
		
		Thread.sleep(100L);
		
//		System.out.println("Interrupting threads");

		
//		System.out.println("Joining threads");

		System.out.println("All done");				
	}
	
	private static class WorkedThread extends Thread {		
		@Override
		public void run() {
			long sum = 0;
			for(int i=0; i<2_000_000_000; i++) {
				sum +=i;
				if(i%100 == 0 && isInterrupted()) {
					System.out.println("Loop interrupted at i = " + i);
					break;
				}
			}
			System.out.println("End loop for thread : " + getName());
		}
	}
	
	private static class SleeperThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1_000L);
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted");
			}
			System.out.println("End sleeper thread: " + getName());
		}
	}
}
