package ua.itstep.shaptala.inheritance.examples;



public class UsingMethods {

//	public static int sum(int[] numbers) {
//		int s = 0;
//		for(int num: numbers) {
//			s += num;
//		}
//		return s;
//	}
	
	// переменнное количество аргументов
	public static int sum(int... numbers) {
		int s = 0;
		for(int num: numbers) {
			s += num;
		}
		return s;
	}
	
	private static void printArray(int[] arr, int idx) {
		if(idx >= 0) {
			System.out.println(arr[idx] + " ");			
			printArray(arr, idx-1);
		} 
	}
	
	public static void main(String[] args) {
		System.out.println("sum = " + sum());
		System.out.println("sum = " + sum(1,2));
		System.out.println("sum = " + sum(1,2,3,4));
		
		printArray(new int[]{1,2,3,4,5,6,7,8,9,10}, 9);		
	}
}
