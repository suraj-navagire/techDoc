package org.example.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FuturesExample {
		public static void main(String[] args) {
				System.out.println("FuturesExample Started : ");

				ExecutorService service = Executors.newFixedThreadPool(2);

				Future<List<Integer>> result1 = service.submit(new ListPopulator());

				Future<List<Integer>> result2 = service.submit(new ListPopulator());

				try {
						System.out.println("Result 1 : "+result1.get().size());
						System.out.println("Result 2 : "+result2.get().size());
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				} catch (ExecutionException e) {
						throw new RuntimeException(e);
				}

				service.shutdown();
				System.out.println("FuturesExample Ended : ");
		}
}

class ListPopulator implements Callable<List<Integer>> {

		@Override public List<Integer> call() throws Exception {
				List<Integer> list = new ArrayList<>();
				for(int i=0;i<10;i++){
						Thread.sleep(1000);
						list.add(i);
				}
				return list;
		}
}