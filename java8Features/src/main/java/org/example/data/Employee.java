package org.example.data;

import lombok.Data;
import lombok.NonNull;

@Data
public class Employee implements Comparable<Employee>{

	@NonNull
	private Integer id;

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;

	private String middleName;

	private int age;

	private Gender gender;

	private ContactDetail contactDetail;

	@Override
	public int compareTo(Employee o) {
		return this.age - o.age;
	}
}
