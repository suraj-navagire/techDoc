package com.datastructures.application;

import com.datastructures.HashTable;

import java.util.Iterator;

public class HashTableApplication {
		public static void main(String[] args) {
				try {
						HashTableApplication application = new HashTableApplication();
						application.start();
				} catch (Throwable e) {
						e.printStackTrace();
				}
		}

		private void start() {
				System.out.println("HashTable implementation : ");

				HashTable<String> studentCollection = new HashTable<>();

				studentCollection.put("Mark");

				studentCollection.put("Mark");

				studentCollection.put("James");

				studentCollection.put("Rob");

				studentCollection.put("Edward");

				studentCollection.put("Ricky");


				System.out.println("Is Rob present in collection : "+ studentCollection.contains("Rob"));

				System.out.println("Is Lisa present in collection : "+ studentCollection.contains("Lisa"));

				System.out.print("Iterating over Hash table using iterator : ");

				Iterator<String> iterator = studentCollection.iterator();

				System.out.print("[");
				while (iterator.hasNext()){
						String m = iterator.next();
						System.out.print(m+",");
				}

				System.out.println("]");

				System.out.print("Iterating over Hash table using advanced for loop : ");

				System.out.print("[");

				for(String a : studentCollection){
						System.out.print(a+",");
				}

				System.out.println("]");


				System.out.print("Iterating over Hash table using forEach method : ");
				System.out.print("[");
				studentCollection.forEach(q -> System.out.print(q+","));
				System.out.println("]");
		}
}
