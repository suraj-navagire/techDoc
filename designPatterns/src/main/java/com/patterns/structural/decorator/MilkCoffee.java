package com.patterns.structural.decorator;

/**
 * This is concrete decorator class which is adding new functionality.
 */
public class MilkCoffee extends CoffeeDecorator{
		public MilkCoffee(Coffee coffee) {
				super(coffee);
		}

		@Override
		public int getCost(){
				return coffee.getCost() + 5;
		}

		@Override
		public void prepare(){
				coffee.prepare();
				System.out.println("Adding Milk to it.");
		}

}
