package org.example.streams;

import java.util.List;
import java.util.stream.Collectors;

import org.example.data.Employee;
import org.example.util.MockDataGenerator;

public class SortDemo {
	public static void main(String[] args) {
		SortDemo demo = new SortDemo();
		demo.startApp();
	}
	
	private void startApp() {
		List<Employee> employees  = MockDataGenerator.getListOfEmployees(50);
		System.out.println("All employees count : " + employees.size());
		System.out.println("All employees : ");
		employees.stream().forEach(System.out::println);
		
		sortEmpUsingNaturalSorting(employees);
		sortEmpUsingAgeDesc(employees);
		sortUsingGenderAndAgeAsce(employees);
	}
	
	private void sortEmpUsingNaturalSorting(List<Employee> employees) {
		List<Employee> sortedEmp = employees.stream().sorted().collect(Collectors.toList());
		System.out.println();
		System.out.println("Sorted Employee Using Age Ascending order : ");
		sortedEmp.stream().forEach(System.out::println);
	}
	
	private void sortEmpUsingAgeDesc(List<Employee> employees) {
		List<Employee> sortedEmp = employees.stream().sorted((e1, e2) -> -(e1.compareTo(e2))).collect(Collectors.toList());
		System.out.println();
		System.out.println("Sorted Employee Using Age Descending order : ");
		sortedEmp.stream().forEach(System.out::println);
	}
	
	private void sortUsingGenderAndAgeAsce(List<Employee> employees) {
		List<Employee> sortedEmp = employees.stream().sorted((e1, e2) -> {
			int re = e2.getGender().compareTo(e1.getGender());
			if(re == 0) {
				re = e1.getAge() - e2.getAge();
			}
			return re;
		}).collect(Collectors.toList());
		System.out.println();
		System.out.println("Sorted Employee Using gender and age : ");
		sortedEmp.stream().forEach(System.out::println);
	}
	
}
