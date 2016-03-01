package ua.itstep.shaptala.examples;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Application {

	public static long testSpeed(List<Integer> list, int n) {
		Instant start = Instant.now();
		for (int i = 0; i < n; i++) {
			list.add(new Integer(i));
		}		
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}
		Instant end = Instant.now();
		return Duration.between(start, end).getNano();
	}

	static class BenchmarkList {
		static int from = 1000;
		static int to   = 100000;
		static int step = 10;
		List<? super Integer> list;
		
		Map<Integer, Integer> results = new TreeMap<>();
		
		public BenchmarkList(List<? super Integer> list) {
			this.list = list;
		}
		
		void run() {
			for(int i=from; i<=to; i*=step) {
				iter(i);
			}
		}
		
		void iter(int n) {
			Instant start = Instant.now();
			for (int i = 0; i < n; i++) {
				list.add(new Integer(i));
			}		
			Iterator<?> iter = list.iterator();
			while (iter.hasNext()) {
				iter.next();
				iter.remove();
			}
			Instant end = Instant.now();
			results.put(n, Duration.between(start, end).getNano());
		}
		
		@Override
		public String toString() {
			
			return super.toString();
		}
		
		static void printResults(List<BenchmarkList> bl) {
			System.out.print(String.format("%8s", "N"));
			for(BenchmarkList b: bl) {
				System.out.print(String.format("%12s",b.list.getClass().getSimpleName()));
			}
			if(bl.size() > 1){
				System.out.print(String.format("%12s", bl.get(0).list.getClass().getSimpleName())
					                               + "/" + bl.get(1).list.getClass().getSimpleName());
			}
			System.out.println();
			for(int i=BenchmarkList.from; i<=BenchmarkList.to; i*=BenchmarkList.step) {
				System.out.print(String.format("%8d",i));
				for(BenchmarkList b: bl) {
					System.out.print(String.format("%12d",b.results.get(i)));
				}
				if(bl.size() > 1) {
				System.out.print(String.format("%15g", (double)bl.get(0).results.get(i) /
													   bl.get(1).results.get(i)));				
				}
				System.out.println();
			}
		}
	}
		
	public static void main(String[] args) {		
		List<BenchmarkList> benchmarks = new ArrayList<>(); 
		benchmarks.add(new BenchmarkList(new ArrayList<>()));
		benchmarks.add(new BenchmarkList(new LinkedList<>()));
		
		for(BenchmarkList b: benchmarks) {
			b.run();
		}
		BenchmarkList.printResults(benchmarks);
	}

}
