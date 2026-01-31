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

		public void addToFirstNode(Node<K> node){
				Node<K> prev = node.prev;

				if(prev == null){
						return;
				}

				Node<K> next = node.next;

				if(next == null){
						tail = prev;
				} else {
						next.prev = prev;
				}


				node.prev = null;
				node.next = head;

				head.prev = node;
		}


		public Node<K> removeLast(){
				Node<K> node = tail;

				Node<K> prev = tail.prev;

				tail = prev;

				prev.next = null;

				return node;
		}
}
