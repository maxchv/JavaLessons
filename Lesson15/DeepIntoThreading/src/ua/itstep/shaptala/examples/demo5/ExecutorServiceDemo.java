package ua.itstep.shaptala.examples.demo5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
	public static class Worker implements Callable<String> {

		final String name;
		
		
		public Worker(String string) {
			name = string;
		}


		@Override
		public String call() throws Exception {
			long sleepTime = (long) (Math.random()*10000L);
			System.out.println(name + " started, going to sleep for " + sleepTime + " seconds");
			Thread.sleep(sleepTime);
			System.out.println(name + " finished");
			return name;
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		System.out.println("Submit worker 1");		
		Future<String> future1 =  executor.submit(new Worker("Worker 1"));
		
		System.out.println("Submit worker 1");
		Future<String> future2 =  executor.submit(new Worker("Worker 2"));
		
		System.out.println("Result of worker 1: " + future1.get());
		System.out.println("Result of worker 2: " + future2.get());
		
		System.out.println("---------------------");
		
		System.out.println("Submit workers using invoke All");
		
		List<Future<String>> futures = executor.invokeAll(Arrays.asList(new Worker("Worker 3"), new Worker("Worker 4")));
	
		System.out.println("Exited invokeAll");
		
		for(Future<String> future: futures) {
			System.out.println("Result from worker: " + future.get());
		}
		
		executor.shutdown();
		
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}
}
