package ua.itstep.shaptala.inheritance.examples;

public class InheritanceClass {

	int someData;
	
	{  // ������������� ���� �������������
		someData = 100;
	}
	
	static final int someSataticData;
	static int n = 100; 
	static { // ����������� ���� �������������
		someSataticData = 200;
	}
	
	int some;
	
	// ����������� �� ��������
	public InheritanceClass() {
		this(100); // ����� ������������ � �����������
	}
	
	// ����������� � �����������
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
			System.out.println("��� �� Dog");
		}
		animal.Say();
		
		animal = dog;
		// downcasting
		System.out.println(((Dog)animal).lengthTail());
		
		Animal cat = new Cat("�����");
		
		Animal[] zoo = new Animal[3];
		zoo[0] = new Cat("������");
		zoo[1] = new Dog("�����");
		zoo[2] = cat; 
		for(Animal a: zoo) {
			System.out.println(a.getName());
		}
	}
}
