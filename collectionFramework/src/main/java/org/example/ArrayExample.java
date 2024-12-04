package org.example;

import java.util.ArrayList;

public class ArrayExample {
		public static void main(String[] args) {
				System.out.println("ArrayExample Example");

				int[] a = new int[10];
				a[0] = 0;

				System.out.println("Class name for a[] : "+a.getClass().getName());
				System.out.println("Size of array : "+a.length);


				int[] b = new int['a'];
				b[0] = 0;
				System.out.println("Size of array with char size : "+b.length);

				Object[] c = new Object[4];
				System.out.println("Object array type : "+ c.getClass().getName());


		}
}
