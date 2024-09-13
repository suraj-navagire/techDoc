package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//New thread will get created if current thread is busy.
public class CachedThreadPoolExample {
		public static void main(String[] args) {
				System.out.println("CachedThreadPoolExample started :");

				ExecutorService service = Executors.newCachedThreadPool();

				for(int i=0;i<10;i++){
						service.submit(new RunnableWorker3(i));
				}


				service.shutdown();

				System.out.println("CachedThreadPoolExample ended");
		}
}

class RunnableWorker3 implements Runnable {
		private Integer number;

		RunnableWorker3(Integer number){
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