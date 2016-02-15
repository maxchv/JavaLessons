package ua.itstep.shaptala.examples;

public class Car extends Object {
	private String name;
	private String color;
	private int price;
	
	// констант
	static final String type;
	
	static { // для инициализации статических полей
		type = "Car";
	}
	
	public Car(String name, String color, int price) {
		this.setName(name);
		this.color = color;
		this.price = price;
	}	
	
	// Перегрузка метода базовго класса Object
	@Override
	public String toString() {		
		return getName() + " " + color + " $" + price;
	}

	int	getDiscount() {		
		return (int)(price*0.05);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
