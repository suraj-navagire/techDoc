package org.example.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {
		public static void main(String[] args) {
				System.out.println("SemaphoreExample2 started : ");

				ExecutorService service = Executors.newCachedThreadPool();

				Connection1 con = new Connection1();

				for(int i=0;i<200;i++){
						service.submit(() -> {
								con.openConnection();
						});
				}

				service.shutdown();
		}
}

class Connection1 {
		private Integer count = 0;

		private Semaphore semaphore = new Semaphore(10);

		public void openConnection(){
				try {
						//Only 10 threads will get permit
						semaphore.acquire();

						synchronized (this) {
								count++;
						}

						try {
								Thread.sleep(1);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}

						//Count will never go above 10
						System.out.println("Connection count : "+count);

						try {
								Thread.sleep(100);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}

						synchronized (this){
								count--;
						}

				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				} finally {
						semaphore.release();
				}


		}
}
