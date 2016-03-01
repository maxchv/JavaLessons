package ua.itstep.shaptala.examples;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Application {

	
	static class BenchmarkList {
		static int from = 1000;
		static int to   = 100000;
		static int step = 10;
		List<? super Integer> list;
		static Random rnd = new Random();
		
		Map<Integer, Integer> results = new TreeMap<>();
		
		void clear() {
			results.clear();
			list.clear();
		}
		
		public BenchmarkList(List<? super Integer> list) {
			this.list = list;
		}
		
		void runCreateRemove() {
			clear();
			for(int i=from; i<=to; i*=step) {
				iterCreateRemove(i);				
			}
		}
		
		void runRandomAccess() {
			clear();
			for(int i=from; i<=to; i*=step) {				
				iterRandomGet(i);
			}
		}
		
		void iterRandomGet(int n) {
			Instant start = Instant.now();
			createList(n);		
			randomAccess();
			Instant end = Instant.now();
			results.put(n, Duration.between(start, end).getNano());
		}

		private void randomAccess() {
			for(int i=0; i<list.size(); i++) {
				Object tmp = list.get(rnd.nextInt(list.size()));
			}
		}
		
		void iterCreateRemove(int n) {
			Instant start = Instant.now();
			createList(n);		
			removeAllItem();
			Instant end = Instant.now();
			results.put(n, Duration.between(start, end).getNano());
		}

		private void removeAllItem() {
			Iterator<?> iter = list.iterator();
			while (iter.hasNext()) {
				iter.next();
				iter.remove();
			}
		}

		private void createList(int n) {
			for (int i = 0; i < n; i++) {
				list.add(new Integer(i));
			}
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
		
		System.out.println("Create/Remove");
		for(BenchmarkList b: benchmarks) {
			b.runCreateRemove();
		}		
		BenchmarkList.printResults(benchmarks);
						
		System.out.println("Random Access");
		for(BenchmarkList b: benchmarks) {
			b.runRandomAccess();
		}		
		BenchmarkList.printResults(benchmarks);
	}

}
