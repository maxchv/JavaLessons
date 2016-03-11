package ua.itstep.shaptala.examples.demo6;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

public class ForkJoinPoolDemo {

	
	public static class RecurciveCalc extends RecursiveTask<Double> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8661015621105069784L;

		private final int[] array;
		private final int start;
		private final int end;
		
		private static final int SEQUENTIAL_TRESHOLD = 50_000;		
		
		public RecurciveCalc(int[] t, int i, int length) {
			this.array = t;
			this.start = i;
			this.end = length;
		}

		@Override
		protected Double compute() {
			if(end - start <= SEQUENTIAL_TRESHOLD) {
				return calculate(array, start, end);
			} else {
				int mid = (start + end)/2;
				RecurciveCalc left = new RecurciveCalc(array, start, mid);
				RecurciveCalc right = new RecurciveCalc(array, mid, end);
				invokeAll(left, right);
				return left.join() + right.join();
			}			
		}
	}

	public static int[] prepareArray() {
		int[] array = new int[20_000_000];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		return array;
	}

	public static double calculate(int[] array) {
		return calculate(array, 0, array.length);
	}

	public static double calculate(int[] array, int start, int end) {
		double num = 0;
		for (int i = start; i < end; i++) {
			num += function(array[i]);
		}
		return num;
	}

	private static double function(int i) {
		return Math.sin(i);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Sequence calculation");
		baseCalculation(ForkJoinPoolDemo::calculate);

		System.out.println("\n\nExecutorService calculation");
		ExecutorService exec = Executors.newFixedThreadPool(2);
		baseCalculation((t) -> {
			Future<Double> f1 = exec.submit(() -> calculate(t, 0, t.length/2));
			Future<Double> f2 = exec.submit(() ->  calculate(t, t.length/2, t.length));

			Double res = 0.0;
			try {
				res = f1.get() + f2.get();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			return res;
		});
		exec.shutdown();		

		System.out.println("\n\nForkJoinPool calculation");
		ForkJoinPool pool = new ForkJoinPool();
		baseCalculation((t) -> {
			Double res = pool.invoke(new RecurciveCalc(t, 0, t.length));
			return res;
		});
		pool.shutdown();
		
		System.out.println("\n\nParralel Stream");
		baseCalculation((t) -> {			
			return Arrays.stream(t).
					parallel().
					mapToDouble(ForkJoinPoolDemo::function).
					sum();					
		});
	}

	private static void baseCalculation(Function<int[], Double> calc) {
		int[] array = prepareArray();
		System.out.println("Start calculation");
		Instant start = Instant.now();

		double sum = calc.apply(array);

		Instant end = Instant.now();

		System.out.println("Sum: " + sum);
		System.out.println("Time: " + start.until(end, ChronoUnit.MILLIS) + " ms");
	}
}
