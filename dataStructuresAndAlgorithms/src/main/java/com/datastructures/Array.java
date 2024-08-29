package com.datastructures;

public class Array {
		public static void main(String[] args) {
				//Declaring array
				int[] n;

				//Initializing an array. Tells JVM to allocate memory for 5 integers.
				n = new int[5];

				for(int i=0 ; i<5 ; i++) {
						n[i] = i;
				}

				System.out.print("Elements of an array n are : [");

				for(int i=0 ; i<5 ; i++) {
						System.out.print(n[i]);

						if(i != 4)
						System.out.print(",");
				}

				System.out.print("]");

		}
}
