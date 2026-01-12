package com.systemdesign.lld.parkinglot;

import java.util.List;

public class NearestSlotAssignmentStrategy implements ISlotAssignmentStrategy{
		@Override public ParkingSlot assignSlot(Vehicle vehicle, List<ParkingSlot> parkingSlots) {
				return parkingSlots.stream().sorted().filter(parkingSlot -> parkingSlot.getCurrentParkingSlotState().isAvailable()).findFirst().orElse(null);
		}
}
