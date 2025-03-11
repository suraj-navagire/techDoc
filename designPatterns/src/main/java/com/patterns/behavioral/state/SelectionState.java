package com.patterns.behavioral.state;

/**
 * Contains object of machine on which operations need to be performed.
 */
public class SelectionState implements CoffeeMachineState{

		private CoffeeMachine machine;

		public SelectionState(CoffeeMachine machine) {
				this.machine = machine;
		}

		/**
		 * If machine is in selection state it will do following operation on insert coin action.
		 */
		@Override public void insertCoin() {
				System.out.println("Coin is already inserted, Please select coffee");
		}

		/**
		 * If machine is in selection state it will do following operation on select coffee action.
		 */
		@Override public void selectCoffee() {
				System.out.println("Coffee is selected. Now preparing it.");
				System.out.println("Coffee is ready. Please click dispense button to collect it");
				machine.setCurrentState(machine.getDispenseState());
		}

		/**
		 * If machine is in selection state it will do following operation on dispense action.
		 */
		@Override public void dispense() {
				System.out.println("Please select coffee first.");
		}

}
