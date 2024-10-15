package com.patterns.creational.prototype.copyConstructor;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee{
		private List<Employee> employees;

		public Manager(String name, int age, Address address, String managerId, List<Employee> employees) {
				super(name, age, address, managerId);
				this.employees = employees;
		}

		public Manager(Manager m){
				super(m);
				List<Employee> newEmployees = new ArrayList<>();
				for(Employee e : m.getEmployees()){
						newEmployees.add(e.copy());
				}
				this.employees = newEmployees;
		}

		public List<Employee> getEmployees() {
				return employees;
		}

		public void setEmployees(List<Employee> employees) {
				this.employees = employees;
		}

		@Override public Manager copy() {
				return new Manager(this);
		}
}
