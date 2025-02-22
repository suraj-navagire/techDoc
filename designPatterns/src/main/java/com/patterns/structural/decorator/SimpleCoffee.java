package com.patterns.structural.decorator;

/**
 * Concrete class for component. No one will extend this class to provide additional functionalities.
 */
public class SimpleCoffee implements Coffee{
		@Override public int getCost() {
				return 10;
		}

		@Override public void prepare() {
				System.out.println("Preparing Simple Coffee");
		}
}
