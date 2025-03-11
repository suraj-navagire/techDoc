package com.patterns.behavioral.state;

/**
 * It contains methods that need to be implemented by each state. Each state will have different behavior for same action.
 *
 * So user can perform following 3 actions at any given time with coffee machine.
 * Behavior will vary based on machine's state:-
 *
 * insertCoin
 * selectCoffee
 * dispense
 */
public interface CoffeeMachineState {
		void insertCoin();

		void selectCoffee();

		void dispense();
}
