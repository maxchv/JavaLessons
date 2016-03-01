package ua.step.example;

public class Task07 {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		for (int i = 0; i < 10; i++) {
			MyThread t1 = new MyThread(counter, i);
			Thread t = new Thread(t1);
			t.start();
		}
		Thread.sleep(5000);
		System.out.println(counter.getCount());
	}
}

class Counter {
	private /*volatile*/ long count;

	public void increment() {
		count++;
	}

	public void decrement() {
		count--;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long l) {
		count = l;
	}
}

class MyThread implements Runnable {
	private Counter counter;

	private int id;

	public MyThread(Counter counter, int id) {
		this.counter = counter;
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Thread id = " + id + " start");
		for (int i = 0; i < 100; i++) {
			long count = counter.getCount();
			if (id % 2 == 0) {
				counter.setCount(++count);
			}
			if (id % 2 == 0) {
				counter.setCount(--count);
			}
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Thread id = " + id + " end");
	}
}