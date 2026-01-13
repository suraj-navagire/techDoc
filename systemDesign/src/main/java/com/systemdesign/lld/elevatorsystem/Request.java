package com.systemdesign.lld.elevatorsystem;

public class Request {
		private final Direction direction;

		private final int floor;

		public Request(Direction direction, int floor) {
				this.direction = direction;
				this.floor = floor;
		}

		public Direction getDirection() {
				return direction;
		}

		public int getFloor() {
				return floor;
		}
}
