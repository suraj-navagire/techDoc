package com.datastructures;

public class QueueLinkedListImplementation<T> {

		private Node head;

		private Node tail;

		private int max;

		private int currentSize;

		private class Node {
				T element;
				Node next;
		}

		public QueueLinkedListImplementation(int max){
				this.max = max;
		}

		public void enqueue(T element){
				if(isFull()){
						throw new RuntimeException("Cannot insert new element as queue is full");
				}

				System.out.println("Adding element : "+element);
				Node newNode = new Node();
				newNode.element = element;

				if(head == null){
						head = newNode;
				}

				if(tail == null){
						tail = newNode;
				} else {
						tail.next = newNode;
						tail = tail.next;
				}

				currentSize++;
		}

		public T dequeue(){
				if(isEmpty()){
						throw new RuntimeException("Queue is empty cannot return element.");
				}

				T element = head.element;

				head = head.next;

				if(head == null){
						tail = null;
				}

				currentSize--;
				return element;
		}

		public T peek(){
				if(isEmpty()){
						throw new RuntimeException("Queue is empty cannot return element.");
				}

				return head.element;
		}

		public boolean isEmpty(){
				return head == null;
		}

		public boolean isFull(){
				return currentSize == max;
		}
}
