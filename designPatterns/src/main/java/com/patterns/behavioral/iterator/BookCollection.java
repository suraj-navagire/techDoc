package com.patterns.behavioral.iterator;

/**
 * Collection class which holds books. We will not use thi class to iterate over books. Instead, we will return iterator
 */
public class BookCollection implements Iterable{
		private Book[] books;

		public BookCollection(Book[] books) {
				this.books = books;
		}

		@Override public Iterator getIterator() {
				return new BookIterator(books);
		}
}
