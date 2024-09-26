package com.algorithms;

import java.util.Random;

public class HeapSortApplication {
		public static void main(String[] args) {
				HeapSortApplication application = new HeapSortApplication();
				application.start();
		}

		public void start(){
				HeapSort<Integer> heapsort = new HeapSort<>();
				int n =10;

				Random ran = new Random();
				Integer[] inputTree = new Integer[n];
				for(int i=0;i<n;i++){
						int number = ran.nextInt(100);
						System.out.print(number+",");
						inputTree[i] = number;
				}

				heapsort.sort(inputTree);

				System.out.println("After Heap Sort : ");

				for(int i=0;i<n;i++){
						System.out.print(inputTree[i]+",");
				}
		}
}
