package com.patterns.behavioral.state;

/**
 * Contains all states along with current state.
 *
 * Action will be performed based on current state.
 */
public class CoffeeMachine {

		private IdleState idleState;
		private SelectionState selectionState;
		private DispenseState dispenseState;

		private CoffeeMachineState currentState;

		public CoffeeMachine() {
				this.idleState = new IdleState(this);
				this.selectionState = new SelectionState(this);
				this.dispenseState = new DispenseState(this);

				this.currentState = idleState;
		}

		public void insertCoin() {
				currentState.insertCoin();
		}

		public void selectCoffee() {
				currentState.selectCoffee();
		}

		public void dispense() {
				currentState.dispense();
		}

		public void setCurrentState(CoffeeMachineState state){
				this.currentState = state;
		}

		public IdleState getIdleState() {
				return idleState;
		}

		public SelectionState getSelectionState() {
				return selectionState;
		}

		public DispenseState getDispenseState() {
				return dispenseState;
		}
}
