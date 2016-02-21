package ua.itstep.shaptala.examples;

public class GarbageCollector {
	
	Object [] arr;
	
	public GarbageCollector() {
		arr = new Object[100];
	}
	
	

	public static void main(String[] args) {
		
		GarbageCollector gc = new GarbageCollector();
		
		gc.toString();
		
		System.out.println(gc);
	}

}
