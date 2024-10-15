package com.patterns.creational.prototype.copyConstructor;

import java.util.ArrayList;
import java.util.List;

public class PrototypeTest {
		public static void main(String[] args) {
				System.out.println("PrototypeTest started");

				Employee e1 = new Employee("James", 23, new Address("London"), "1");

				Employee e2 = new Employee("Kevin", 33, new Address("Paris"), "1");

				List<Employee> empList = new ArrayList<>();
				empList.add(e1);
				empList.add(e2);

				Manager m1 = new Manager("Ned", 53, new Address("New york"), null, empList);

				System.out.println("Old Manager Hash code : "+m1.hashCode());

				Manager m2 = m1.copy();

				System.out.println("New Manager Hash code : "+m2.hashCode());

		}
}
