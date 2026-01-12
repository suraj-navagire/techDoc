package com.systemdesign.lld.parkinglot;

public class ParkingSlot implements Comparable<ParkingSlot> {
		private String slotId;

		private ParkingFloor floor;

		private VehicleType vehicleType;

		private Vehicle vehicle;

		private IParkingSlotState currentParkingSlotState;

		private FreeParkingLotState freeParkingSlotState;

		private OccupiedParkingSlotState occupiedParkingSlotState;

		public ParkingSlot(String slotId, VehicleType vehicleType, ParkingFloor floor) {
				this.slotId = slotId;
				this.vehicleType = vehicleType;
				this.freeParkingSlotState = new FreeParkingLotState(this);
				this.occupiedParkingSlotState = new OccupiedParkingSlotState(this);
				this.currentParkingSlotState = freeParkingSlotState;
				this.floor = floor;
		}

		public String getSlotId() {
				return slotId;
		}

		public VehicleType getVehicleType() {
				return vehicleType;
		}

		public Vehicle getVehicle() {
				return vehicle;
		}

		public void setVehicle(Vehicle vehicle) {
				this.vehicle = vehicle;
		}

		public void setCurrentParkingSlotState(IParkingSlotState currentParkingSlotState) {
				this.currentParkingSlotState = currentParkingSlotState;
		}

		public FreeParkingLotState getFreeParkingSlotState() {
				return freeParkingSlotState;
		}

		public OccupiedParkingSlotState getOccupiedParkingSlotState() {
				return occupiedParkingSlotState;
		}

		@Override public int compareTo(ParkingSlot o) {
				return this.slotId.compareTo(o.slotId);
		}

		public void parkVehicle(Vehicle vehicle){
				currentParkingSlotState.parkVehicle(vehicle);
		}

		public Vehicle unParkVehicle(){
				return currentParkingSlotState.unParkVehicle();
		}

		public ParkingFloor getFloor(){
				return this.floor;
		}

		public IParkingSlotState getCurrentParkingSlotState() {
				return currentParkingSlotState;
		}
}
