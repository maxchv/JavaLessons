package ua.itstep;

public class Palindrome {

	public static void main(String[] args) {
		String str = "Аргентина манит негра.";
		
		str = str.replaceAll("[\\s\\.!*,:;]", "").toLowerCase();
		StringBuffer buf = new StringBuffer(str);		
		if(str.equals(buf.reverse().toString())) {
			System.out.println("Это палиндром");
		} else {
			System.out.println("Это Не палиндром");
		}
	}
}
