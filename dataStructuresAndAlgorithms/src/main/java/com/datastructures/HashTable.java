package com.datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.function.Consumer;

public class HashTable<T> implements Iterable<T> {

		private Node[] table;

		private int size;

		public HashTable() {
				this.table = new Node[16];
				this.size = 0;
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
						size++;
				} else {
						addElement(element, hash, table[index]);
				}
		}

		private void addElement(T element, int hash, Node<T> existingNode){

				Node tempNode = existingNode;

				boolean valuePresent = false;
				while (tempNode.next != null) {
						if(tempNode.hash == hash && (tempNode.element == element || tempNode.element.equals(element))) {
								valuePresent = true;
								break;
						}

						tempNode = tempNode.next;
				}

				if(tempNode.hash == hash && (tempNode.element == element || tempNode.element.equals(element))) {
						valuePresent = true;
				}

				if(valuePresent) {
						return;
				}

				Node<T> newNode = new Node();
				newNode.element = element;
				newNode.hash = hash;

				tempNode.next = newNode;
				size++;
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

		@Override public Iterator<T> iterator() {
				return new Itr();
		}

		@Override public void forEach(Consumer<? super T> action) {
				for(int i=0 ; i<table.length; i++){
						Node<T> currentNode = table[i];

						if(currentNode == null){
								continue;
						}

						while (currentNode.next != null) {
								action.accept(currentNode.element);
						}

						action.accept(currentNode.element);
				}
		}

		private class Itr implements Iterator<T> {

				int originalSize;

				int cursor;

				Node<T>[] tempTable;

				Node<T>currentNode;

				int currentIndex;

				Itr(){
						this.originalSize = size;
						this.cursor = 0;
						this.tempTable = table;
				}

				@Override public boolean hasNext() {
						if(originalSize != size){
								throw new ConcurrentModificationException();
						}

						if(cursor >= originalSize) {
								return false;
						} else {
								return true;
						}
				}

				@Override public T next() {

						if(originalSize != size) {
								throw new ConcurrentModificationException();
						}

						if(currentNode == null) {
								while (currentIndex < tempTable.length){
										currentNode = tempTable[currentIndex++];
										if(currentNode != null){
												cursor++;
												break;
										}
								}
						} else {
								if(currentNode.next != null){
										currentNode = currentNode.next;
								} else {
										while (currentIndex < tempTable.length){
												currentNode = tempTable[currentIndex++];
												if(currentNode != null){
														cursor++;
														break;
												}
										}
								}



						}

						return currentNode.element;
				}
		}

}
