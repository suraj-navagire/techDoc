package org.example.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Used to schedule task. Creates fixed size of thread pool. If we use submit method then task gets executed immediately like other
// executor service. If we use schedule then we can delay task . or can set periodic task.
public class ScheduledThreadPoolExample {
		public static void main(String[] args) {
				System.out.println("ScheduledThreadPoolExample started :");


				ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

				for(int i=0;i<10;i++){
						//Delaying task
						service.schedule(new RunnableWorker3(i), 5, TimeUnit.SECONDS);
				}


				service.shutdown();

				System.out.println("ScheduledThreadPoolExample ended");
		}
}


class RunnableWorker5 implements Runnable {
		private Integer number;

		RunnableWorker5(Integer number){
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