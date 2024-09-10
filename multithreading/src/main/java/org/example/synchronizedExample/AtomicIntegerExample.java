package org.example.synchronizedExample;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
		//This is shared resource
		private AtomicInteger count = new AtomicInteger(0);

		public static void main(String[] args) {
				AtomicIntegerExample e = new AtomicIntegerExample();
				e.doWork();
		}

		private void doWork(){

				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
										count.incrementAndGet();
						}
				});


				Thread t2 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
										count.incrementAndGet();
						}
				});

				t1.start();
				t2.start();

				try {
						t1.join();
						t1.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}


				//Since we have used Atomic integers we will get correct output
				System.out.println("Count : "+count);
		}
}
