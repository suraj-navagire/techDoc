package com.systemdesign.lld.elevatorsystem;

import java.util.Collections;
import java.util.TreeSet;

public class Elevator {
		private String id;
		private Direction direction;
		private ElevatorState state;
		private int currentFloor;
		private TreeSet<Integer> requestedUpperFloors;
		private TreeSet<Integer> requestedDownFloors;

		public Elevator(String id, Direction direction, ElevatorState state, int currentFloor) {
				this.id = id;
				this.direction = direction;
				this.state = state;
				this.currentFloor = currentFloor;

				this.requestedUpperFloors = new TreeSet<>();
				this.requestedDownFloors = new TreeSet<>(Collections.reverseOrder());
		}

		public void addFloor(int requestedFloor){
				if(this.currentFloor < requestedFloor){
						this.requestedUpperFloors.add(requestedFloor);
				}else {
						this.requestedDownFloors.add(requestedFloor);
				}
		}

		public void move(){
				if(this.requestedUpperFloors.isEmpty() && this.requestedDownFloors.isEmpty()){
						this.direction = Direction.IDLE;
						return;
				}

				if(this.direction == Direction.IDLE && this.requestedUpperFloors.isEmpty() ){
						this.direction = Direction.DOWN;
				}

				if(this.direction == Direction.IDLE && this.requestedDownFloors.isEmpty()){
						this.direction = Direction.UP;
				}

				if(this.direction == Direction.UP){
						this.currentFloor++;
						System.out.println("Elevator "+this.getId()+" reached : "+this.currentFloor);
						int requestedFloor = this.requestedUpperFloors.first();
						if(this.currentFloor == requestedFloor){
								openDoor();
								this.requestedUpperFloors.remove(requestedFloor);
								try {
										Thread.sleep(1000);
								} catch (InterruptedException e) {
										throw new RuntimeException(e);
								}
								closeDoor();
						}
				} else {
						this.currentFloor--;
						System.out.println("Elevator "+this.getId()+" reached : "+this.currentFloor);
						int requestedFloor = this.requestedDownFloors.first();
						if(this.currentFloor == requestedFloor){
								openDoor();
								this.requestedDownFloors.remove(requestedFloor);
								try {
										Thread.sleep(1000);
								} catch (InterruptedException e) {
										throw new RuntimeException(e);
								}
								closeDoor();
						}
				}
		}

		public void openDoor(){
				System.out.println("Door is opening at floor : "+this.currentFloor);

		}

		public void closeDoor() {
				System.out.println("Door is closing at floor : "+this.currentFloor);
		}

		public void setState(ElevatorState state){
				this.state = state;
		}

		public ElevatorState getState(){
				return this.state;
		}

		public Direction getDirection(){
				return this.direction;
		}

		public void setDirection(Direction direction) {
				this.direction = direction;
		}

		public int getCurrentFloor(){
				return this.currentFloor;
		}

		public String getId() {
				return id;
		}
}
