package com.patterns.creational.factoryPattern;

public class ComputerTest {
		public static void main(String[] args) {
				System.out.println("ComputerTest Started");

				String type = "PC";

				Computer computer = ComputerFactory.getInstance(type, "16GB", "1TB", "8");

				String result = computer.toString();

				System.out.println(result);

				type = "SERVER";
				computer = ComputerFactory.getInstance(type, "64GB", "5TB", "16");
				result = computer.toString();

				System.out.println(result);

				System.out.println("ComputerTest ended");
		}
}
