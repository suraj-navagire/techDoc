package com.datastructures;

public class DoublyLinkedList <T> {

		private Node head;

		private Node tail;

		private int size;

		private class Node {
				T data;
				Node previous;
				Node next;
		}

		public void add(T data) {
				if(head == null) {
						Node node = new Node();
						node.data = data;
						head = node;
						tail = node;
						size = 1;
						return;
				}

				Node currentNode = head;

				while (currentNode.next != null){
						currentNode = currentNode.next;
				}

				Node newNode = new Node();
				newNode.data = data;

				currentNode.next = newNode;
				newNode.previous = currentNode;

				tail = newNode;
				size++;
		}


		public T get(int i) {

				if(i >= size) {
						System.out.println("Invalid index");
						return null;
				}

				if(i == 0){
						return head.data;
				}

				Node currentNode = head;
				int counter = 0;

				while(counter != i){
						currentNode = currentNode.next;
						counter++;
				}

				return currentNode.data;
		}

		public void printAllSequentially() {
				Node currentNode = head;

				System.out.print("[");
				while(currentNode.next != null){
						System.out.print(currentNode.data+",");
						currentNode = currentNode.next;
				}

				System.out.println(currentNode.data+"]");
		}


		public void printAllInReverseOrder(){
				Node currentNode = tail;

				System.out.print("[");
				while (currentNode.previous != null) {
						System.out.print(currentNode.data+",");
						currentNode = currentNode.previous;
				}

				System.out.println(currentNode.data+"]");
		}
}
