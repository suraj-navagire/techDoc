package org.example.streams;

import java.util.List;
import java.util.stream.Collectors;

import org.example.data.Employee;
import org.example.data.Gender;
import org.example.util.MockDataGenerator;

/**
 * This class is used to demo filter method of stream.
 * @author Asus
 *
 */
public class FilterDemo {
	
	public static void main(String[] args) {
		FilterDemo demo = new FilterDemo();
		demo.startApp();
	}
	
	private void startApp() {
		List<Employee> employees  = MockDataGenerator.getListOfEmployees(50);
		System.out.println("All employees count : " + employees.size());
		System.out.println("All employees : ");
		employees.stream().forEach(System.out::println);
		
		printFemaleEmployee(employees);
		printMaleEmployee(employees);
	}
	
	private void printFemaleEmployee(List<Employee> employees) {
		List<Employee> femaleEmployee = employees.stream().filter(r -> Gender.FEMALE.equals(r.getGender())).collect(Collectors.toList());
		System.out.println();
		System.out.println("Female Employee count : " + femaleEmployee.size());
		System.out.println("Female Employee : ");
		femaleEmployee.stream().forEach(System.out::println);
	}

	private void printMaleEmployee(List<Employee> employees){
			List<Employee> maleEmployees = employees.stream().filter(r -> Gender.MALE.equals(r.getGender()))
					.collect(Collectors.toList());

			System.out.println();
			System.out.println("Male Employee count : " + maleEmployees.size());
			System.out.println("Male Employee : ");

			maleEmployees.stream().forEach(System.out::println);
	}

}
