package org.example.map;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapExample {
		public static void main(String[] args) {
				System.out.println("TreeMapExample Started");

				TreeMap<Integer, String> naturalSorting = new TreeMap<>();
				naturalSorting.put(3, "Three");
				naturalSorting.put(9, "Nine");
				naturalSorting.put(5, "Five");

				System.out.println("naturalSorting" +naturalSorting);


				TreeMap<Integer, String> customSorting = new TreeMap<>(new CustomSorting());
				customSorting.put(3, "Three");
				customSorting.put(9, "Nine");
				customSorting.put(5, "Five");

				System.out.println("customSorting" +customSorting);

				System.out.println("customSorting.comparator()" +customSorting.comparator());
				System.out.println("TreeMapExample Ended");
		}
}

 class CustomSorting implements Comparator<Integer> {

		 @Override
		 public int compare(Integer object1, Integer object2) {
				 return -(object1 - object2);
		 }
 }