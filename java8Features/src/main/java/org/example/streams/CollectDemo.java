package org.example.streams;

import org.example.data.Employee;
import org.example.util.MockDataGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectDemo {
		public static void main(String[] args) {
				System.out.println("CollectDemo started");

				CollectDemo demo = new CollectDemo();

				List<Employee> employees  = MockDataGenerator.getListOfEmployees(50);
				System.out.println("All employees count : " + employees.size());
				System.out.println("All employees : ");
				employees.stream().forEach(System.out::println);


				demo.collectToList(employees);
				demo.collectToMap(employees);
		}

		private void collectToList(List<Employee> employees){

				List<Integer> employeeIds = employees.stream().map(e -> e.getId()).collect(Collectors.toList());

				employeeIds.forEach(System.out::println);
		}

		private void collectToMap(List<Employee> employees){
				//Converting list to map where key will be id and value will be employee object
				Map<Integer, Employee> employeeMap = employees.stream()
						.collect(Collectors.toMap(k -> k.getId(), v -> v));

				employeeMap.entrySet().forEach(System.out::println);
		}
}
