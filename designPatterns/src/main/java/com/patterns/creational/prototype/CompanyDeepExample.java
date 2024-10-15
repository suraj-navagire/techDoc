package com.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class CompanyDeepExample implements Cloneable{

		private String name;
		private String dateOfInception;
		private List<Employee> employees;

		public CompanyDeepExample(String name, String dateOfInception, List<Employee> employees) {
				this.name = name;
				this.dateOfInception = dateOfInception;
				this.employees = employees;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getDateOfInception() {
				return dateOfInception;
		}

		public void setDateOfInception(String dateOfInception) {
				this.dateOfInception = dateOfInception;
		}

		public List<Employee> getEmployees() {
				return employees;
		}

		public void setEmployees(List<Employee> employees) {
				this.employees = employees;
		}

		@Override public CompanyDeepExample clone() {
				try {
						CompanyDeepExample clone = (CompanyDeepExample) super.clone();

						List<Employee> newEmployee = new ArrayList<>();
						for(Employee e : employees){
								newEmployee.add(e.clone());
						}

						clone.setEmployees(newEmployee);
						return clone;
				} catch (CloneNotSupportedException e) {
						throw new AssertionError();
				}
		}
}
