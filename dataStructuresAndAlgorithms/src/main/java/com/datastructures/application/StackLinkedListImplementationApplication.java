package com.datastructures.application;

import com.datastructures.StackLinkedListImplementation;

import java.util.Random;

public class StackLinkedListImplementationApplication {
		public static void main(String[] args) {
				System.out.println("StackLinkedListImplementationApplication started");

				try{
						StackLinkedListImplementationApplication application = new StackLinkedListImplementationApplication();
						application.start();
				}catch (Exception e){
						e.printStackTrace();
				}


				System.out.println("StackLinkedListImplementationApplication ended");
		}

		private void start(){
				int maxSize = 10;

				StackLinkedListImplementation<Integer> stack = new StackLinkedListImplementation<>(10);

				System.out.println("Is stack empty : "+stack.isEmpty());

				Random ran = new Random();

				for(int i=0; i<maxSize; i++){
						stack.push(ran.nextInt(100));
				}

				System.out.println("Is stack empty : "+stack.isEmpty());
				System.out.println("Is stack full : "+stack.isFull());


				System.out.println("Peek element : "+stack.peek());

				for(int i=0; i<maxSize; i++){
						System.out.println("Popped element : "+stack.pop());
				}

				System.out.println("Is stack full : "+stack.isFull());
				System.out.println("Is stack empty : "+stack.isEmpty());
		}
}
