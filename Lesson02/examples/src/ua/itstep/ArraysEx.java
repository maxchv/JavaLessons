package ua.itstep;

import java.util.Arrays;

public class ArraysEx {
	public static void main(String[] args) {
		
		int[] arr1d = {1,2,3,4,5};
		int[][] arr2d = {{1,2,3}, {4,5,6}};
		//Arrays.fill(arr1d, 100);
		System.out.println(Arrays.toString(arr1d));
		System.out.println(Arrays.deepToString(arr2d));
		
		// копирование массива. Способ 1
		int[] copy = new int[arr1d.length];
		System.arraycopy(arr1d, 0, copy, 0, arr1d.length);
		System.out.println(Arrays.toString(copy));

		// копирование массива. Способ 2
		int[] copy2 = Arrays.copyOfRange(arr1d, 0, arr1d.length);
		System.out.println(Arrays.toString(copy2));
		
		// сортировка
		int[] arr = {2,5,1,4,5};
		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		// сравнение массивов
		System.out.println(Arrays.equals(copy, copy2));
		
		// поиск индекса элемента в массиве
		int idx5 = Arrays.binarySearch(arr, 5);
		System.out.println(idx5);
		
		// Написать программу, которая расчитает среднегодовую температуру 
		// (среднюю, максимальную и минимальную), по предоставленым данным. 
		// Найти два месяца с максимальным перепадом температур.
		double[][] temp = {
				{-1.7, -1, 2.6,  9, 15.1, 19.4, 21.4, 21.2, 17.1, 11.1, 5.9, 1.4},
				{   1,  1,   5, 12,   19,   24,   26,   26,   21,   15,   8,   4},
				{  -4, -4,   0,  6,   12,   16,   18,   17,   13,    8,   3,  -1}
		};
		// среднегодовя температура (средняя, максимальная,  минимальная)
		double[] aver_temp = new double[temp.length];
					
		for(int i=0; i<temp.length; i++) {
			for(int j=0; j<temp[i].length; j++) {
				aver_temp[i] += temp[i][j];
			}			
		}
		
		for(int i=0; i<temp.length; i++) {
			aver_temp[i] /= temp[i].length;
		}
		
		System.out.println(Arrays.toString(aver_temp));
		
		double min = temp[1][0] - temp[2][0];
		double max = min;
		
		int min_idx = 0;
		int max_idx = 0;
		
		double cur;
		
		for(int i=0; i<temp[1].length; i++) {
			cur = temp[1][i] - temp[2][i];
			if(cur < min) {
				min = cur;
				min_idx = i;
			}
			if(cur > max) {
				min = cur;
				max_idx = i;
			}
		}
		
		System.out.println("min: " + min_idx + " max: " + max_idx);
	}
}
