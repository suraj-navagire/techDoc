package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedSizeThreadPoolAwaitTermination {
		public static void main(String[] args) {
				System.out.println("FixedSizeThreadPoolAwaitTermination example started");

				ExecutorService service = Executors.newFixedThreadPool(5);

				for(int i=0;i<15;i++) {
						service.submit(new RunnableWorker(i));
				}


				try {
						//Program stops at this line. In background executors keeps running. After given time executors ends.
						service.awaitTermination(10, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Ended");
		}
}


class RunnableWorker1 implements Runnable {
		private Integer number;

		RunnableWorker1(Integer number){
				this.number = number;
		}

		@Override public void run() {
				try {
						Thread.sleep(1000);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}
				System.out.println("Printing Runnable worker with number : "+ number+" from Thread : "+Thread.currentThread().getName());
		}
}