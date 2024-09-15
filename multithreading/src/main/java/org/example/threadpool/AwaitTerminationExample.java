package org.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Await termination will send current thread to wait state until all worker completes its job or waiting time gets exhausted.
 */
public class AwaitTerminationExample {
		public static void main(String[] args) {

				System.out.println("AwaitTerminationExample started");

				ExecutorService service = Executors.newSingleThreadExecutor();

				RunnableWorker7 worker = new RunnableWorker7();

				service.submit(worker);

				service.shutdown();

				System.out.println("No more worker tasks will get accepted");

				try {
						service.awaitTermination(5, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Main Thread Ended");

		}
}

class RunnableWorker7 implements Runnable {

		@Override public void run() {
				System.out.println("Worker started");

				try {
						Thread.sleep(3000);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Worker completed");

		}
}