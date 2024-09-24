package com.datastructures;

public class StackLinkedListImplementation<T> {

		private int max;

		private Node top;

		private int currentSize;

		private class Node{
				T element;
				Node previous;
		}

		public StackLinkedListImplementation(int max){
				this.max = max;
				this.currentSize = -1;
		}

		public void push(T element){
				if(isFull()){
						throw new RuntimeException("Stack is full cannot insert new element");
				}
				System.out.println("Adding element : "+element);
				currentSize++;
				if(top == null){
						top = new Node();
						top.element = element;
						return;
				}

				Node newNode = new Node();
				newNode.element = element;
				newNode.previous = top;

				top = newNode;
		}

		public T pop(){
				if(isEmpty()){
						throw new RuntimeException("Stack is empty cannot pop element");
				}
				T element = top.element;
				top = top.previous;

				currentSize--;
				return element;
		}

		public T peek(){
				return top.element;
		}

		public boolean isEmpty(){
				return currentSize == -1;
		}

		public boolean isFull(){
				return currentSize == max-1;
		}
}
