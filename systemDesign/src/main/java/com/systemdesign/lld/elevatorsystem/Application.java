package com.systemdesign.lld.elevatorsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
		public static void main(String[] args) {
				System.out.println("Elevator system application started");
				List<Elevator> elevators = new ArrayList<>();
				ExecutorService executor = Executors.newFixedThreadPool(5);


				Elevator elevatorA = new Elevator("1",Direction.UP,ElevatorState.WORKING,2);
				ElevatorRunner runnerA = new ElevatorRunner(elevatorA);

				Elevator elevatorB = new Elevator("2",Direction.DOWN,ElevatorState.WORKING,8);
				ElevatorRunner runnerB = new ElevatorRunner(elevatorB);

				Elevator elevatorC = new Elevator("3",Direction.IDLE,ElevatorState.WORKING,5);
				ElevatorRunner runnerC = new ElevatorRunner(elevatorC);


				elevators.add(elevatorA);
				elevators.add(elevatorB);
				elevators.add(elevatorC);

				executor.submit(runnerA);
				executor.submit(runnerB);
				executor.submit(runnerC);


				ElevatorScheduler scheduler = new CostBasedElevatorScheduler();
				ElevatorSystem system = new ElevatorSystem(elevators, scheduler);


				ExternalRequest request = new ExternalRequest(6,Direction.UP);
				ExternalResponse response = system.assignElevator(request);

				try {
						Thread.sleep(5000);

						Elevator elevator = elevators.get(Integer.parseInt(response.getElevatorId())-1);
						elevator.addFloor(15);

						executor.awaitTermination(1, TimeUnit.DAYS);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Elevator system application started");
		}
}
