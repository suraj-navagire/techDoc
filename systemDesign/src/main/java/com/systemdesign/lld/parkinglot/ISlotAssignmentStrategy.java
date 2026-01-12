package com.systemdesign.lld.parkinglot;

import java.util.List;

public interface ISlotAssignmentStrategy {
		ParkingSlot assignSlot(Vehicle vehicle, List<ParkingSlot> parkingSlots);
}
