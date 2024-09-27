package com.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a max heap as an array, implement List<Integer> peekTopK(int[] arr, int k) to find the top k elements.
 * Do not modify the heap or copy entire heap to a different data structure. Example:
 *
 *            15
 *        /        \
 *      13         12
 *    /   \       /
 *  10     8     9
 * Input: [15, 13, 12, 10, 8, 9], k = 5
 * Output: [15, 13, 12, 10, 9]
 */
public class PrintMaxKElementHEAP {
		public static void main(String[] args) {
				System.out.println("PrintMaxKElementHEAP started");

				PrintMaxKElementHEAP printMaxKElementHEAP = new PrintMaxKElementHEAP();

				int[] arr = new int[6];
				arr[0] = 15;
				arr[1] = 13;
				arr[2] = 12;
				arr[3] = 10;
				arr[4] = 8;
				arr[5] = 9;

				int k = 5;

				List<Integer> result = printMaxKElementHEAP.peekTopK(arr, k);

				System.out.print("Result : ");

				for(Integer i : result){
						System.out.print(i+",");
				}
		}

		List<Integer> peekTopK(int[] arr, int k){
				//This step is important here we are comparing values of index and depending on values we are storing index priority.
				PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> arr[b] - arr[a]);

				List<Integer> result = new ArrayList<>();

				queue.add(0);

				while ( !queue.isEmpty() && k > 0){
						int index = queue.poll();
						 result.add(arr[index]);

						 int leftChildrenIndex = (2 * index) + 1;

						 int rightChildrenIndex = leftChildrenIndex + 1;

						 if(leftChildrenIndex < arr.length){
								 queue.add(leftChildrenIndex);
						 }

						 if(rightChildrenIndex < arr.length){
								 queue.add(rightChildrenIndex);
						 }

						 k--;
				}

				return result;
		}

}
