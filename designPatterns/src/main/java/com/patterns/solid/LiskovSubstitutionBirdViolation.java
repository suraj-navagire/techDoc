package com.patterns.solid;

public class LiskovSubstitutionBirdViolation {
		public static void main(String[] args) {
				//Client is calling bird object on Bird reference

				Bird1 bird1 = new Sparrow();
				Bird1 bird2 = new Penguin();

				bird1.fly();
				bird2.fly();
		}
}

interface Bird1 {
		void eat();

		void fly();
}

class Sparrow implements Bird1{

		@Override public void eat() {
				System.out.println("Sparrow eating");
		}

		@Override public void fly() {
				System.out.println("Sparrow flying");
		}
}

class Penguin implements Bird1{

		@Override public void eat() {
				System.out.println("Penguin eating");
		}

		@Override public void fly() {
			throw new UnsupportedOperationException("Penguin cannot fly");
		}
}