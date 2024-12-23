package org.example.set;

import java.util.TreeSet;

public class ComparatorExample {

		public static void main(String[] args) {
				System.out.println("ComparatorExample Started");

				TreeSet<Integer> naturalSorting = new TreeSet<>();
				naturalSorting.add(5);
				naturalSorting.add(2);
				naturalSorting.add(12);
				naturalSorting.add(8);
				naturalSorting.add(7);
				naturalSorting.add(20);

				System.out.println("naturalSorting : "+ naturalSorting);

				TreeSet<Integer> comparator = new TreeSet<>(new MyComparator());

				comparator.add(5);
				comparator.add(2);
				comparator.add(12);
				comparator.add(8);
				comparator.add(7);
				comparator.add(20);

				System.out.println("comparator : "+ comparator);
		}
}


