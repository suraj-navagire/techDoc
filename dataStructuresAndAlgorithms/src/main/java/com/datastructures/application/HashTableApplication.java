package com.datastructures.application;

import com.datastructures.HashTable;

public class HashTableApplication {
		public static void main(String[] args) {
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
		}
}
