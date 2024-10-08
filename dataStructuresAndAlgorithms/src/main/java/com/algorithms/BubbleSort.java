package com.algorithms;

public class BubbleSort {

		public static void main(String[] args) {
				System.out.println("BubbleSort Started");

				int[] array = new int[]{8,3,9,23,4};

				bubbleSort(array);

				for(int a : array){
						System.out.print(a+" ");
				}

				System.out.println("BubbleSort Ended");
		}

		/**
		 * This will give in ascending order.
		 *
		 * @param array
		 */
		private static void bubbleSort(int[] array){
				int n = array.length;
				for(int i=0; i<n; i++){
						//Each time iterate minus i times . As in every iteration 1 element gets fixed as last index.
						for(int j=0;j<n-1-i;j++){
								if(array[j] > array[j+1]){
										int temp = array[j+1];
										array[j+1] = array[j];
										array[j] = temp;
								}
						}
				}
		}
}


