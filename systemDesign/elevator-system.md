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

1. ElevatorControllerSystem (Brain)
2. Elevator
3. Request
4. Scheduler

## Step 3 : Identify responsibility of each entity
Now let's understand responsibility of each entity
1. ElevatorController : Holds all elevators and scheduler.
2. Elevator : Moves up/down, opens door and track state
3. Request : User intent to go up or down along with floor information.
4. Scheduler : Decides which elevator will serve the request.

## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system

1. How elevator will be assigned?
   - Here we can use strategy pattern where we can have different strategies to assign an elevator.
   - As of now i can think of CostBasedScheduler
     - This will take  2 input. Request and List of Elevator's
     - Algorithm : 
       - Iterate on each elevator and find cost : 
         - If elevator.direction == IDLE -> return Math.abs(request.floor - elevator.currentFloor)
         - else If elevator.direction == request.direction and elevator will pass from request.floor -> return Math.abs(request.floor - elevator.currentFloor)
         - else return Penalty + Math.abs(request.floor - elevator.currentFloor). (This means elevator is going opposite direction So we should
         add opposite direction penalty to this elevator so that its cost will increase as such elevator will take more time to serve request)

Scenario:
Elevator A: Floor 2 → UP
Elevator B: Floor 8 → DOWN
Elevator C: Floor 5 → IDLE
Request: Floor 6 → UP

Algorithm output:
Elevator A and request has same direction and passes 6, so cost 4
Elevator B and request has opposite direction, so cost 1002 (1000 penalty)
Elevator C is idle, so cost 1

Algorithm will select C

## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~

~~~
Explain class diagram in flow :-


## Step 6 : Implementation
Example : 

## Step 7 : API Design

## Step 8 : Concurrency and Locking


## Step 9 : Final comments
The design avoids conditional logic, follows SOLID principles using appropriate design patterns, and keeps the REST layer thin