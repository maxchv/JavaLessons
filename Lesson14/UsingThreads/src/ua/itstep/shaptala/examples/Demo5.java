package ua.itstep.shaptala.examples;

public class Demo5 {

	public static void main(String[] args) {
		
	}

}

class Singleton {
	private int foo;
	private String bar;

	private static Singleton instance = null;
	
	private Singleton() {
		foo = 13;
		bar = "zap";
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}