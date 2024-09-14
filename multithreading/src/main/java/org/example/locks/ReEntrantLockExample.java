package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Without synchronization, we will get wrong result.
 */
public class ReEntrantLockExample {
		private Lock lock = new ReentrantLock();

		private Integer count = 0;

		public static void main(String[] args) {
				System.out.println("ReEntrantLockExample started");

				ReEntrantLockExample example = new ReEntrantLockExample();

				Thread t1 = new Thread(()->{
						example.addElement1();
				});

				Thread t2 = new Thread(()->{
						example.addElement2();
				});

				t1.start();

				t2.start();

				try {
						t1.join();
						t2.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Count : "+ example.count);

				System.out.println("Main thread ended");
		}

		private void addElement1(){
				increment();
		}

		private void addElement2(){
				increment();
		}

		private void increment(){
				lock.lock();
				try{
						for(int i =0;i<1000;i++){
								count++;
						}
				} finally {
						lock.unlock();
				}

		}
}
