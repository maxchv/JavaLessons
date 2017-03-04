package ua.itstep.shaptala.examples;

public class InterruptDemo2 {

	private static class WorkedThread extends Thread {		
		@Override
		public void run() {
			System.out.println("Start working thread : " + getName());
			long sum = 0;
			for(int i=0; i<2_000_000_000; i++) {
				sum +=i;
				
				if(i%100 == 0 && isInterrupted()) {
					System.out.println("Loop interrupted at i = " + i);
					break;
				}
			}
			System.out.println("End working thread : " + getName());
		}
	}
	
	private static class SleeperThread extends Thread {
		@Override
		public void run() {
			System.out.println("Start sleeper thread: " + getName());
			try {
				Thread.sleep(1_000L);
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted");
			}
			System.out.println("End sleeper thread: " + getName());
		}
	}
	
	public static void main(String[] args) throws Exception {
		Thread worker = new WorkedThread();
		Thread sleeper = new SleeperThread();
		
		// TODO: Daemon threads
//		System.out.println("Set daemon threads");

		
		// TODO: Starting threads
//		System.out.println("Starting threads");		
		
//		Thread.sleep(100L);
		
		// TODO: Interrupting threads
//		System.out.println("Interrupting threads");

		
		// TODO: Joining threads
//		System.out.println("Joining threads");

		
		System.out.println("All done");				
	}
	

}
