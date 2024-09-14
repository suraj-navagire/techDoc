package org.example.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingQueue {

		private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);


		public static void main(String[] args) {

				System.out.println("ProducerConsumerUsingQueue started");

				Thread t1 = new Thread(() -> {
						try {
								producer();
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
				});

				Thread t2 = new Thread(() -> {
						try {
								consumer();
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
				});

				t1.start();
				t2.start();

				System.out.println("Main thread ended");
		}


		private static void producer() throws InterruptedException {
				Random random = new Random();
				while (true){
						Thread.sleep(random.nextInt(500));
						queue.put(random.nextInt(100));
				}
		}

		private static void consumer() throws InterruptedException {
				Random random = new Random();
				while (true){
						Thread.sleep(random.nextInt(500));
						Integer value = queue.take();

						System.out.println("Queue size : "+queue.size()+", Taking element from queue : "+value);
				}
		}

}
