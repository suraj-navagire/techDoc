package org.example.deadlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we are expecting to get size of each list as 2000 and total 4000, But as multiple threads working on lists parallelly we will not get correct
 * output. Check DeadLockExample2
 */
public class DeadLockExample1 {

		private List<Integer> list1 = new ArrayList<>();

		private List<Integer> list2 = new ArrayList<>();

		public static void main(String[] args) {

				System.out.println("DeadLockExample1 started");

				DeadLockExample1 example = new DeadLockExample1();

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
						list1.add(i);
						list2.add(i);
				}

		}

		private void addElement2(){
				for(int i =0; i<1000;i++){
						list1.add(i);
						list2.add(i);
				}
		}
}
