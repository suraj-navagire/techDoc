package com.patterns.behavioral.state;

/**
 * Contains object of machine on which operations need to be performed.
 */
public class DispenseState implements CoffeeMachineState{

		private CoffeeMachine machine;

		public DispenseState(CoffeeMachine machine) {
				this.machine = machine;
		}

		/**
		 * If machine is in dispense state it will do following operation on dispense action.
		 */
		@Override public void insertCoin() {
				System.out.println("Coffee is ready please click on dispense to collect it.");
		}

		/**
		 * If machine is in dispense state it will do following operation on dispense action.
		 */
		@Override public void selectCoffee() {
				System.out.println("Coffee is ready please click on dispense to collect it.");
		}

		/**
		 * If machine is in dispense state it will do following operation on dispense action.
		 */
		@Override public void dispense() {
				System.out.println("Thanks for using this machine. Have a nice day ahead.");
				machine.setCurrentState(machine.getIdleState());
		}
}
