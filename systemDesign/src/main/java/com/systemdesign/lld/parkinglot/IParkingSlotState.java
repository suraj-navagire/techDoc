package com.systemdesign.lld.parkinglot;

public interface IParkingSlotState {
		void parkVehicle(Vehicle vehicle);

		Vehicle unParkVehicle();

		boolean isAvailable();
}
