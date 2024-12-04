package org.example;

public class MultiDimensionalArrayExample {
		public static void main(String[] args) {
				System.out.println("MultiDimensionalArrayExample Started");

				twoDimensionalArray();

		}

		private static void twoDimensionalArray(){
				int[][] a = new int[2][2];

				a[0][0] = 1;
				a[0][1] = 2;
				a[1][0] = 3;
				a[1][1] = 4;

				System.out.println("Array int[][] a filled");

				try{
						a[0][2] = 4;

				}catch (Exception e){
						System.out.println("Not able to add element at a[0][2] in array int[2][2].");
				}

				int[][] b = new int[2][];

				b[0] = new int[3];
				b[1] = new int[1];
				b[0][0] = 1;
				b[0][1] = 2;
				b[0][2] = 3;
				b[1][0] = 4;
				System.out.println("Arrays int[][] b filled. Able to insert element at b[0][2] ");
		}
}
