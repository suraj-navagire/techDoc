package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Only one thread will get created in thread pool
public class SingleThreadPoolExample {
		public static void main(String[] args) {
				System.out.println("SingleThreadPoolExample started :");

				ExecutorService service = Executors.newSingleThreadExecutor();

				for(int i=0;i<10;i++){
						service.submit(new RunnableWorker3(i));
				}


				service.shutdown();

				System.out.println("SingleThreadPoolExample ended");
		}
}

class RunnableWorker4 implements Runnable {
		private Integer number;

		RunnableWorker4(Integer number){
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