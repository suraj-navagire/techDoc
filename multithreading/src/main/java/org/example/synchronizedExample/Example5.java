package org.example.synchronizedExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Here to solve Example4 problem we can make methods synchronized. but then count is correct but time is doubled, since both thread working on
//same object and at a time only one thread can acquire intrinsic lock.
public class Example5 {
		public static void main(String[] args) {
				long startTime = System.currentTimeMillis();
				Worker2 e = new Worker2();


				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								e.process();
						}
				});

				Thread t2 = new Thread(new Runnable() {
						@Override public void run() {
								e.process();
						}
				});

				t1.start();
				t2.start();

				try {
						t1.join();
						t2.join();
				} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
				}


				System.out.println("Total time : " + (System.currentTimeMillis()-startTime));
				System.out.println("List 1 count : "+e.getL1Count()+", List 2 count : " + e.getL2Count());
		}
}


class Worker2 {
		private List<Integer> l1 = new ArrayList<>();

		private List<Integer> l2 = new ArrayList<>();

		private Random random = new Random();
		private synchronized void addL1(){
				try {
						Thread.sleep(1);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				l1.add(random.nextInt());
		}

		private synchronized void addL2(){
				try {
						Thread.sleep(1);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				l2.add(random.nextInt());
		}

		public void process(){
				for (int i=0;i<1000;i++){
						addL1();
						addL2();
				}
		}

		public int getL1Count(){
				return l1.size();
		}

		public int getL2Count(){
				return l2.size();
		}
}