package ua.itstep.shaptala.example;

public class UsingEnums {

	public static void main(String[] args) {
		Month curMonth = Month.FEB;
		for(Month m: Month.values()) {
			System.out.println(m + ": " +m.getDays() + " " +  m.ordinal());
		}
	}

}

enum Month {
	JAN (31),
	FEB (28),
	MAR (31),
	AIPR (30);
	
	private int days;
	
	Month(int days) {
		this.setDays(days);
	}
	
	public boolean isLeap() {
		return true;
	}

	public int getDays() {
		return days;
	}

	private void setDays(int days) {
		this.days = days;
	}
		
	@Override
	public String toString() {		
		return super.toString() + " info";
	}
}
