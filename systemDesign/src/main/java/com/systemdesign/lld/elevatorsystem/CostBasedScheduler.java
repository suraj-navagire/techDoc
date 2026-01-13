package com.systemdesign.lld.elevatorsystem;

import java.util.List;

public class CostBasedScheduler implements ElevatorScheduler{
		@Override public Elevator assignElevator(Request request, List<Elevator> elevators) {

				int minCost = Integer.MAX_VALUE;

				Elevator selectedElevator = null;

				for(Elevator elevator : elevators){

						if(elevator.getState() == ElevatorState.MAINTENANCE){
								continue;
						}

						int cost = calculateCost(request, elevator);

						if(cost < minCost){
								minCost = cost;
								selectedElevator = elevator;
						}
				}
				return selectedElevator;
		}

		private int calculateCost(Request request, Elevator elevator) {

				int penalty = 1000;

				if(elevator.getDirection() == Direction.IDLE){
						return Math.abs(request.getFloor() - elevator.getCurrentFloor());
				}

				if(elevator.getDirection() == request.getDirection() && isOnTheWay(request, elevator)){
						return Math.abs(request.getFloor() - elevator.getCurrentFloor());
				}

				//opposite direction penalty
				return penalty + Math.abs(request.getFloor() - elevator.getCurrentFloor());
		}

		private boolean isOnTheWay(Request request, Elevator elevator){
				return (elevator.getDirection() == Direction.UP && request.getFloor() > elevator.getCurrentFloor()) ||
						(elevator.getDirection() == Direction.DOWN && request.getFloor() < elevator.getCurrentFloor());
		}
}
