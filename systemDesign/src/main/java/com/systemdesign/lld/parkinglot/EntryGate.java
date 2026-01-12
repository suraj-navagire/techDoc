package com.systemdesign.lld.parkinglot;

import java.time.LocalDateTime;
import java.util.List;

public class EntryGate {
		private String gateId;

		private ISlotAssignmentStrategy assignmentStrategy;

		public EntryGate(String gateId, ISlotAssignmentStrategy assignmentStrategy) {
				this.gateId = gateId;
				this.assignmentStrategy = assignmentStrategy;
		}

		public Ticket issueTicket(Vehicle vehicle, List<ParkingFloor> floors){
				ParkingSlot slot = null;
				for(ParkingFloor floor :floors ){
						slot = assignmentStrategy.assignSlot(vehicle, floor.getParkingSlots());
						if(slot != null){
								break;
						}
				}

				if(slot == null){
						return null;
				}

				slot.parkVehicle(vehicle);
				Ticket ticket = new Ticket(LocalDateTime.now(), vehicle, slot);
				System.out.println("Ticket - Id: "+ticket.getId()+", Slot: " +ticket.getSlot().getSlotId()+", gate : "+gateId);

				return ticket;
		}

		public String getGateId() {
				return gateId;
		}

		public ISlotAssignmentStrategy getAssignmentStrategy() {
				return assignmentStrategy;
		}
}
