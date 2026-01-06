package com.patterns.solid;

public class LiskovSubstitutionBirdCorrect {
		public static void main(String[] args) {
				//Client is calling bird object on Bird interface
				Flyable bird2 = new Sparrow1();

				bird2.fly();

				//In this case penguin is not child of Flyable so client will not substitute it. Problem solved
		}
}

interface Bird2{
		void eat();
}

interface Flyable{
		void fly();
}

class Sparrow1 implements Bird2, Flyable{

		@Override public void eat() {
				System.out.println("Sparrow is eating");
		}

		@Override public void fly() {
				System.out.println("Sparrow is flying");
		}
}

class Penguin1 implements Bird2{

		@Override public void eat() {
				System.out.println("Penguin is eating");
		}
}