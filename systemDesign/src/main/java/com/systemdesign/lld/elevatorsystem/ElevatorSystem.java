package com.systemdesign.lld.elevatorsystem;

import java.util.List;

public class ElevatorSystem {
		private List<Elevator> elevatorList;

		private ElevatorScheduler elevatorScheduler;

		public ElevatorSystem(List<Elevator> elevatorList, ElevatorScheduler elevatorScheduler) {
				this.elevatorList = elevatorList;
				this.elevatorScheduler = elevatorScheduler;
		}

		public ExternalResponse assignElevator(ExternalRequest externalRequest){
				Elevator selectedElevator = elevatorScheduler.assignElevator(externalRequest, elevatorList);

				selectedElevator.addFloor(externalRequest.getFloor());

				return new ExternalResponse(selectedElevator.getId());
		}
}
