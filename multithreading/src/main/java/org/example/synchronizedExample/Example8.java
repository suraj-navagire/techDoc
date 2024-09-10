package org.example.synchronizedExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//In this example Threads are working on different Worker5 objects but list inside them is static. Hence both object of worker is sharing
//same list object. Even we have marked add method synchronized still we are getting wrong count. As synchronized on method takes lock of
//object but since both threads are working on different object it's useless.
//
public class Example8 {
		public static void main(String[] args) {
				long startTime = System.currentTimeMillis();
				Worker5 e = new Worker5();


				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								e.process();
						}
				});

				Worker5 e1 = new Worker5();
				Thread t2 = new Thread(new Runnable() {
						@Override public void run() {
								e1.process();
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
				System.out.println("List count : "+e.getL1Count());
		}
}


class Worker5 {
		private static List<Integer> l1 = new ArrayList<>();

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

				l1.add(random.nextInt());
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

}