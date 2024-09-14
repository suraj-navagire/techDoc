package org.example.producerconsumer;

import java.util.Random;

public class WaitNotifyExample {

		private Object lockObject = new Object();

		private static Boolean canProduce = true;

		private static Integer sharedInteger;

		public static void main(String[] args) {
				System.out.println("WaitNotifyExample started : ");

				WaitNotifyExample example = new WaitNotifyExample();

				Thread t1 = new Thread(()->{
						try {
								example.produce();
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
				});

				Thread t2 = new Thread(() -> {
						try {
								example.consume();
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
				});

				t1.start();
				t2.start();
				System.out.println("Main thread ended");
		}

		private void produce() throws InterruptedException {
				Random random = new Random();
				while (true){
						if(canProduce){
								synchronized (lockObject){
										sharedInteger = random.nextInt(500);
										System.out.println("Producing element : " + sharedInteger);
										canProduce = false;
										lockObject.wait();;
										System.out.println("Producer waiting ended");
								}
						}
						Thread.sleep(random.nextInt(1000));
				}

		}


		private void consume() throws InterruptedException {

				Thread.sleep(2000);

				Random random = new Random();
				while (true) {
						if(!canProduce) {
								synchronized (lockObject){
										System.out.println("Consuming element."+ sharedInteger);
										canProduce = true;
										lockObject.notify();
										System.out.println("Consumer sent notification");
								}
						}

						Thread.sleep(random.nextInt(1000));

				}
		}

}
