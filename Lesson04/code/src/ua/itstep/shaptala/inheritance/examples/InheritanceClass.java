package ua.itstep.shaptala.inheritance.examples;

public class InheritanceClass {

	int someData;
	
	{  // нестатический блок инициализации
		someData = 100;
	}
	
	static final int someSataticData;
	static int n = 100; 
	static { // статический блок инициализации
		someSataticData = 200;
	}
	
	int some;
	
	// Конструктор по умоланию
	public InheritanceClass() {
		this(100); // вызов конструктора с параметрами
	}
	
	// конструктор с параметрами
	public InheritanceClass(int initSome) {
		some = initSome;
	}
	
	public static void main(String[] args) {
		//InheritanceClass obj = new InheritanceClass();
		//obj.some = 10;
		
		Dog dog = new Dog("Boss");
		dog.Say();
		
		System.out.println(dog.lengthTail());
		
		Animal animal = dog;// = new Animal();
		if(animal instanceof Dog) {
			System.out.println(((Dog)animal).lengthTail());
		} else {
			System.out.println("Это не Dog");
		}
		animal.Say();
		
		animal = dog;
		// downcasting
		System.out.println(((Dog)animal).lengthTail());
		
		Animal cat = new Cat("Мурка");
		
		Animal[] zoo = new Animal[3];
		zoo[0] = new Cat("Васька");
		zoo[1] = new Dog("Бобик");
		zoo[2] = cat; 
		for(Animal a: zoo) {
			System.out.println(a.getName());
		}
	}
}
