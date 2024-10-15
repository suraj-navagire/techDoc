package com.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class ShallowCopy {
		public static void main(String[] args) {
				System.out.println("Test started");

				Employee e = new Employee("Mark", 29, new Address("London"));
				Employee e1 = new Employee("Kevin", 43, new Address("Berlin"));

				List<Employee> empList = new ArrayList<>();
				empList.add(e);
				empList.add(e1);

				CompanyShallowExample companyShallowExample1 = new CompanyShallowExample("Meta", "2nd Jan 2000", empList);

				System.out.println("Old companyShallowExample Hash code : "+companyShallowExample1.hashCode());

				CompanyShallowExample companyShallowExample = companyShallowExample1.clone();
				System.out.println("New companyShallowExample Hash code : "+companyShallowExample.hashCode());

				System.out.println("New companyShallowExample properties : "+companyShallowExample.getName()+","+companyShallowExample.getDateOfInception());

				System.out.println("Old companyShallowExample.employees Hash code : "+companyShallowExample1.getEmployees().hashCode());

				System.out.println("New companyShallowExample.employees Hash code : "+companyShallowExample.getEmployees().hashCode());

				System.out.println("Old companyShallowExample.employees.address Hash code : "+companyShallowExample1.getEmployees().get(0).getName()+", "+companyShallowExample1.getEmployees().get(0).getAddress().hashCode());

				System.out.println("New companyShallowExample.employees.address Hash code : "+companyShallowExample.getEmployees().get(0).getName()+", "+companyShallowExample.getEmployees().get(0).getAddress().hashCode());


		}
}
