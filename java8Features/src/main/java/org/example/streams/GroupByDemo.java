package org.example.streams;

import org.example.data.Employee;
import org.example.data.Gender;
import org.example.util.MockDataGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByDemo {


		public static void main(String[] args) {
				try{
						GroupByDemo demo = new GroupByDemo();
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

				groupEmployeeUsingGender(employees);
		}

		private void groupEmployeeUsingGender(List<Employee> employees){
				Map<Gender, List<Employee>> employeeMap = employees.stream()
						.collect(Collectors.groupingBy(e->e.getGender()));

				System.out.println();
				System.out.println("All Genders : ");
				employeeMap.keySet().forEach(System.out::println);
		}
}
