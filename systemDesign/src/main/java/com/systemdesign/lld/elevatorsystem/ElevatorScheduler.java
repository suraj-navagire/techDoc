package com.systemdesign.lld.elevatorsystem;

import java.util.List;

public interface ElevatorScheduler {
		Elevator assignElevator(ExternalRequest externalRequest, List<Elevator> elevators);
}
