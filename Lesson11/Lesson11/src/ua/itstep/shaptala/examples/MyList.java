package ua.itstep.shaptala.examples;

import java.util.AbstractList;

public class MyList<T> extends AbstractList<T> {

	public static void main(String[] args) {
		MyList<Integer> list = new MyList<>(new Integer[]{1,2,3,4});
		for(Integer item: list) {
			System.out.println(item);
		}	
	}
	
	private T[] array;
	public MyList(T[] arr) {
		array = arr;
	}

	@Override
	public T get(int idx) {
		return array[idx];
	}
	
	@Override
	public T set(int index, T element) {	
		return array[index] = element;
	}

	@Override
	public int size() {		
		return array.length;
	}

}
