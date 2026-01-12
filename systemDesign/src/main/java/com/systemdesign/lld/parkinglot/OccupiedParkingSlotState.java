package com.systemdesign.lld.parkinglot;

public class OccupiedParkingSlotState implements IParkingSlotState{

		private ParkingSlot parkingSlot;

		public OccupiedParkingSlotState(ParkingSlot parkingSlot) {
				this.parkingSlot = parkingSlot;
		}

		@Override
		public void parkVehicle(Vehicle vehicle) {
				throw  new IllegalStateException("Slot is not available");
		}

		@Override public Vehicle unParkVehicle() {
				Vehicle vehicle = parkingSlot.getVehicle();
				parkingSlot.setVehicle(null);
				parkingSlot.setCurrentParkingSlotState(parkingSlot.getFreeParkingSlotState());
				System.out.println("Unparking vehicle : "+vehicle.getRegistrationNumber()+" from Slot : "+parkingSlot.getSlotId());
				parkingSlot.getFloor().notifyObservers();
				return vehicle;
		}

		@Override public boolean isAvailable() {
				return false;
		}
}
