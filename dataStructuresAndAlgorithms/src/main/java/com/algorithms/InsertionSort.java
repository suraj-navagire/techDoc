package com.algorithms;

public class InsertionSort {

		public static void main(String[] args) {
				System.out.println("InsertionSort Started");

				int[] array = new int[]{8,3,9,23,4};

				insertionSort(array);

				for(int a : array){
						System.out.print(a+" ");
				}

				System.out.println("InsertionSort Ended");
		}


		private static void insertionSort(int[] array){
				int n = array.length;

				//In ith iteration we sort 0 to i elements. In ith iteration we find correct position for i from 0 to i.
				for(int i=0;i<n;i++){
						int sortedIndex = i;
						for(int j=i;j>0;j--){
								if(array[sortedIndex] < array[j-1]){
										int temp = array[j];
										array[j] = array[j-1];
										array[j-1] = temp;
										sortedIndex = j-1;
								}
						}
				}

		}
}
