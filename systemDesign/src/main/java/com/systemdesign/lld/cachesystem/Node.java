package com.systemdesign.lld.cachesystem;

public class Node<K> {
		K key;
		Node<K> prev;
		Node<K> next;

		public Node(K key) {
				this.key = key;
		}
}
