package ua.itstep.shaptala.examples;

public class IntroClasses {

	public static void main(String[] args) {		
		System.out.println(Car.type);
		//Car.type = "Bicycle";		
		Car ferarri = new Car("Ferrari", "Red", 100000);
		System.out.println(ferarri.type);
		System.out.println(ferarri.toString());
		System.out.println(ferarri.getName());
		System.out.println(ferarri.getDiscount());
	}
}
