package com.datastructures;

public class StackArrayImplementation<T> {
		private int top;

		private Object[] stack;

		public StackArrayImplementation(int max){
				this.stack = new Object[max];
				this.top = -1;
		}

		private StackArrayImplementation(){

		}

		public void push(T element){
				if(isFull()){
						throw new RuntimeException("Stack is full cannot insert new element");
				}
				System.out.println("Adding element to stack : "+ element);
				top++;
				stack[top] = element;
		}

		public T pop(){
				if(isEmpty()){
						throw new RuntimeException("Stack is empty cannot pop element");
				}
				T element = (T) stack[top];
				top--;
				return element;
		}

		public T peek(){
				return (T) stack[top];
		}

		public boolean isEmpty(){
				return top == -1;
		}

		public boolean isFull(){
				return stack.length-1 == top;
		}
}
