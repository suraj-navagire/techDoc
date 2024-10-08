package com.algorithms;

public class SelectionSort {
		public static void main(String[] args) {
				System.out.println("SelectionSort Started");

				int[] array = new int[]{8,3,9,23,4};

				selectionSort(array);

				for(int a : array){
						System.out.print(a+" ");
				}
				System.out.println("SelectionSort Ended");
		}

		private static void selectionSort(int[] array){

				int n = array.length;

				for(int i=0;i<n;i++){
						int smallestIndex = i;
						//j will start from i+1 as we are taking i as smallest
						for(int j=i+1;j<n;j++){
								if(array[smallestIndex] > array[j]){
										smallestIndex = j;
								}
						}

						int temp = array[i];
						array[i] = array[smallestIndex];
						array[smallestIndex] = temp;
				}
		}
}
