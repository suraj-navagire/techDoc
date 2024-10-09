package com.algorithms;

public class QuickSort {
		public static void main(String[] args) {
				System.out.println("QuickSort Started");

				int[] a = new int[]{6,3,9,5,2,8};

				quickSort(a, 0, 5);

				System.out.println("Sorted elements : ");
				for (int n : a ){
						System.out.print(n+" ");
				}

				System.out.println("QuickSort Ended");
		}


		private static void quickSort(int[] a, int low, int high){
				if(low < high){
						int pivotSortedIndex = partition(a, low, high);

						quickSort(a, low, pivotSortedIndex -1);
						quickSort(a, pivotSortedIndex+1, high);
				}
		}

		private static int partition(int[] a, int low, int high){
				int pivot = a[high];
				int i = low -1;

				for (int j=low;j<high;j++){
						if(a[j] < pivot) {
								i++;
								int temp = a[j];
								a[j] = a[i];
								a[i] = temp;
						}
				}

				i++;

				int temp = a[i];
				a[i] = pivot;
				a[high] = temp;

				return i;
		}
}
