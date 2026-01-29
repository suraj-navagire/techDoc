package com.systemdesign.lld.elevatorsystem;

public class ExternalResponse {
		private String elevatorId;

		public ExternalResponse(String elevatorId) {
				this.elevatorId = elevatorId;
		}

		public String getElevatorId() {
				return elevatorId;
		}

		public void setElevatorId(String elevatorId) {
				this.elevatorId = elevatorId;
		}

		@Override public String toString() {
				return "ExternalResponse{" + "elevatorId='" + elevatorId + '\'' + '}';
		}
}
