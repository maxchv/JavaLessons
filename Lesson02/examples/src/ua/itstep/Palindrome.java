package ua.itstep;

public class Palindrome {

	public static void main(String[] args) {
		String str = "��������� ����� �����.";
		
		str = str.replaceAll("[\\s\\.!*,:;]", "").toLowerCase();
		StringBuffer buf = new StringBuffer(str);		
		if(str.equals(buf.reverse().toString())) {
			System.out.println("��� ���������");
		} else {
			System.out.println("��� �� ���������");
		}
	}
}
