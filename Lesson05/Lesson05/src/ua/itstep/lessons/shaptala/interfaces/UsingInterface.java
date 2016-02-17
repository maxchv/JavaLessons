package ua.itstep.lessons.shaptala.interfaces;

public class UsingInterface {
	public static void main(String[] args) {
		
		Dog dog = new Dog("Bobic", 5);
		System.out.println(dog);	
		dog.newRaptorMethod();
		
		System.out.println(dog.environment());
		
		Animal animal = new Bird(); // интерфейсна€ ссылка
		System.out.println(Animal.numberOfAnimals);
		animal.isHungry();
		animal.isAlive();
		
		if(animal instanceof Mammal) {		
			Mammal mammal = (Mammal) animal;			
			System.out.println(mammal.environment());
		}
		animal.Method();
		
		Animal.StaticMethod();
	}
}

interface Animal {
	int numberOfAnimals = 10;	// static public final
	boolean isAlive(); 			// abstract public
	boolean isHungry();
	static public void StaticMethod() {
		System.out.println("Static Method Animal");
	}
	//void Method();
	default void Method() {     // public
		System.out.println("New Method in Animal");
	}	
}

interface FlyAnimal extends Animal {
	default void Method() {     // public
		System.out.println("New Method in FlyAnimal");
	}
}

class Bird implements FlyAnimal {

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHungry() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void Method() {     // public
		System.out.println("New Method in Bird");
	}
	
}

interface Mammal extends Animal, Raptor {
	String environment();
}

interface Raptor {
	default void newRaptorMethod() {
		System.out.println("Raptor method");
	}
}

class Dog implements Mammal, Raptor {	
	private String name;
	private int age;
	
	
	public void SomeMethod() {
		
	}
		
	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean isAlive() {		
		return false;
	}

	@Override
	public boolean isHungry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String environment() {
		// TODO Auto-generated method stub
		return "ƒнепр";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {	
		return "name: " + name + " age: " + age;
	}
}


