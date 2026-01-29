# Design an Elevator system

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)

1. Multiple elevators?
   - Yes
2. Buttons/Request
   - External buttons : Each floor will have up/down buttons
   - Internal buttons : Floor numbers inside elevator
3. Elevator behaviour?
   - Can elevator stop on multiple floor in each trip ? Yes
   - Should it priorities direction ? Yes
4. Expectations from schedular which will manage elevator assignment
   - Waiting time : This is the primary user expectations. Less waiting time.
   - Directional compatibility : Avoid unnecessary reversal.
   - Throughput : Avoid idle elevators
   - Fairness : Prevent starvation
Note : No schedular optimizes everything perfectly.

Flow : 
External button :
- User will press up/down button on floor
- System will assign an elevator to this request
- Elevator arrives and opens door

Internal button :
- User selects destination floor
- Elevator moves and stops accordingly


## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system

1. ElevatorSystem (Brain)
2. Elevator
3. ExternalRequest
4. Scheduler
5. ElevatorRunner

## Step 3 : Identify responsibility of each entity
Now let's understand responsibility of each entity
1. ElevatorSystem : Holds all elevators and scheduler.
2. Elevator : Moves up/down, opens door and track state
3. ExternalRequest : User intent to go up or down along with floor information.
4. Scheduler : Decides which elevator will serve the request.
5. ElevatorRunner : This will help elevator to move

## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system

1. How elevator will be assigned?
   - Here we can use strategy pattern where we can have different strategies to assign an elevator.
   - As of now i can think of CostBasedScheduler
     - This will take  2 input. Request and List of Elevator's
     - Algorithm : 
       - Iterate on each elevator and find cost : 
         - If elevator.direction == NO_DIRECTION -> return Math.abs(request.floor - elevator.currentFloor)
         - else If elevator.direction == request.direction and elevator will pass from request.floor -> return Math.abs(request.floor - elevator.currentFloor)
         - else return Penalty + Math.abs(request.floor - elevator.currentFloor). (This means elevator is going opposite direction So we should
         add opposite direction penalty to this elevator so that its cost will increase as such elevator will take more time to serve request)

Scenario:

Elevator A: Floor 2 → UP

Elevator B: Floor 8 → DOWN

Elevator C: Floor 5 → NO_DIRECTION

Request: Floor 6 → UP

Algorithm output:
Elevator A and request has same direction and passes 6, so cost 4
Elevator B and request has opposite direction, so cost 1002 (1000 penalty)
Elevator C is idle, so cost 1

Algorithm will select C

## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram

class Direction {
	<<enum>>
    NO_DIRECITON
    UP
    DOWN
}

class ElevatorState {
	<<enum>>
    MOVING
    STOPPED
    MAINTENANCE
}

class ElevatorSystem {
    - elevators : List<Elevator>
    - scheduler : ElevatorScheduler
    + assignElevator(externalRequest : ExternalRequest) : ExternalResponse
}

class Elevator {
    - id : String
    - direction : Direction
    - elevatorState : ElevatorState
    - currentFloor : int
    - requestedUpperFloors : TreeSet<Integer>
    - requestedDownFloors : TreeSet<Integer>
    + addFloor(floor : int) : void
    + move() : void
    + openDoor() : void
    + closeDoor() : void
}

class ElevatorRunner{
    - elevator : Elevator
    + run() : void
}

class ExternalRequest {
    - direction : Direction
    - floor : int
}

class ExternalResponse {
    - elevatorId : String
}

class ElevatorScheduler {
	<<interface>>
    + assignElevator(
        externalRequest : ExternalRequest,
        elevators : List<Elevator>
      ) : Elevator
}

class CostBasedElevatorScheduler {
    + assignElevator(
        externalRequest : ExternalRequest,
        elevators : List<Elevator>
      ) : Elevator
}

CostBasedElevatorScheduler ..|> ElevatorScheduler
ElevatorSystem --> ElevatorScheduler
ElevatorSystem --> Elevator
ExternalRequest --> Direction
Elevator --> Direction
Elevator --> ElevatorState

ElevatorRunner ..|> Runnable
ElevatorRunner --> Elevator
~~~

Explain class diagram in flow :-
There is ElevatorRunner for each individual Elevator which extends Runnable class. These will call move method of Elevator class continuously.
When user will press external button ExternalRequest will get raised. 
This request will be arrived at ElevatorSystem. ElevatorSystem will then assign elevator to this request with the help of ElevatorScheduler.
ElevatorSystem will then add request floor to selected elevator. Elevator will change its state to moving when floor will get added.
Elevator move method -> If elevator is not Stopped or under maintenance then based on direction it will go up or down. If direction is no direction then it will decide 
direction based on requestedUpperFloors or requestedDownFloors if not empty. If both empty then it changes state to STOPPED and direction to NO_DIRECTION.
Then elevator will reach to that floor. Then user will select desired floor. This floor will get added to elevator.
Then elevator will reach that floor.

## Step 6 : Implementation
Example : com.systemdesign.lld.elevatorsystem.Application

## Step 7 : API Design
Optional (Don't tell unless asked)
~~~
Get /api/v1/elevatorsystem/elevator

Request :
{
"direction": "UP",
"floor": "6"
}

Response :
{
"elevatorId": "3"
}
~~~

Controller → ElevatorSystem.assignElevator() → ElevatorScheduler.assignElevator() → ExternalResponse

## Step 8 : Concurrency and Locking
1. Is Elevator thread-safe?
→ One thread per elevator; addFloor is synchronized.

2. Why TreeSet?
→ Maintains ordered stops in current direction.

3. How direction is chosen?
→ Policy-based; simplified here.

4. What happens if new request arrives while doors open?
→ Queued and processed next cycle.

As of now ElevatorRunner is call Elevator.move() continuously. We can make this event based to decrease cpu utilization.

## Step 9 : Final comments
This is intentionally simplified. In production, the elevator would be single-threaded and event-driven; locks here are illustrative.
The design uses strategy so that we can have any scheduler implementation in future, follows SOLID principles using appropriate design patterns.