package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//Here the case is we want to perform one task i.e. populating list entirely and after that we want to execute some business logic.
// But populating list is done by multiple threads. Might be n number of threads. So practically using join is not a solution.
// So in this case we can use countdown latch. Once threads done their part of duty i.e. populating list then they will decrement count.
// Here if we don't wait for threads to complete its task then we will get wrong size of array.
public class CountDownLatchExample {
		public static void main(String[] args) {
				System.out.println("CountDownLatchExample started");

				CountDownLatch latch = new CountDownLatch(2);

				//Following is shared resource
				List<Integer> sharedList = new ArrayList<>();

				ListPopulator populator = new ListPopulator(1, 5, sharedList, latch);

				ListPopulator populator1 = new ListPopulator(6, 10, sharedList, latch);

				new Thread(populator).start();

				new Thread(populator1).start();

				try {
						latch.await();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				//Business logic that needs to perform after all threads completes their task.
				System.out.println("Printing List with size : " + sharedList.size());

				System.out.println("CountDownLatchExample ended");
		}
}

class ListPopulator implements Runnable {

		private Integer start;

		private Integer end;

		private List<Integer> sharedList;

		private CountDownLatch latch;

		ListPopulator(Integer start, Integer end, List<Integer> sharedList, CountDownLatch latch){
				this.start = start;
				this.end = end;
				this.sharedList = sharedList;
				this.latch = latch;
		}

		@Override public void run() {

				for(int i=start;i<=end;i++){
						try {
								Thread.sleep(500);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
						System.out.println("Add in list : "+i+" from thread : "+Thread.currentThread().getName());
						sharedList.add(i);
				}

				latch.countDown();
		}
}