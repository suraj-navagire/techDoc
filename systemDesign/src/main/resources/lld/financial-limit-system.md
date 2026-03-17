# Design a financial limit system
System that restricts financial transaction based on configured limits.

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)
1. Bank admin will configure limit and it will be applicable for transactions done by bank user as well as bank customer?
Yes
2. Can we have different limits for different transactions?
Yes
3. Can we assign limits on different level?
Yes. System level, user group level, user level and also customer can edit it.
4. What will be the hierarchy of applicable limits?
System -> User group -> user -> customer edit (Left to right overriding)
5. Can we have transaction group limit?
Yes. We can group transaction and configure limit. 
6. What are the limits?
Transactional limit, Daily limit (amount/count), Monthly limit(amount/count), Durational limit(amount)

   
## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system

1. TransactionalLimit
2. PeriodicLimit
3. DurationalLimit
4. TransactionGroup
5. Transaction
6. LimitLevel
7. LimitUtilization


## Step 3 : Identify responsibility of each entity
Now lets understand responsibility of each entity
1. TransactionalLimit -> It will contain minimum and maximum amount for transaction
2. PeriodicLimit ->  It will contain daily or monthly allowed amount and count
3. DurationalLimit -> It will contain time duration and allowed amount within that time for newly added payee.
4. TransactionGroup -> It will group transactions.
5. Transaction -> Individual transaction
6. Entity -> Entity is a level like System, UserGroup, User, Custom
7. LimitUtilization -> Limit utilization data.

## Step 4 : Identify where and which design pattern we can use in this system.



## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram

TransactionlLimit ..> ILimit
PeriodicLimit ..> ILimit
DurationLimit ..> ILimit


class ILimit{
    <<Marker Interface>>
}

class Currency{
    <<enum>>
    INR, USD
}

class CurrencyAmount{
    - amount : int
    - currency : Currency
}

class TransactionlLimit{
    - id : String
    - name : String
    - description : String
    - minAmount : CurrencyAmount
    - maxAmount : CurrencyAmount
}

class PerioodicType{
    DAILY, MONTHLY
}

class PeriodicLimit{
    - id : String
    - name : String
    - description : String
    - type : PerioodicType
    - amount : CurrencyAmount
    - count : int
}

class DurationLimit{
    - id : String
    - name : String
    - description : String
    - startTime : 0
    - endTime : int //In hour
    - amount : CurrencyAmount
}

class Transaction{
    - id : String
    - name : String
    - description : String    
}

class TransactionGroup{
    - id : String
    - name : String
    - description : String
    - transactions : Set<Transaction> //We cannot have same transaction in multiple group. If we allow then we will not be able to decide which limit to apply.
}

class LimitPackage{
    - id : String
    - name : String
    - description : String
    - transactionLimitMap : Map<Transaction, List<ILimit>>
    - transactionGroupLimitMap : Map<TransactionGroup, List<ILimit>>
}

class EntityType{
    SYSTEM, USER_GROUP, USER
}

class EntityLimitPackageMap{
    - entityType : EntityType
    - entityValue : String
    - limitPackage : LimitPackage
}

class LimitUtilizaion{
    - entityValue : String
    - entityType : EntityType
    - transactionId : String
    - amount : CurrencyAmount
    - count : int
    - date : Date    
}

class LimitCheckRequest{
    - userId : String
    - transaction : Transaction
    - amount : CurrencyAmount
    - payeeId : String
}

class LimitCheckResponse{
    - isAllowed : boolean
}

class ILimitValidation{
    - validate(request : LimitCheckRequest) : LimitCheckResponse
}
~~~
Explain class diagram in flow :-

There are 2 parts:
    1. Limit Data model
    2. Limit validation algorithm

1. Limit Data Model:
   - We can set limit as transactional limit, periodic limit and durational limit.
   - We can create group of transaction.
   - We can create limit package and map transaction or transaction group with limit.
   - We can assign this limit package to entities like system, user group
   - Customer can customize its limit. Entity will be user.

2. Limit Validation Algorithm:
    -  
## Step 6 : Implementation
Example : com.systemdesign.lld.parkinglot.Client

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

## Step 9 : Final comments
The design avoids conditional logic, follows SOLID principles using appropriate design patterns, and keeps the REST layer thin