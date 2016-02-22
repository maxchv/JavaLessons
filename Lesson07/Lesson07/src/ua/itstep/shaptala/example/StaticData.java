package ua.itstep.shaptala.example;

public class StaticData {

	public static void main(String[] args) {		
		Goods motobike = new Goods("Harley Davidson");
		Goods m = motobike;
		//motobike = null;
		//m = null;
		System.out.println(motobike.getId());
		Goods mers = new Goods("Mersedes LX");
		System.out.println(mers.getId());
		Goods goods = new Goods("Something");
		System.out.println(goods.getId());
		
		System.out.println(goods);
		System.out.println(goods.getClass().getName() + "@" + Integer.toHexString(goods.hashCode()));
		goods = null;
		System.gc();
	}
}

class Goods {
	static private int id = 0;
	private String name;
	
	Goods(String name) {
		this.name = name;
		setId(getId() + 1);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		Goods.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Name: " + name;
	}
	
	@Override
	public void finalize() {
		System.out.println(name + " is sold");
	}
	
}

