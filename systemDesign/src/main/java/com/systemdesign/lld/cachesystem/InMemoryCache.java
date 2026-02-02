package com.systemdesign.lld.cachesystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryCache<K, V> implements Cache<K, V> {

		private final Map<K, CacheEntry<V>> data = new HashMap<>();
		private final int capacity;
		private final EvictionPolicy<K> evictionPolicy;
		private ReentrantLock lock = new ReentrantLock();
		private final long timeToLive;

		public InMemoryCache(int capacity, EvictionPolicy<K> evictionPolicy, long timeToLive) {
				this.capacity = capacity;
				this.evictionPolicy = evictionPolicy;
				this.timeToLive = timeToLive;
		}

		@Override public V get(K key) {
				CacheEntry<V> value = data.get(key);

				if(value != null){
						lock.lock();
						evictionPolicy.keyAccessed(key);
						lock.unlock();
						return value.value;
				}

				return null;
		}

		@Override public void put(K key, V value) {
				lock.lock();
				if(!data.containsKey(key) && capacity == data.size()){
						K evictKey = evictionPolicy.evictKey();
						data.remove(evictKey);
				}

				long expiryTime = System.currentTimeMillis() + this.timeToLive;
				data.put(key, new CacheEntry<>(value, expiryTime));
				evictionPolicy.keyAccessed(key);
				lock.unlock();
		}
}
