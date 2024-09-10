package org.example.synchronizedExample;

import java.util.ArrayList;
import java.util.List;

//In this example we are adding elements in 2 different arrays. Here task is completed by main thread
public class Example3 {

		public static void main(String[] args) {
				long startTime = System.currentTimeMillis();
				Worker e = new Worker();
				e.process();
				System.out.println("Total time : " + (System.currentTimeMillis()-startTime));
				System.out.println("List 1 count : "+e.getL1Count()+", List 2 count : " + e.getL2Count());
		}
}


class Worker {
		private List<Double> l1 = new ArrayList<>();

		private List<Double> l2 = new ArrayList<>();

		private void addL1(){
				try {
						Thread.sleep(1);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				l1.add(Math.random());
		}

		private void addL2(){
				try {
						Thread.sleep(1);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				l2.add(Math.random());
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