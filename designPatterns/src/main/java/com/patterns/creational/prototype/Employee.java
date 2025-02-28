package com.patterns.creational.prototype;

public class Employee implements Cloneable{
		private String name;
		private int age;

		private Address address;
		public Employee(String name, int age, Address address) {
				this.name = name;
				this.age = age;
				this.address = address;
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

		@Override public Employee clone() {
				try {
						Employee clone = (Employee) super.clone();
						clone.setAddress(clone.getAddress().clone());
						return clone;
				} catch (CloneNotSupportedException e) {
						throw new AssertionError();
				}
		}

}
