# Design a parking lot system

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)
1. Vehicle types?
    - Only Car? Or Bike, Car, Truck?
    - My assumption : Bike, Car, Truck

2. Parking structure?
    - Single floor or multiple floors?
    - My assumption : Multi floor

3. Pricing model?
    - Flat rate or hourly? Same price for all vehicles?
    - My assumption : Hourly pricing

4. Entry & Exit gates?
    - Single entry/exit or multiple?
    - My assumption : Multiple gates

5. Payment timing?
    - Pay at exit only? Or pay while parking?
    - My assumption : Pay at exit

6. Display boards required?
    - Show available slots per floor?
    - My assumption : yes

## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system

1. ParkingLot
2. ParkingFloor
3. ParkingSlot
4. Vehicle
5. Ticket
6. EntryGate
7. ExitGate
8. Payment
9. DisplayBoard

## Step 3 : Identify responsibility of each entity
Now lets understand responsibility of each entity

1. ParkingLot -> It is responsible for Overall orchestration
2. ParkingFloor -> It holds ParkingSlot's
3. ParkingSlot -> It holds Vehicle
4. Vehicle -> It has Vehicle Information
5. Ticket -> It has entry time and slot information
6. EntryGate -> It is responsible for issuing ticket
7. ExitGate -> It is responsible for calculating fees and payment
8. Payment -> Payment execution
9. DisplayBoard -> It will show available slots


## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system

1. How do you assign slots to vehicles?
    - Slots assignment can vary, so i will use strategy pattern
    - Strategies
        - ISlotAssignmentStrategy (interface)
        - NearestSlotStrategy
        - RandomSlotStrategy

2. How do you calculate parking fee?
    - Fee calculation also varies, so I’ll use Strategy pattern again with the help of factory pattern, Based on user's payment type we can use one of the strategy.
    - Strategies
        - IFeeCalculationStrategy (interface)
        - CarFeeCalculationStrategy
        - BikeFeeCalculationStrategy
        - TruckFeeCalculationStrategy

3. How do you track slot availability?
    - ParkingSlot has 2 states FREE and OCCUPIED. Here we can use state design pattern which will support 3 actions. isAvailable, parkVehicle, unParkVehicle
    - States
        - FreeState
        - OccupiedState

4. How are display boards updated?
    - To update display board it has to observe ParkingFloor. So here we can use Observer pattern.
    - First floor will get created. Then on that floor slots will be created. As we create slot we will pass respective floor to it. Then once slot
   is created then we will add this slot to floor. Also Floor will have list of observers. So when slot will be occupied or free it will call floor's
   notifyObserver method
    - Subject(Observable) : ParkingFloor, Observer : DisplayBoard


## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram

BikeFeeCalculationStrategy ..|> IFeeCalculationStrategy
CarFeeCalculationStrategy ..|> IFeeCalculationStrategy
TruckFessCalculationStrategy ..|> IFeeCalculationStrategy

class IFeeCalculationStrategy{
	<<interface>>
	+calculateFee(ticket : Ticket) : long
}

class BikeFeeCalculationStrategy{
	+calculateFee(ticket : Ticket) : long
}

class CarFeeCalculationStrategy{
	+calculateFee(ticket : Ticket) : long
}

class TruckFeesCalculationStrategy{
	+calculateFee(ticket : Ticket) : long
}

DisplayBoard ..|> Observer

class Observer {
   +update(count : int) : void
}

class DisplayBoard {
	-id : String
	-count: int
	+update(count : int) : void
	+getId() : String
	+getCount() : int
}

ParkingFloor --|> Observable

ParkingFloor --> ParkingSlot

class ParkingFloor{
	-floorId : String
	-parkingSlots : List<ParkingSlot>
	+getFloorId() : String
	+getParkingSlots() : List<ParkingSlot>
	+notifyObservers() : void
	+addParkingSlot(parkingSlot : ParkingSlot) : void
} 


EntryGate --> ISlotAssignmentStrategy

class EntryGate{
	-gateId : String
	-slotAssignmentStrategy : ISlotAssignmentStrategy
	+issueTicket(vehicle : Vehicle, floors : List<ParkingFloor>) : Ticket
	+getGateId() : String
	+getSlotAssignmentStrategy() : ISlotAssignmentStrategy
}

ExitGate --> FeesCalculationFactory
ExitGate --> PaymentFactory

class ExitGate{
	-gateId : String
	-feeCalculationFactory : FeesCalculationFactory
	-paymentFactory : PaymentFactory
	+processExit(ticket : Ticket, paymentType : PaymentType) : boolean
	+getGateId() : String
}

FeeCalculationFactory --> IFeeCalculationStrategy

class FeeCalculationFactory{
	-strategyMap : Map<VehicleType, IFeeCalculationStrategy>
	+getInstance(vehicleType : VehicleType) : IFeeCalculationStrategy
}


class IParkingSlotState{
	<<interface>>
	+parkVehicle(vehicle : Vehicle) : void
	+unParkVehicle() : Vehicle
	+isAvailable() : boolean
}

FreeParkingLotState --> ParkingSlot
OccupiedParkingSlotState --> ParkingSlot

class FreeParkingLotState{
	-parkingSlot : ParkingSlot
	+parkVehicle(vehicle : Vehicle) : void
	+unParkVehicle() : Vehicle
	+isAvailable() : boolean (true)
}

class OccupiedParkingSlotState{
	-parkingSlot : ParkingSlot
	+parkVehicle(vehicle : Vehicle) : void
	+unParkVehicle() : Vehicle
	+isAvailable() : boolean (false)
}

NearestSlotAssignmentStrategy ..|> ISlotAssignmentStrategy

RandomSlotAssignmentStrategy ..|> ISlotAssignmentStrategy

class ISlotAssignmentStrategy{
	<<interface>>
	+assignSlot(vehicle : Vehicle,parkingSlots : List<ParkingSlot>) : ParkingSlot
}

class NearestSlotAssignmentStrategy{
	+assignSlot(vehicle : Vehicle,parkingSlots : List<ParkingSlot>) : ParkingSlot
}

class RandomSlotAssignmentStrategy{
	+assignSlot(vehicle : Vehicle,parkingSlots : List<ParkingSlot>) : ParkingSlot
}

ParkingSlot ..|> Comparable
ParkingSlot --> VehicleType
ParkingSlot --> Vehicle
ParkingSlot --> IParkingSlotState
ParkingSlot --> ParkingFloor

class ParkingSlot{
	-slotId : String
	-floor : ParkingFloor
	-vehicleType : VehicleType
	-vehicle : Vehicle
	-currentParkingSlotState : IParkingSlotState
	-freeParkingSlotState : FreeParkingLotState
	-occupiedParkingSlotState : OccupiedParkingSlotState
	+getSlotId() : String
	+getVehicleType() : VehicleType
	+getVehicle() : Vehicle
	+setVehicle(vehicle : Vehicle) : void
	+setCurrentParkingSlotState(slotState : IParkingSlotState) : void
	+getFreeParkingSlotState() : IParkingSlotState
	+getOccupiedParkingSlotState() : IParkingSlotState
	+getCurrentParkingSlotState() : IParkingSlotState
	+getParkingFloor() : ParkingFloor
	+parkVehicle(vehicle : Vehicle) : void
	+unParkVehicle() : Vehicle
	+compareTo(slot : ParkingSlot) : int
}

PaymentFactory --> PaymentType
PaymentFactory --> IPaymentStrategy

class IPaymentStrategy {
	<<interface>>
	+pay(amount : long) : boolean
}

class PaymentFactory{
	-strategyMap : Map<PaymentType, IPaymentStrategy>
	+getInstance(type : PaymentType) : IPaymentStrategy
}

class PaymentType{
	<<enum>>
	CASH, UPI
}

CashPaymentStrategy ..|> IPaymentStrategy
UPIPaymentStrategy ..|> IPaymentStrategy

class CashPaymentStrategy{
	+pay(amount : long) : boolean
}

class UPIPaymentStrategy{
	+pay(amount : long) : boolean
}

Ticket --> vehicle
Ticket --> ParkingSlot

class Ticket{
	-id : String
	-entryTime : LocalDateTime
	-vehicle : Vehicle
	-slot : ParkingSlot
}

Vehicle -->  VehicleType
class Vehicle{
	-registrationNumber : String
	-vehicleType : VehicleType
}

class VehicleType{
	<<enum>>
	BIKE, CAR, TRUCK
}

ParkingLot --> ParkingFloor
ParkingLot --> EntryGate
ParkingLot --> ExitGate

class ParkingLot{
	-parkingFloors : List<ParkingFloor>
	-entryGates : List<EntryGate>
	-exitGates : List<ExitGate>
}
~~~
Explain class diagram in flow :-
At entry gate, vehicle comes in. EntryGate uses SlotAssignmentStrategy to find a free slot across floors.
Once parked, slot changes its state to occupied and a ticket is generated.
At exit, ExitGate uses FeeCalculationFactory based on vehicle type, calculates fee, then delegates payment to PaymentFactory.
After successful payment, slot transitions back to free state and observers are notified.

## Step 6 : Implementation
Exmaple : com.systemdesign.lld.parkinglot.Client

## Step 7 : API Design

### API to get parking ticket
~~~
POST /api/v1/parking/entry

Request :
{
  "registrationNumber": "MH12AB1234",
  "vehicleType": "CAR",
  "entryGateId": "G-IN-1"
}

Response : 
{
  "ticketId": "T123",
  "slotId": "S45",
  "floorId": "F2",
  "entryTime": "2026-01-10T10:30:00"
}
~~~

Controller → EntryGate.issueTicket() → SlotAssignmentStrategy.assignSlot() → ParkingSlot.parkVehicle() → Ticket

### API for vehicle exit and payment
~~~
POST /api/v1/parking/exit

Request :
{
  "ticketId": "T123",
  "paymentType": "UPI"
}

Response :
{
  "amount": 120,
  "paymentStatus": "SUCCESS",
  "exitTime": "2026-01-10T13:45:00"
}
~~~
Controller → ExitGate.processExit() → FeeCalculationFactory.getInstance(vehicleType) → IFeeCalculationStrategy.calculateFee() 
→ PaymentFactory.getInstance(paymentType) → IPaymentStrategy.pay() → ParkingSlot.unParkVehicle()

## Step 8 : Concurrency and Locking
Slot assignment and state transitions must be synchronized