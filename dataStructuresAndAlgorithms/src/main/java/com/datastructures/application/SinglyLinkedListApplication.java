package com.datastructures.application;

import com.datastructures.SinglyLinkedList;

public class SinglyLinkedListApplication {
		public static void main(String[] args) {
				SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

				for(int i=0 ; i<10; i++){
						list.add(i);
				}


				System.out.print("Printing all elements : ");
				list.printAll();

				System.out.println("Printing element present at 3rd position : " + list.get(2));

				System.out.println("Printing element present at 100th position : " + list.get(99));

		}
}
