package com.datastructures.application;

import com.datastructures.QueueLinkedListImplementation;

import java.util.Random;

public class QueueLinkedListImplementationApplication {

		public static void main(String[] args) {
				System.out.println("QueueLinkedListImplementationApplication started");

				try{
						QueueLinkedListImplementationApplication application = new QueueLinkedListImplementationApplication();
						application.start();
				} catch (Exception e){
						e.printStackTrace();
				}

				System.out.println("QueueLinkedListImplementationApplication ended");
		}

		private void start(){
				int maxSize = 10;

				QueueLinkedListImplementation<Integer> queue = new QueueLinkedListImplementation<>(10);

				System.out.println("Is queue empty : "+queue.isEmpty());

				Random ran = new Random();

				for(int i=0; i<maxSize; i++){
						queue.enqueue(ran.nextInt(100));
				}

				System.out.println("Is queue empty : "+queue.isEmpty());
				System.out.println("Is queue full : "+queue.isFull());


				System.out.println("Peek element : "+queue.peek());

				for(int i=0; i<maxSize; i++){
						System.out.println("Popped element : "+queue.dequeue());
				}

				System.out.println("Is queue full : "+queue.isFull());
				System.out.println("Is queue empty : "+queue.isEmpty());
		}
}
