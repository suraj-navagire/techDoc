package com.systemdesign.lld.elevatorsystem;

public class Elevator {
		private String elevatorId;
		private ElevatorState state;
		private int currentFloor;
		private Direction direction;

		public String getElevatorId() {
				return elevatorId;
		}

		public void setElevatorId(String elevatorId) {
				this.elevatorId = elevatorId;
		}

		public ElevatorState getState() {
				return state;
		}

		public void setState(ElevatorState state) {
				this.state = state;
		}

		public int getCurrentFloor() {
				return currentFloor;
		}

		public void setCurrentFloor(int currentFloor) {
				this.currentFloor = currentFloor;
		}

		public Direction getDirection() {
				return direction;
		}

		public void setDirection(Direction direction) {
				this.direction = direction;
		}
}
