package com.systemdesign.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
		private static List<ParkingFloor> floors;

		private static List<EntryGate> entryGates;

		private static List<ExitGate> exitGates;

		public ParkingLot() {
				this.floors = new ArrayList<>();
				for(int i=0;i<10;i++){
						ParkingFloor floor = new ParkingFloor("floor-"+i);
						for (int j=0;j<10;j++){
								ParkingSlot slot = new ParkingSlot(i+"-"+j, VehicleType.CAR, floor);
								floor.addParkingSlot(slot);
						}
						for (int j=0;j<10;j++){
								ParkingSlot slot = new ParkingSlot(i+"-"+j+10, VehicleType.BIKE, floor);
						}
						for (int j=0;j<10;j++){
								ParkingSlot slot = new ParkingSlot(i+"-"+j+20, VehicleType.TRUCK, floor);
						}

						DisplayBoard board = new DisplayBoard(floor.getParkingSlots().size(), "floor-"+i);
						floor.addObserver(board);
						floors.add(floor);
				}

				entryGates = new ArrayList<>();
				EntryGate entryGate = new EntryGate("Entry gate : 1", new NearestSlotAssignmentStrategy());
				EntryGate entryGate2 = new EntryGate("Entry gate : 2", new RandomSlotAssignmentStrategy());
				entryGates.add(entryGate);
				entryGates.add(entryGate2);

				exitGates = new ArrayList<>();

				ExitGate exitGate = new ExitGate("Exit-1");
				exitGates.add(exitGate);

				ExitGate exitGate2 = new ExitGate("Exit-2");
				exitGates.add(exitGate2);
		}

		public List<ParkingFloor> getFloors() {
				return floors;
		}

		public List<EntryGate> getEntryGates() {
				return entryGates;
		}

		public List<ExitGate> getExitGates() {
				return exitGates;
		}
		
}
