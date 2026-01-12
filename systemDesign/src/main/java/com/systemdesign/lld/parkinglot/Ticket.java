package com.systemdesign.lld.parkinglot;

import java.time.LocalDateTime;
import java.util.UUID;

public class  Ticket {
		private String id;

		private LocalDateTime entryTime;

		private Vehicle vehicle;

		private ParkingSlot slot;

		public Ticket(LocalDateTime entryTime, Vehicle vehicle, ParkingSlot slot) {
				this.id = UUID.randomUUID().toString();
				this.entryTime = entryTime;
				this.vehicle = vehicle;
				this.slot = slot;
		}

		public String getId() {
				return id;
		}

		public LocalDateTime getEntryTime() {
				return entryTime;
		}

		public Vehicle getVehicle() {
				return vehicle;
		}

		public ParkingSlot getSlot() {
				return slot;
		}
}
