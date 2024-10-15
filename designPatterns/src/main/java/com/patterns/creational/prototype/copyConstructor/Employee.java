package com.patterns.creational.prototype.copyConstructor;

public class Employee implements Prototype{
		private String name;
		private int age;

		private Address address;

		private String managerId;

		public Employee(String name, int age, Address address, String managerId) {
				this.name = name;
				this.age = age;
				this.address = address;
				this.managerId = managerId;
		}

		//Copy constructor
		public Employee(Employee e) {
				this.name = e.name;
				this.age = e.age;
				this.address = e.address.copy();
				this.managerId = e.managerId;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public int getAge() {
				return age;
		}

		public void setAge(int age) {
				this.age = age;
		}

		public Address getAddress() {
				return address;
		}

		public void setAddress(Address address) {
				this.address = address;
		}

		@Override public Employee copy() {
				return new Employee(this);
		}
}
