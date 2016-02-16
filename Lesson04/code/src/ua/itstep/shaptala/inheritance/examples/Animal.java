package ua.itstep.shaptala.inheritance.examples;

abstract class Animal {
	
	private String name;
	
	public Animal() {
		setName("Animal");
		System.out.println("Constructor Animal...");
	}
	
	public Animal(int somedata) {
		
	}
	
	public abstract void Say();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
