package org.example.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Here without semaphore as you can see any number of threads can open connection. Count ++ and -- operations are synchronized
 * that is not issue here. Issue is if n number of threads can open connection then we might performance issues.
 *
 * For solution check SemaphoreExample2
 */
public class SemaphoreExample {
		public static void main(String[] args) {
				System.out.println("SemaphoreExample started : ");

				ExecutorService service = Executors.newCachedThreadPool();

				Connection con = new Connection();

				for(int i=0;i<200;i++){
						service.submit(() -> {
								con.openConnection();
						});
				}

				service.shutdown();
		}
}

class Connection {
		private Integer count = 0;

		public void openConnection(){
				synchronized (this) {
						count++;
				}

				//This sleep shows some task in real life
				try {
						Thread.sleep(1);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Connection count : "+count);

				try {
						Thread.sleep(100);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				synchronized (this){
						count--;
				}

		}
}
