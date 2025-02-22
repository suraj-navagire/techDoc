package com.patterns.structural.decorator;

public class SugarCoffee extends CoffeeDecorator{

		public SugarCoffee(Coffee coffee) {
				super(coffee);
		}

		@Override
		public int getCost(){
				return coffee.getCost() + 2;
		}

		@Override
		public void prepare(){
				coffee.prepare();
				System.out.println("Adding sugar to it.");
		}
}
