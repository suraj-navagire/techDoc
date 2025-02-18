package org.example.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Fail-fast Example
public class ConcurrentModificationExceptionExample extends Thread {

		private static List<String> sharedResource = new ArrayList<>();

		public static void main(String[] args) {
				try {
						System.out.println("ConcurrentModificationExceptionExample Started");

						sharedResource.add("first");
						sharedResource.add("second");

						ConcurrentModificationExceptionExample thread = new ConcurrentModificationExceptionExample();
						thread.start();

						Iterator<String> iterator = sharedResource.iterator();
						while (iterator.hasNext()){
								System.out.println(iterator.next());
								try {
										Thread.sleep(200);
								} catch (InterruptedException e) {
										throw new RuntimeException(e);
								}
						}

				} catch (Exception e){
						e.printStackTrace();
				}

				System.out.println("ConcurrentModificationExceptionExample Ended");
		}

		@Override
		public void run(){
				try {
						Thread.sleep(100);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				sharedResource.add("threadFirst");
		}

}
