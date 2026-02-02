package com.systemdesign.lld.cachesystem;

import java.util.concurrent.TimeUnit;

public class Client {
		public static void main(String[] args) {
				System.out.println("Cache System started");

				Cache<String, String> cache = getCache(2);

				cache.put("Tea", "Coffee");
				cache.put("Bat", "Ball");
				cache.put("Come", "Go");

				System.out.println("Capacity is 2 Tea is removed from cache as its LRU : "+ cache.get("Tea"));

				System.out.println("Bat is removed from cache as its LRU : "+ cache.get("Bat"));


				System.out.println("Cache System started");
		}

		private static Cache<String, String> getCache(int capacity){
				return new InMemoryCache<>(capacity, new LRUEvictionPolicy<>(), TimeUnit.HOURS.toMillis(1));
		}
}
