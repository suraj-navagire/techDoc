package com.patterns.creational.prototype;

import java.util.List;

public class CompanyShallowExample implements Cloneable{

		private String name;
		private String dateOfInception;
		private List<Employee> employees;

		public CompanyShallowExample(String name, String dateOfInception, List<Employee> employees) {
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

		@Override public CompanyShallowExample clone() {
				try {
						CompanyShallowExample clone = (CompanyShallowExample) super.clone();
						// TODO: copy mutable state here, so the clone can't change the internals of the original
						return clone;
				} catch (CloneNotSupportedException e) {
						throw new AssertionError();
				}
		}
}
