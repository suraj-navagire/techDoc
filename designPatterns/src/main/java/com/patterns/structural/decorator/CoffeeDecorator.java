package com.patterns.structural.decorator;

/**
 * This is decorator class. This will be extended by child classes to provide new functionalities.
 */
public abstract class CoffeeDecorator implements Coffee{
		protected Coffee coffee;

		public CoffeeDecorator(Coffee coffee) {
				this.coffee = coffee;
		}
}
