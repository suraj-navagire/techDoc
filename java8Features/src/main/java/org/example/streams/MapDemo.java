package org.example.streams;

import org.example.data.Employee;
import org.example.util.MockDataGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class MapDemo {
	
	public static void main(String[] args) {
			try{
					MapDemo demo = new MapDemo();
					demo.startApp();
			} catch (Exception e){
					e.printStackTrace();
			}

	}
	
	private void startApp() {
		List<Employee> employees  = MockDataGenerator.getListOfEmployees(50);
		System.out.println("All employees count : " + employees.size());
		System.out.println("All employees : ");
		employees.stream().forEach(System.out::println);
		
		printIdOfAllEmployee(employees);
	}
	
	private void printIdOfAllEmployee(List<Employee> employees) {
		List<Integer> ids = employees.stream().map(e -> e.getId()).collect(Collectors.toList());
		System.out.println();
		System.out.println("All Employee Ids : ");
		ids.stream().forEach(System.out::println);
	}

}
