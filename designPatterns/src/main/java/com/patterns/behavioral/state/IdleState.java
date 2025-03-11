package com.patterns.behavioral.state;

/**
 * Contains object of machine on which operations need to be performed.
 */
public class IdleState implements CoffeeMachineState{

		private CoffeeMachine machine;

		public IdleState(CoffeeMachine machine) {
				this.machine = machine;
		}

		/**
		 * If machine is in idle state it will do following operation on insert coin action.
		 */
		@Override public void insertCoin() {
				System.out.println("Coin is inserted. Please select the coffee.");
				machine.setCurrentState(machine.getSelectionState());
		}

		/**
		 * If machine is in idle state it will do following operation on select Coffee action.
		 */
		@Override public void selectCoffee() {
				System.out.println("Please insert coin");
		}

		/**
		 * If machine is in idle state it will do following operation on dispense action.
		 */
		@Override public void dispense() {
				System.out.println("Please insert coin");
		}
}
