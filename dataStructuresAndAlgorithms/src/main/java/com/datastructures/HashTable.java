package com.datastructures;

public class HashTable<k> {

		private Node[] table;

		public HashTable() {
				this.table = new Node[16];
		}

		private class Node<k> {
				int hash;
				k element;
				Node  next;
		}

		public void put(k element) {
				//calculate hash of element
				//calculating hash using hashcode

				int hash = element.hashCode();

				int index = hash % table.length;

				if(table[index] == null) {
						Node<k> newNode = new Node<>();
						newNode.element = element;
						newNode.hash = hash;
						table[index] = newNode;
				} else {
						addElement(element, hash, table[index]);
				}
		}

		private void addElement(k element, int hash, Node<k> existingNode){

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

				Node<k> newNode = new Node();
				newNode.element = element;
				newNode.hash = hash;

				tempNode.next = newNode;
		}

		public boolean contains(k element){
				//calculate hash of element
				//calculating hash using hashcode
				int hash = element.hashCode();

				int index = hash % table.length;

				if(table[index] == null) {
						return false;
				}

				Node<k> tempElement = table[index];

				while (tempElement != null) {
						if(tempElement.hash == hash && (tempElement.element == element || tempElement.element.equals(element))) {
								return true;
						}

						tempElement = tempElement.next;
				}

				return false;
		}
}
