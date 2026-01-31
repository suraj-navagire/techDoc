package com.systemdesign.lld.cachesystem;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache<K, V> implements Cache<K, V> {

		private final Map<K, V> data = new HashMap<>();
		private final int capacity;
		private final EvictionPolicy<K> evictionPolicy;

		public InMemoryCache(int capacity, EvictionPolicy<K> evictionPolicy) {
				this.capacity = capacity;
				this.evictionPolicy = evictionPolicy;
		}

		@Override public V get(K key) {
				V value = data.get(key);

				if(value != null){
						evictionPolicy.keyAccessed(key);
				}

				return value;
		}

		@Override public void put(K key, V value) {
				if(capacity == data.size()){
						K evictKey = evictionPolicy.evictKey();
						data.remove(evictKey);
				}

				data.put(key, value);
				evictionPolicy.keyAccessed(key);
		}
}
