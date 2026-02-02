package com.systemdesign.lld.cachesystem;

public class CacheEntry<V> {
		V value;
		long expiryTime;

		public CacheEntry(V value, long expiryTime) {
				this.value = value;
				this.expiryTime = expiryTime;
		}
}
