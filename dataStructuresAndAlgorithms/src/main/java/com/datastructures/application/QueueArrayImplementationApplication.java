package com.datastructures.application;

import com.datastructures.QueueArrayImplementation;

import java.util.Random;

public class QueueArrayImplementationApplication {

		public static void main(String[] args) {
				System.out.println("QueueArrayImplementationApplication started");

				try{
						QueueArrayImplementationApplication application = new QueueArrayImplementationApplication();
						application.start();
				} catch (Exception e){
						e.printStackTrace();
				}

				System.out.println("QueueArrayImplementationApplication ended");
		}

		private void start(){
				int maxSize = 10;

				QueueArrayImplementation<Integer> queue = new QueueArrayImplementation<>(10);

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
