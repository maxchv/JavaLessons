package ua.itstep.shaptala;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSumDemo {
	public static void main(String[] args) {
		
		try {
			String test = "Это тестовое сообщение";
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] dig = md.digest(test.getBytes());
			for(byte b: dig) {
				System.out.format("%x", b);
			}
			System.out.println();
			
			String test2 = "Это тестовое сообщение";
			md.reset();
			byte[] dig2 = md.digest(test2.getBytes());
			for(byte b: dig2) {
				System.out.format("%x", b);
			}
			
			System.out.println();
			
			if(Arrays.equals(dig, dig2)) {
				System.out.println("Equals");
			} else {
				System.out.println("Not equals");
			}
				
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} 

	}

}
