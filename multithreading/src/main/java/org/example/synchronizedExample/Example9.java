package org.example.synchronizedExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//To solve problem in Example8 we can use class level lock. So that all threads working on different objects of Worker6 class will add
// data synchronously.
public class Example9 {
		public static void main(String[] args) {
				long startTime = System.currentTimeMillis();
				Worker6 e = new Worker6();


				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								e.process();
						}
				});

				Worker6 e1 = new Worker6();
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


class Worker6 {
		private static List<Integer> l1 = new ArrayList<>();

		private Random random = new Random();
		private void addL1(){
				synchronized (Worker6.class){
						try {
								Thread.sleep(1);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}

						l1.add(random.nextInt());
				}

		}

		private void addL2(){
				synchronized (Worker6.class) {
						try {
								Thread.sleep(1);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}

						l1.add(random.nextInt());
				}

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