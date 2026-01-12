package com.systemdesign.lld.parkinglot;

public class FreeParkingLotState implements IParkingSlotState{
		private final ParkingSlot parkingSlot;

		public FreeParkingLotState(ParkingSlot parkingSlot) {
				this.parkingSlot = parkingSlot;
		}

		@Override
		public void parkVehicle(Vehicle vehicle) {
				System.out.println("Vehicle : "+vehicle.getRegistrationNumber()+" is parking at Slot : "+parkingSlot.getSlotId());
				parkingSlot.setVehicle(vehicle);
				parkingSlot.setCurrentParkingSlotState(parkingSlot.getOccupiedParkingSlotState());
				parkingSlot.getFloor().notifyObservers();
		}

		@Override public Vehicle unParkVehicle() {
				System.out.println("Vehicle not present");
				return null;
		}

		@Override public boolean isAvailable() {
				return true;
		}

}
