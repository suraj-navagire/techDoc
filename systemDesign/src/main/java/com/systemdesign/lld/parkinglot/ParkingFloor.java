package com.systemdesign.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ParkingFloor extends Observable {
		private String floorId;

		private List<ParkingSlot> parkingSlots;

		public ParkingFloor(String floorId) {
				this.floorId = floorId;
				this.parkingSlots = new ArrayList<>();
		}

		@Override
		public void notifyObservers(){
			long freeSlots = parkingSlots.stream().filter(parkingSlot -> parkingSlot.getCurrentParkingSlotState().isAvailable()).count();
			setChanged();
			notifyObservers((int)freeSlots);
		}

		public String getFloorId() {
				return floorId;
		}

		public List<ParkingSlot> getParkingSlots() {
				return parkingSlots;
		}

		public void addParkingSlot(ParkingSlot parkingSlot) {
				this.parkingSlots.add(parkingSlot);
		}
}
