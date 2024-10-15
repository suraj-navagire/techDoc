package com.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class DeepCopy {
		public static void main(String[] args) {
				System.out.println("DeepCopy started");

				Employee e = new Employee("Mark", 29, new Address("London"));
				Employee e1 = new Employee("Kevin", 43, new Address("Berlin"));

				List<Employee> empList = new ArrayList<>();
				empList.add(e);
				empList.add(e1);

				CompanyDeepExample companyDeepExample1 = new CompanyDeepExample("Meta", "2nd Jan 2000", empList);

				System.out.println("Old companyDeepExample1 Hash code : "+companyDeepExample1.hashCode());

				CompanyDeepExample companyDeepExample = companyDeepExample1.clone();
				System.out.println("New companyShallowExample Hash code : "+companyDeepExample.hashCode());

				System.out.println("New companyShallowExample properties : "+companyDeepExample.getName()+","+companyDeepExample.getDateOfInception());

				System.out.println("Old companyShallowExample.employees Hash code : "+companyDeepExample1.getEmployees().hashCode());

				System.out.println("New companyShallowExample.employees Hash code : "+companyDeepExample.getEmployees().hashCode());

				System.out.println("Old companyShallowExample.employees.address Hash code : "+companyDeepExample1.getEmployees().get(0).getName()+", "+companyDeepExample1.getEmployees().get(0).getAddress().hashCode());

				System.out.println("New companyShallowExample.employees.address Hash code : "+companyDeepExample.getEmployees().get(0).getName()+", "+companyDeepExample.getEmployees().get(0).getAddress().hashCode());

		}
}
