package com.datastructures;

public class SinglyLinkedList<T> {

		private Node head;

		private int size;

		private class Node {
				T data;
				Node next;
		}

		public void add(T data){
				if(head == null) {
						head = new Node();
						head.data = data;
						size = 1;
						return;
				}

				Node currentNode = head;

				while(currentNode.next != null) {
						currentNode = currentNode.next;
				}

				Node newNode = new Node();
				newNode.data = data;

				currentNode.next = newNode;
				size++;
		}


		public T get(int i) {

				if(i >= size){
						System.out.println("Invalid index");
						return null;
				}

				if(i == 0){
						return head.data;
				}

				Node currentNode = head;
				int counter = 0;
				while(counter != i) {
						currentNode = currentNode.next;
						counter++;
				}

				return currentNode.data;
		}

		public void printAll(){
				Node currentNode = head;

				System.out.print("[");
				while (currentNode.next != null) {
						System.out.print(currentNode.data+",");
						currentNode = currentNode.next;
				}

				System.out.println(currentNode.data+"]");
		}

}
