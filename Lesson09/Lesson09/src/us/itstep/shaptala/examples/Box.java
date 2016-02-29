package us.itstep.shaptala.examples;

import java.time.LocalDate;

public class Box<T extends Products> {

	private T value;

	public Box(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {	
		return value.toString();
	}

	public static void main(String[] args) {
		Box<Tea> box1 = new Box<>(new Tea("Black tea", 25));
		Box<Coffee> box2 = new Box<>(new Coffee("Brazil", 100));
//		Box<Money> box3 = new Box<>(new Money());
//		Box<Integer> box4 = new Box<>(new Integer(10));
//		Box<Object> box5 = new Box<>(new Boolean(true));

		Tea tea = (Tea) box1.getValue();
		tea.setCount(tea.getCount() - 1);
		System.out.println(box1.getValue());

//		Money money = box3.getValue();
		
//		Box box6 = new Box(10);
//		Integer i = (Integer)box6.getValue();
		
		Products prod = new Products("Unknown");
		Tea lipton = new Tea("Lipton", 25);
		prod = lipton;
		
		Box<Products> box7 = new Box<>(new Products("Some products"));
		Box<Tea> box8 = new Box<>(new Tea("", 0));
		//box7 = box8; Ошибка
		Object obj = box8;
		
		printProducts(box7);
		printProducts(box8);
		
//		printProducts(box6);
		
		List<Box<Tea>> lst = new List<>(new Box(new Tea("", 0)));
		
		// autoboxing упаковка
		Integer num = 10;//Integer.valueOf(10);
		// unboxing распаковка
		int n = num; //num.intValue();
		
		List<Integer> intlst = new List<>(10);
		
		Number n1 = 0; 
		Class<?> c = intlst.getClass();
		System.out.println(c.getName());
		
		ObjecList ol = new ObjecList(new Coffee("StarBuks", 100));
		List<Coffee> gl = new List<>(new Coffee("StarBuks", 100));
		
		Coffee cof = gl.getValue();
		cof = (Coffee)ol.getValue();
	}
	
	static void printProducts(Box<?> prod){		
		Products p = prod.getValue();
		System.out.println(prod);
	}
}

interface iMoney {
	
}

class ObjecList {
	Object value;
	ObjecList(Object obj) {
		value = obj;
	}
	Object getValue() {
		return value;
	}
}

class List<T> {	
	T value;
	List(T obj) {
		value = obj;
	}
	T getValue() {
		return value;
	}
}

class Products {
	protected String name;
	
	public Products(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

class Coffee extends Products {
	
	private int count;

	public Coffee(String name, int count) {
		super(name);
		this.setCount(count);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return super.toString() + " " + count;
	}
}

class Tea  extends Products {
	private int count;

	public Tea(String name, int count) {
		super(name);
		this.setCount(count);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return super.toString() + " " + count;
	}
}

class Money implements iMoney {

}
