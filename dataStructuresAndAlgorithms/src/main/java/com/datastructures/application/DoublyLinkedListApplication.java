package com.datastructures.application;

import com.datastructures.DoublyLinkedList;

public class DoublyLinkedListApplication {
		public static void main(String[] args) {
				DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

				for(int i=0 ; i<10; i++){
						list.add(i);
				}


				System.out.print("Printing all elements sequentially: ");
				list.printAllSequentially();

				System.out.print("Printing all elements in reversal order : ");
				list.printAllInReverseOrder();

				System.out.println("Printing element present at 3rd position : " + list.get(2));

				System.out.println("Printing element present at 100th position : " + list.get(99));
		}
}
