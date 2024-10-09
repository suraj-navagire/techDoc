package com.algorithms;

public class BinarySearch {

		public static void main(String[] args) {

				System.out.println("BinarySearch Start");

				int[] a = new int[]{2,6,9,12,56,190};
				boolean isPresent = binarySearch(a, 0, 5, 56);

				System.out.println("Is 56 part of array : "+ isPresent);

				isPresent = binarySearch(a, 0, 5, 98);

				System.out.println("Is 98 part of array : "+ isPresent);

				System.out.println("BinarySearch End");
		}

		private static boolean binarySearch(int[] a, int low, int high, int element){
				if(low > high){
						return false;
				}

				int n = low + (high-low)/2;

				if(a[n] == element){
						return true;
				} else if(element < a[n]){
						return binarySearch(a, low, n-1, element);
				} else {
						return binarySearch(a, n+1, high, element);
				}
		}
}
