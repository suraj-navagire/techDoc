package com.systemdesign.lld.parkinglot;

import java.util.List;

public class Client {

		public static void main(String[] args) {
				System.out.println("Parking lot application started");
				ParkingLot parkingLot = new ParkingLot();
				List<ParkingFloor> floors = parkingLot.getFloors();
				EntryGate entryGate = parkingLot.getEntryGates().get(0);
				ExitGate exitGate = parkingLot.getExitGates().get(1);

				Vehicle vehicle = new Vehicle("MH02-0198", VehicleType.CAR);
				Ticket ticket = entryGate.issueTicket(vehicle, floors);
				exitGate.processExit(ticket, PaymentType.CASH);

				System.out.println("Parking lot application ended");
		}

}
