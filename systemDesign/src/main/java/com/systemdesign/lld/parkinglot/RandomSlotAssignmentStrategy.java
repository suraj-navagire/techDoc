package com.systemdesign.lld.parkinglot;

import java.util.List;

public class RandomSlotAssignmentStrategy implements ISlotAssignmentStrategy{
		@Override public ParkingSlot assignSlot(Vehicle vehicle, List<ParkingSlot> parkingSlots) {
				return parkingSlots.stream().filter(p -> p.getCurrentParkingSlotState().isAvailable()).findFirst().orElse(null);
		}
}
