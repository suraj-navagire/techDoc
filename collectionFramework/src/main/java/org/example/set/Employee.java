package org.example.set;

public class Employee implements Comparable<Employee>{

		private String name;

		private Integer age;

		public Employee(String name, Integer age ) {
				this.name = name;
				this.age = age;
		}

		public String getName() {
				return name;
		}

		public Integer getAge() {
				return age;
		}

		public void setName(String name) {
				this.name = name;
		}

		public void setAge(Integer age) {
				this.age = age;
		}

		@Override public int compareTo(Employee o) {
				return this.age - o.age;
		}

		@Override public String toString() {
				return "Employee{" + "name='" + name + '\'' + ", age=" + age + '}';
		}
}
