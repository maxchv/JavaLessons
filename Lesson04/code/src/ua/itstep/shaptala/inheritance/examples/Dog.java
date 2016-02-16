package ua.itstep.shaptala.inheritance.examples;

public class Dog extends Animal {

	private String name;
	
	public Dog(String name) {
		super(10);
		setName(name);
		System.out.println("Constructor class Dog...");
	}
	
	@Override
	public void Say() {
		System.out.println("Gav Gav");
	}
	
	public int lengthTail() {
		return 5;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String n) {		
		name = n;
	}

}
