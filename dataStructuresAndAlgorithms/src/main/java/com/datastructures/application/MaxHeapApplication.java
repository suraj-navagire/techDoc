package com.datastructures.application;

import com.datastructures.MaxHeap;

import java.util.Random;

public class MaxHeapApplication {
		public static void main(String[] args) {
				System.out.println("MaxHeapApplication started");

				try{
						MaxHeapApplication application = new MaxHeapApplication();
						application.start();
				} catch (Throwable e){
						e.printStackTrace();
				}

				System.out.println("MaxHeapApplication ended");
		}

		private void start(){

				int n = 10;

				MaxHeap<Integer> maxHeap = new MaxHeap<>(n);

				Random ran = new Random();

				System.out.print("Inserting element : ");

				for(int i=0; i<n;i++){
						int element = ran.nextInt(20);
						System.out.print(element+",");
						maxHeap.insert(element);
				}

				System.out.println();

				System.out.println("Extracted element from heap : ");

				for (int i=0;i<n;i++){
						System.out.print(maxHeap.extractMax()+",");
				}

				System.out.println();

		}
}
