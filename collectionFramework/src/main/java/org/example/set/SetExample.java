package org.example.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetExample {
		public static void main(String[] args) {
				System.out.println("SetExample Started : ");

				Set<Integer> hashSet = new HashSet<>();
				hashSet.add(1);
				hashSet.add(8);
				hashSet.add(4);
				hashSet.add(3);
				hashSet.add(22);
				hashSet.add(17);

				System.out.println("HashSet : "+hashSet);

				Set<Integer> linkedHashSet = new LinkedHashSet<>();
				linkedHashSet.add(1);
				linkedHashSet.add(8);
				linkedHashSet.add(4);
				linkedHashSet.add(3);
				linkedHashSet.add(22);
				linkedHashSet.add(17);

				System.out.println("LinkedHashSet : "+linkedHashSet);

				SortedSet<Integer> sortedSet = new TreeSet<>();
				sortedSet.add(1);
				sortedSet.add(8);
				sortedSet.add(4);
				sortedSet.add(3);
				sortedSet.add(22);
				sortedSet.add(17);

				System.out.println("sortedSet : "+sortedSet);
				System.out.println("sortedSet.first() : "+sortedSet.first());
				System.out.println("sortedSet.last() : "+sortedSet.last());
				System.out.println("sortedSet.headSet(4) : "+sortedSet.headSet(4));
				System.out.println("sortedSet.tailSet(4) : "+sortedSet.tailSet(4));
				System.out.println("sortedSet.subSet(3, 17) : "+sortedSet.subSet(3, 17));
				System.out.println("sortedSet.comparator() : "+sortedSet.comparator());


				NavigableSet<Integer> navigableSet = new TreeSet<>();
				navigableSet.add(1);
				navigableSet.add(8);
				navigableSet.add(4);
				navigableSet.add(3);
				navigableSet.add(22);
				navigableSet.add(17);

				System.out.println("navigableSet : "+navigableSet);
				System.out.println("navigableSet.floor(4) : "+navigableSet.floor(4));
				System.out.println("navigableSet.lower(4) : "+navigableSet.lower(4));
				System.out.println("navigableSet.ceiling(8) : "+navigableSet.ceiling(8));
				System.out.println("navigableSet.higher(8) : "+navigableSet.higher(8));
				System.out.println("navigableSet.pollFirst() : "+navigableSet.pollFirst());
				System.out.println("navigableSet.pollLast() : "+navigableSet.pollLast());
				System.out.println("navigableSet after poll : "+navigableSet);
				System.out.println("navigableSet.descendingSet() : "+navigableSet.descendingSet());
		}
}
