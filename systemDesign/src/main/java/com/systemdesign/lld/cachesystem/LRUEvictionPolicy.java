package com.systemdesign.lld.cachesystem;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
		//Note Both DoublyLinkedList and Map is required to achieve O(1) time complexity.
		private final DoublyLinkedList<K> doublyLinkedList = new DoublyLinkedList<>();
		private final Map<K, Node<K>> map = new HashMap<>();


		@Override public void keyAccessed(K key) {
				Node<K> node = map.get(key);
				if(node == null){
						node = doublyLinkedList.addToFirst(key);
						map.put(key, node);
				} else {
						doublyLinkedList.moveToFirst(node);
				}
		}

		@Override public K evictKey() {
				Node<K> node = doublyLinkedList.removeLast();
				map.remove(node.key);
				return node.key;
		}
}
