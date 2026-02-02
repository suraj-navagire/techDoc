package com.systemdesign.lld.cachesystem;

public class DoublyLinkedList<K> {
		private Node<K> head;
		private Node<K> tail;

		public Node<K> addToFirst(K key){
				Node<K> node = new Node<>(key);
				node.next = head;

				if (head != null){
						head.prev = node;
				}

				head = node;

				if(tail == null){
						tail = node;
				}

				return node;
		}

		public void moveToFirst(Node<K> node){
				if (node == head) return;

				// detach node
				if (node.prev != null) {
						node.prev.next = node.next;
				}
				if (node.next != null) {
						node.next.prev = node.prev;
				}

				if (node == tail) {
						tail = node.prev;
				}

				// move to head
				node.prev = null;
				node.next = head;

				if (head != null) {
						head.prev = node;
				}

				head = node;

				if (tail == null) {
						tail = head;
				}
		}


		public Node<K> removeLast(){
				Node<K> node = tail;

				if(head == tail){
						head = tail = null;
						return node;
				}

				tail = tail.prev;

				tail.next = null;

				return node;
		}
}
