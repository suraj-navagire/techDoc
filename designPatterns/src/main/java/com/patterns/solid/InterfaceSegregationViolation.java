package com.patterns.solid;

public class InterfaceSegregationViolation {
		public static void main(String[] args) {
				IAnimal animal = new Dog1();
				animal.walk();

				//Unnecessary implementation as this is of no use
				animal.swim();

				//Unnecessary implementation as this is of no use
				animal.fly();
		}
}

interface IAnimal {
		void walk();
		void swim();
		void fly();
}

class Dog1 implements IAnimal {

		@Override public void walk() {
				System.out.println("Walk");
		}

		@Override public void swim() {
			//Unnecessary implementation as Dog cannon walk.
		}

		@Override public void fly() {
				//Unnecessary implementation as Dog cannon walk.
		}
}