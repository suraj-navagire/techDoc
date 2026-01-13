package com.systemdesign.lld.elevatorsystem;

import java.util.List;

public interface ElevatorScheduler {
		Elevator assignElevator(Request request, List<Elevator> elevators);
}
