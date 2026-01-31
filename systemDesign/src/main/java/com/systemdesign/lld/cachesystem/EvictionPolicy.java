package com.systemdesign.lld.cachesystem;

public interface EvictionPolicy<K> {
		void keyAccessed(K key);

		K evictKey();
}
