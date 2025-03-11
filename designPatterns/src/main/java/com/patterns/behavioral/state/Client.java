package com.patterns.behavioral.state;

/**
 * Consider that client is trying to interact with coffee machine and performing following steps.
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("State design pattern started");

				CoffeeMachine machine = new CoffeeMachine();

				//Wrong step
				machine.selectCoffee();

				//correct step
				machine.insertCoin();

				//Again wrong step
				machine.dispense();

				//Correct step
				machine.selectCoffee();

				//Correct step.
				machine.dispense();

				System.out.println("State design pattern ended");
		}
}
