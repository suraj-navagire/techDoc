package org.example.set;

import java.util.TreeSet;

public class TreeSetExample {
		public static void main(String[] args) {
				System.out.println("TreeSetExample Started");

				TreeSet<Employee> treeSet = new TreeSet<>();

				treeSet.add(new Employee("Employee32", 32));
				treeSet.add(new Employee("Employee25", 25));
				treeSet.add(new Employee("Employee28", 28));
				treeSet.add(new Employee("Employee30", 30));

				System.out.println(treeSet);
		}

}
