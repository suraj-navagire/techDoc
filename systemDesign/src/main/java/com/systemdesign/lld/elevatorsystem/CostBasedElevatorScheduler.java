package com.systemdesign.lld.elevatorsystem;

import java.util.List;

public class CostBasedElevatorScheduler implements ElevatorScheduler{
		@Override public Elevator assignElevator(ExternalRequest externalRequest, List<Elevator> elevators) {

				Elevator selectedElevator = null;
				int minimumCost = Integer.MAX_VALUE;
				int penalty = 1000;

				for (Elevator elevator : elevators){
						int tempCost;
						if(elevator.getState() == ElevatorState.MAINTENANCE){
								continue;
						}

						if (elevator.getDirection() == Direction.NO_DIRECTION){
								tempCost = Math.abs(externalRequest.getFloor() - elevator.getCurrentFloor());
						} else if(elevator.getDirection() == externalRequest.getDirection() &&
								canPassFrom(externalRequest, elevator.getCurrentFloor())){
								tempCost = Math.abs(externalRequest.getFloor() - elevator.getCurrentFloor());
						} else {
								tempCost = Math.abs(externalRequest.getFloor() - elevator.getCurrentFloor()) + penalty;
						}

						if(tempCost < minimumCost){
								minimumCost = tempCost;
								selectedElevator = elevator;
						}

				}
				return selectedElevator;
		}


		private boolean canPassFrom(ExternalRequest externalRequest, int elevatorCurrentFloor){
				if((externalRequest.getDirection() == Direction.UP && externalRequest.getFloor() > elevatorCurrentFloor) ||
						(externalRequest.getDirection() == Direction.DOWN && externalRequest.getFloor() < elevatorCurrentFloor)){
						return true;
				}

				return false;
		}
}
