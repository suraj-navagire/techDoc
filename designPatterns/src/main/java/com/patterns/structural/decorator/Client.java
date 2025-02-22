package com.patterns.structural.decorator;

public class Client {
		public static void main(String[] args) {
				System.out.println("Client started decorator design pattern");

				Coffee coffee = new SimpleCoffee();
				System.out.println("Coffee cost : "+coffee.getCost());
				coffee.prepare();

				System.out.println("New order");

				Coffee milkAndSugarCoffee = new SugarCoffee(new MilkCoffee(new SimpleCoffee()));
				System.out.println("Milk and Sugar coffee cost : "+milkAndSugarCoffee.getCost());
				milkAndSugarCoffee.prepare();

				System.out.println("Client started decorator design pattern");
		}
}
