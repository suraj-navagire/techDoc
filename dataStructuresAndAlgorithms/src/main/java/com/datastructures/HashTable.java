package com.datastructures;

public class HashTable<T> {

		private Node[] table;

		public HashTable() {
				this.table = new Node[16];
		}

		private class Node<T> {
				int hash;
				T element;
				Node  next;
		}

		public void put(T element) {
				//calculate hash of element
				//calculating hash using hashcode

				int hash = element.hashCode();

				int index = hash % table.length;

				if(table[index] == null) {
						Node<T> newNode = new Node<>();
						newNode.element = element;
						newNode.hash = hash;
						table[index] = newNode;
				} else {
						addElement(element, hash, table[index]);
				}
		}

		private void addElement(T element, int hash, Node<T> existingNode){

				Node tempNode = existingNode;

				boolean valuePresent = false;
				while (tempNode.next != null) {
						if(existingNode.hash == hash && (existingNode.element == element || existingNode.element.equals(element))) {
								valuePresent = true;
								break;
						}

						tempNode = tempNode.next;
				}

				if(valuePresent) {
						return;
				}

				Node<T> newNode = new Node();
				newNode.element = element;
				newNode.hash = hash;

				tempNode.next = newNode;
		}

		public boolean contains(T element){
				//calculate hash of element
				//calculating hash using hashcode
				int hash = element.hashCode();

				int index = hash % table.length;

				if(table[index] == null) {
						return false;
				}

				Node<T> tempElement = table[index];

				while (tempElement != null) {
						if(tempElement.hash == hash && (tempElement.element == element || tempElement.element.equals(element))) {
								return true;
						}

						tempElement = tempElement.next;
				}

				return false;
		}
}
