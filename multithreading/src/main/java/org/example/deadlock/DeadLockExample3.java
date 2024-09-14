package org.example.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Here we kept lock acquiring sequence consistent.
 */
public class DeadLockExample3 {

		private List<Integer> list1 = new ArrayList<>();

		private List<Integer> list2 = new ArrayList<>();

		private Lock list1Lock = new ReentrantLock();

		private Lock list2Lock = new ReentrantLock();


		public static void main(String[] args) {

				System.out.println("DeadLockExample3 started");

				DeadLockExample3 example = new DeadLockExample3();

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
						acquireLock(list1Lock, list2Lock);
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
						acquireLock(list2Lock, list1Lock);

						try{
								list1.add(i);
								list2.add(i);
						} finally {
								list1Lock.unlock();
								list2Lock.unlock();
						}
				}
		}

		private void acquireLock(Lock lock1, Lock lock2){
				while (true) {
						//Using try lock so that we will get to know result else thread might get stuck.
						boolean gotFirstLock = lock1.tryLock();
						boolean gotSecondLock = lock2.tryLock();

						//If both lock acquired then return.
						if(gotFirstLock && gotSecondLock){
								return;
						}

						//If only one lock acquired then release it else might cause deadlock
						if(gotFirstLock){
								lock1.unlock();
						}

						//If only one lock acquired then release it else might cause deadlock
						if(gotSecondLock){
								lock2.unlock();
						}
				}

		}
}
