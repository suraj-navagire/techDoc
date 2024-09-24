package com.datastructures.application;

import com.datastructures.StackArrayImplementation;

import java.util.Random;

public class StackArrayImplementationApplication {
		public static void main(String[] args) {
				System.out.println("StackArrayImplementationApplication started");

				try{
						StackArrayImplementationApplication application = new StackArrayImplementationApplication();
						application.start();
				} catch (Exception e){
						e.printStackTrace();
				}

				System.out.println("StackArrayImplementationApplication ended");
		}

		private void start(){
				int maxSize = 10;

				StackArrayImplementation<Integer> stack = new StackArrayImplementation<>(10);

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
