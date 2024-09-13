package org.example.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//We can schedule tasks for given interval of time.
public class ScheduledPeriodicThreadPoolExample {
		public static void main(String[] args) {
				System.out.println("ScheduledPeriodicThreadPoolExample started :");


				ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

				for(int i=0;i<6;i++){
						//Delaying task and using periodicity to specify time interval.
						service.scheduleAtFixedRate(new RunnableWorker3(i), 5, 5, TimeUnit.SECONDS);
				}

				try {
						service.awaitTermination(20, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				service.shutdown();

				System.out.println("ScheduledPeriodicThreadPoolExample ended");
		}
}

class RunnableWorker6 implements Runnable {
		private Integer number;

		RunnableWorker6(Integer number){
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