package ua.itstep.shaptala.examples.io;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		//exampleDataInputOutput();
		//printWtirerExample();
		//scannerReadExample();
 	}

	private static void scannerReadExample() {
		try(Scanner scanner = new Scanner(new InputStreamReader(new BufferedInputStream(new FileInputStream("hello.txt")),"UTF8"))) {
			while(scanner.hasNext()) {
				System.out.println(scanner.next());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printWtirerExample() {
		try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("hello.txt")), "UTF8"))) {
			writer.println("Здравствуй Мир!!!");
			writer.format("%20s%20s", "Первое слово", "Второе слово");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void exampleDataInputOutput() {
		Integer[] arrInt = {1,2,3,4,5};
		Double[] arrDouble = {1.0,2.0,3.4,5.0, 1d};
		String[] arrString = {"Один", "Два", "Три", "Четыре", "Пять" };
		
		writeData(arrInt, arrDouble, arrString);
		reasData(arrInt, arrDouble, arrString);
	}

	private static void reasData(Integer[] arrInt, Double[] arrDouble, String[] arrString) {
		Integer[] cpyInt = new Integer[5];
		Double[] cpyDouble = new Double[5];
		String[] cpyString = new String[5];
		try(DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")))){
			for(int i=0; i<cpyInt.length; i++) {
				cpyInt[i] = input.readInt();
				cpyDouble[i] = input.readDouble();
				cpyString[i] = input.readUTF();
			}
			System.out.println(Arrays.toString(cpyInt));
			System.out.println(Arrays.equals(arrInt, cpyInt));
			System.out.println(Arrays.toString(cpyDouble));
			System.out.println(Arrays.equals(arrDouble, cpyDouble));
			System.out.println(Arrays.toString(cpyString));
			System.out.println(Arrays.equals(arrString, cpyString));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	private static void writeData(Integer[] arrInt, Double[] arrDouble, String[] arrString) {
		try(DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.txt")))){			
			for(int i=0; i<arrInt.length; i++) {
				output.writeInt(arrInt[i]);
				output.writeDouble(arrDouble[i]);
				output.writeUTF(arrString[i]);
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
