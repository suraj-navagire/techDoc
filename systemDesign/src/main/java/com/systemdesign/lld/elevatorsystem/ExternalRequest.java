package com.systemdesign.lld.elevatorsystem;

public class ExternalRequest {
		private int floor;

		private Direction direction;

		public ExternalRequest(int floor, Direction direction) {
				this.floor = floor;
				this.direction = direction;
		}

		public int getFloor() {
				return floor;
		}

		public void setFloor(int floor) {
				this.floor = floor;
		}

		public Direction getDirection() {
				return direction;
		}

		public void setDirection(Direction direction) {
				this.direction = direction;
		}

		@Override public String toString() {
				return "ExternalRequest{" + "floor=" + floor + ", direction=" + direction + '}';
		}
}
