package com.patterns.solid;

public class InterfaceSegregationCorrect {
		public static void main(String[] args) {
				Animal animal = new Dog();
				animal.walk();
		}
}


interface Fish {
		void swim();
}

interface Animal {
		void walk();
}

interface Bird {
		void fly();
}

class Dog implements Animal{

		@Override public void walk() {
				System.out.println("Walk");
		}
}