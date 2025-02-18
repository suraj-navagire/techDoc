package org.example.concurrent;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FailSafeExample extends Thread {

		private static Map<Integer, String> sharedResource = new ConcurrentHashMap<>();

		public static void main(String[] args) {
				try {
						System.out.println("FailSafeExample Started");

						sharedResource.put(1, "first");
						sharedResource.put(2, "second");

						FailSafeExample thread = new FailSafeExample();
						thread.start();

						Iterator<Map.Entry<Integer, String>> iterator = sharedResource.entrySet().iterator();
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

				System.out.println("FailSafeExample Ended");
		}

		@Override
		public void run(){
				try {
						Thread.sleep(100);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				sharedResource.put(3, "threadFirst");
		}

}