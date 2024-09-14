package org.example.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * In this example we have used Lock objects for locking, but lock acquiring sequence varies for different threads and also threads gets stuck
 * if it didn't get lock. Resulting deadlock.
 * Check DeadLockExample3 for solution
 */
public class DeadLockExample2 {

		private List<Integer> list1 = new ArrayList<>();

		private List<Integer> list2 = new ArrayList<>();

		private Lock list1Lock = new ReentrantLock();

		private Lock list2Lock = new ReentrantLock();


		public static void main(String[] args) {

				System.out.println("DeadLockExample2 started");

				DeadLockExample2 example = new DeadLockExample2();

				Thread t1 = new Thread(() -> {
						example.addElement1();
				});

				Thread t2 = new Thread(() -> {
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

				System.out.println("List 1 size : "+example.list1.size());
				System.out.println("List 2 size : "+example.list2.size());
				System.out.println("Total size : "+(example.list1.size() + example.list2.size()));
				System.out.println("Main thread ended");
		}

		private void addElement1(){
				for(int i =0; i<1000;i++){
						//Check sequence of lock
						list1Lock.lock();
						list2Lock.lock();
						try{
								list1.add(i);
								list2.add(i);
						} finally {
								list1Lock.unlock();
								list2Lock.unlock();
						}
				}

		}

		private void addElement2(){
				for(int i =0; i<1000;i++){
						//Check sequence of lock
						list2Lock.lock();
						list1Lock.lock();
						try{
								list1.add(i);
								list2.add(i);
						} finally {
								list1Lock.unlock();
								list2Lock.unlock();
						}
				}
		}
}
