package com.systemdesign.lld.elevatorsystem;

public class ElevatorRunner implements Runnable{
		private Elevator elevator;

		public ElevatorRunner(Elevator elevator) {
				this.elevator = elevator;
		}

		@Override public void run() {
				System.out.println("Elevator runner started for elevator : "+ this.elevator.getId());
				while (true){
						if(this.elevator.getState() == ElevatorState.WORKING){
								elevator.move();
						}
				}

		}
}
