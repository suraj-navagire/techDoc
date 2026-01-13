package com.systemdesign.lld.elevatorsystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorControllerSystem {
		private final List<Elevator> elevators;

		private final ElevatorScheduler scheduler;

		public ElevatorControllerSystem(ElevatorScheduler scheduler){
				this.elevators = new ArrayList<>();
				this.scheduler = scheduler;
		}

		public void addElevator(Elevator elevator){
				this.elevators.add(elevator);
		}

		public void removeElevator(Elevator elevator){
				this.elevators.remove(elevator);
		}
}
