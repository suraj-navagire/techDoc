package com.datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {

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

		@Override public Iterator<T> iterator() {
				return new Itr();
		}

		private class Itr implements Iterator<T> {

				private int originalSize;

				private int cursor;

				private Node currentNode;

				Itr(){
						this.originalSize = size;
						this.cursor = 0;
						this.currentNode = head;
				}

				@Override public boolean hasNext() {
						if(originalSize != size){
								throw new ConcurrentModificationException();
						}

						if(cursor >= size) {
								return false;
						} else {
								return true;
						}
				}

				@Override public T next() {
						if(originalSize != size){
								throw new ConcurrentModificationException();
						}

						T currentData  = currentNode.data;
						currentNode = currentNode.next;
						cursor++;
						return currentData;
				}
		}
}
