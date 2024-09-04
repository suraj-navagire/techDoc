package com.datastructures.application;

import com.datastructures.SinglyLinkedList;

import java.util.Iterator;

public class SinglyLinkedListApplication {
		public static void main(String[] args) {
				SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

				for(int i=0 ; i<10; i++){
						list.add(i);
				}


				System.out.print("Printing all elements : ");
				list.printAll();

				System.out.println("Printing element present at 3rd position : " + list.get(2));

				System.out.print("Printing element present at 100th position : ");

				System.out.println(list.get(99));

				System.out.print("Iterating over Singly Linked List using iterator : ");

				Iterator<Integer> iterator = list.iterator();

				System.out.print("[");
				while (iterator.hasNext()){
						Integer m = iterator.next();
						System.out.print(m+",");
				}

				System.out.println("]");

				System.out.print("Iterating over Singly Linked List using advanced for loop : ");

				System.out.print("[");

				for(Integer a : list){
						System.out.print(a+",");
				}

				System.out.println("]");


				System.out.print("Iterating over Singly Linked List using forEach method : ");
				System.out.print("[");
				list.forEach(q -> System.out.print(q+","));
				System.out.println("]");
		}
}
