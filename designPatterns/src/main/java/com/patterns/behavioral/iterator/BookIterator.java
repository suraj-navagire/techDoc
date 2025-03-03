package com.patterns.behavioral.iterator;

/**
 * Iterator implementation. It provides a way to access elements of a collection.
 */
public class BookIterator implements Iterator{

		private Book[] books;

		private int currentIndex;

		public BookIterator(Book[] books) {
				this.books = books;
				currentIndex = 0;
		}

		@Override public boolean hasNex() {
				if(currentIndex < books.length){
						return true;
				}
				return false;
		}

		@Override public Object next() {
				return books[currentIndex++];
		}
}
