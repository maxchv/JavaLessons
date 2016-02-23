package ua.itstep.shaptala.examples;

import java.io.IOException;
import java.util.Scanner;

public class UsingExceptions {

	static Scanner in = new Scanner(System.in);
	
	public static void foo()  {
		int number;
		try{
			number = 0;	//in.nextInt();
			
//			if(number == 0){
//				throw new Throwable("Деление на ноль запрещено");
//			} else {
				System.out.println(1/number);
				System.out.println("выполнится");
//			}
			//System.exit(0);
				
		} catch(ArithmeticException ex) {
			//System.err.println(ex.getMessage());
			//ex.printStackTrace();
			
		} finally {		
			System.out.println("Гарантированно выполнится");
			//return 0;
		}
	}
	
	public static void getNumeric(Object obj) throws Exception{
		// Checked exception
		if(!(obj instanceof Number)) {
			throw new Exception("Object должен быть Number");
		}
		
		Number num = (Number)obj;
		
		if(num.intValue() < 0) {
			throw new NegativeNumberException("Число должено быть положительным");
		}
		
		System.out.println(num.intValue());		
	}
	
	public static void boo() {
		// Unchecked exception
		throw new Error("Error");
	}
	
	public static void f() throws IOException {
		
	}
	
	public static void h() throws Exception {
		throw new Exception("Первое исключение");
	}
	
	public static void d() throws Exception {
		try{
			h();
		} catch(Exception ex) {
			throw new Exception("Второе исключение", ex);
		}
		
	}
	
	public static void main(String[] args) {
//		try {
//			getNumeric(10);
//			//getNumeric("String");
//			getNumeric(-1);
//		} catch (NegativeNumberException ex) {
//			System.out.println("Не должно быть 0");
//		} catch (Exception e) {			
//			e.printStackTrace();
//		}	
//		boo();
//		try {
//			f();
//		} catch (Throwable e) {			
//			e.printStackTrace();
//		}
//		try {
//			d();
//		} catch (Exception e) {			
////			e.printStackTrace();
//			for(StackTraceElement ex: e.getStackTrace()) {
//				System.err.println(ex);
//			}
//		}
		for(int i = 0; i < 10; i++) {
			System.out.println("Не ошибка");
			System.err.println("Ошибка");
		}
		
	}
}

class NegativeNumberException extends Exception {
	public NegativeNumberException(String string) {
		super(string);
	}	
}
