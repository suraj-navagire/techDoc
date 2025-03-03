package com.patterns.behavioral.iterator;

public class Client {
		public static void main(String[] args) {
				System.out.println("Iterator started");

				BookCollection collection = new BookCollection(getBooks());

				Iterator booksIterator = collection.getIterator();

				while (booksIterator.hasNex()){
						System.out.println(booksIterator.next());
				}

				System.out.println("Iterator started");
		}


		private static Book[] getBooks(){
				Book[] books = new Book[10];

				for (int i=0 ; i<10; i++){
						books[i] = new Book("Book"+i, i*12);
				}

				return books;
		}
}
