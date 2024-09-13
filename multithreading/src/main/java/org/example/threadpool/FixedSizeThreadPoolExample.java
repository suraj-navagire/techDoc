package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//In thread pool fixed size of threads will get created and those thread wil get used.
public class FixedSizeThreadPoolExample {
		public static void main(String[] args) {

				System.out.println("Fixed size Thread pool example started");

				ExecutorService service = Executors.newFixedThreadPool(5);

				for(int i=0;i<15;i++) {
						service.submit(new RunnableWorker(i));
				}

				//If don't call then executors keeps running in background.
				service.shutdown();

				System.out.println("Ended");
		}


}


class RunnableWorker implements Runnable {
		private Integer number;

		RunnableWorker(Integer number){
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