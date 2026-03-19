# Design a financial limit system
System that restricts financial transaction based on configured limits.

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)
1. Bank admin will configure limit and it will be applicable for transactions done by bank user as well as bank customer?
Yes
2. Can we have different limits for different transactions?
Yes
3. Can we assign limits on different level?
Yes. System level, user group level and also customer can edit it.
4. What will be the hierarchy of applicable limits?
System -> User group -> user edit (Left to right overriding)  Pick ONE level only
5. What are the limits?
Transactional limit, Daily limit (amount/count), Monthly limit(amount/count), Durational limit(amount)

   
## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system

1. TransactionalLimit
2. PeriodicLimit
3. DurationalLimit
4. Transaction
5. LimitPackage 
6. LimitValidator 
7. EntityType 
8. EntityLimitPackageFetcher
9. LimitEvaluator


## Step 3 : Identify responsibility of each entity
Now lets understand responsibility of each entity
1. TransactionalLimit -> It will contain minimum and maxium amount for transaction
2. PeriodicLimit ->  It will contain daily or monthly allowed amount and count
3. DurationalLimit -> It will contain time duration and allowed amount within that time for newly added payee.
4. Transaction -> It will contain transaction details
5. LimitPackage -> It will link transaction with limits
6. LimitValidator -> It will be used to validate transaction amount against each type of limit
7. EntityType -> It will contains level at which we can assign limit.
8. EntityLimitPackageMap -> It contains mapping of entity and limit package
8. EntityLimitPackageFetcher -> It will be used to fetch applicable limit package for given user d transaction.
9. LimitEvaluator -> Main interface which will be used by client.

## Step 4 : Identify where and which design pattern we can use in this system.
1. Strategy: ILimitValidator and AbstractLimitPackageFetcher used as strategy interface.
2. Chain of Responsibility (variant): ordered TreeSet of fetchers processed in sequence.
3. Facade: LimitEvaluationService as the unified API for limit evaluation


## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram
direction LR

%% ===== Core service layer =====
class ILimitEvaluationService {
  <<interface>>
  +validate(request: LimitCheckRequest) LimitCheckResponse
}

class LimitEvaluationService {
  -limitPackageFetchers: TreeSet~AbstractLimitPackageFetcher~
  -limitValidators: List~ILimitValidator~
  +validate(request: LimitCheckRequest) LimitCheckResponse
}

ILimitEvaluationService <|.. LimitEvaluationService

%% ===== Fetchers (limit package resolution) =====
class AbstractLimitPackageFetcher {
  <<abstract>>
  #entityType: EntityType
  +fetchLimitPackage(request: LimitCheckRequest) EntityLimitPackageMap
}
class SystemLevelLimitPackageFetcher {
  +fetchLimitPackage(request: LimitCheckRequest) EntityLimitPackageMap
  +compareTo(o: AbstractLimitPackageFetcher) int
}
class UserGroupLevelLimitPackageFetcher {
  +fetchLimitPackage(request: LimitCheckRequest) EntityLimitPackageMap
  +compareTo(o: AbstractLimitPackageFetcher) int
}
class UserLevelLimitPackageFetcher {
  +fetchLimitPackage(request: LimitCheckRequest) EntityLimitPackageMap
  +compareTo(o: AbstractLimitPackageFetcher) int
}

AbstractLimitPackageFetcher <|-- SystemLevelLimitPackageFetcher
AbstractLimitPackageFetcher <|-- UserGroupLevelLimitPackageFetcher
AbstractLimitPackageFetcher <|-- UserLevelLimitPackageFetcher

%% ===== Validators =====
class ILimitValidator {
  <<interface>>
  +validate(request: LimitCheckRequest, entityLimitPackageMap: EntityLimitPackageMap) boolean
  +utilizeLimit(request: LimitCheckRequest) void
}

class TransactionalLimitValidator {
  +validate(request: LimitCheckRequest, entityLimitPackageMap: EntityLimitPackageMap) boolean
  +utilizeLimit(request: LimitCheckRequest) void
}
class PeriodicLimitValidator {
  +validate(request: LimitCheckRequest, entityLimitPackageMap: EntityLimitPackageMap) boolean
  +utilizeLimit(request: LimitCheckRequest) void
}
class DurationalLimitValidator {
  +validate(request: LimitCheckRequest, entityLimitPackageMap: EntityLimitPackageMap) boolean
  +utilizeLimit(request: LimitCheckRequest) void
}

ILimitValidator <|.. TransactionalLimitValidator
ILimitValidator <|.. PeriodicLimitValidator
ILimitValidator <|.. DurationalLimitValidator

%% ===== Domain model =====
class LimitCheckRequest {
  -userId: String
  -transaction: Transaction
  -amount: CurrencyAmount
  -payeeId: String
}

class LimitCheckResponse {
  -isAllowed: boolean
  +isAllowed() boolean
}

class Transaction {
  -id: String
  -name: String
  -description: String
}

class CurrencyAmount {
  -amount: int
  -currency: Currency
}

class Currency {
  <<enumeration>>
  INR
  USD
}

class EntityLimitPackageMap {
  -entityType: EntityType
  -entityValue: String
  -limitPackage: LimitPackage
}

class EntityType {
  <<enumeration>>
  SYSTEM
  USER_GROUP
  USER
}

class LimitPackage {
  -id: String
  -name: String
  -description: String
  -listMap: Map~String, List~ILimit~~
  +addLimitMap(transaction: String, limit: ILimit) void
  +getLimitMap() Map~String, List~ILimit~~
}

class ILimit {
  <<interface>>
}

class TransactionLimit {
  -minAmount: CurrencyAmount
  -maxAmount: CurrencyAmount
}

class PeriodicLimit {
  -type: PeriodicType
  -amount: CurrencyAmount
  -count: int
}

class DurationLimit {
  -endTime: int
  -amount: CurrencyAmount
}

ILimit <|.. TransactionLimit
ILimit <|.. PeriodicLimit
ILimit <|.. DurationLimit

class PeriodicType {
  <<enumeration>>
  DAILY
  MONTHLY
}

class LimitUtilization {
  -partyType: PartyType
  -partyValue: String
  -transactionId: String
  -amount: CurrencyAmount
  -count: int
  -date: LocalDate
  -periodicType: PeriodicType
}

class PartyType {
  <<enumeration>>
  INITIATOR
  PAYEE
}

class Payee {
  -payeeId: String
  -payeeName: String
  -creationDate: LocalDateTime
  -accountNumber: String
  -ifscCode: String
}

%% ===== Persistence (in-memory) =====
class DataBase {
  <<static>>
  +getEntityLimitPackage(type: EntityType, value: String) EntityLimitPackageMap
  +getUserGroupForUser(user: String) String
  +getUtilizedLimit(partyType: PartyType, partyValue: String, type: PeriodicType, date: LocalDate, transactionId: String) LimitUtilization
  +updateLimitUtilization(transactionId: String, partyType: PartyType, partyValue: String, type: PeriodicType, date: LocalDate, updatedAmount: int, updatedCount: int) void
  +getPayee(payeeId: String) Payee
}

%% ===== Application (wiring/example) =====
class BankingApplication {
  -IMPS: String
  -RTGS: String
  -NEFT: String
  +main(args: String[]) void
}

%% ===== Relationships / usages =====
LimitEvaluationService --> AbstractLimitPackageFetcher
LimitEvaluationService --> ILimitValidator

AbstractLimitPackageFetcher --> EntityType
SystemLevelLimitPackageFetcher --> DataBase
UserGroupLevelLimitPackageFetcher --> DataBase
UserLevelLimitPackageFetcher --> DataBase

TransactionalLimitValidator --> TransactionLimit
PeriodicLimitValidator --> PeriodicLimit
DurationalLimitValidator --> DurationLimit


EntityLimitPackageMap --> EntityType
EntityLimitPackageMap --> LimitPackage

LimitCheckRequest --> Transaction
LimitCheckRequest --> CurrencyAmount
CurrencyAmount --> Currency

LimitUtilization --> PartyType
LimitUtilization --> PeriodicType
LimitUtilization --> CurrencyAmount


BankingApplication --> ILimitEvaluationService
BankingApplication --> LimitCheckRequest
BankingApplication --> LimitCheckResponse
~~~
Explain class diagram in flow :-

BankingApplication builds LimitCheckRequest(userId, transaction, amount, payeeId) and Calls ILimitEvaluationService.validate(request)

LimitEvaluationService.validate(request)
LimitEvaluationService iterates limitPackageFetchers (ordered TreeSet):
    1. UserLevelLimitPackageFetcher.fetchLimitPackage() → DataBase.getEntityLimitPackage(USER, userId)
    2. UserGroupLevelLimitPackageFetcher.fetchLimitPackage() → DataBase.getUserGroupForUser(userId) then DataBase.getEntityLimitPackage(USER_GROUP, groupId)
    3. SystemLevelLimitPackageFetcher.fetchLimitPackage() → DataBase.getEntityLimitPackage(SYSTEM, …)

If any fetcher returns non-null entityLimitMap response then break the loop of fetcher.

LimitEvaluationService iterates limitValidators:
TransactionalLimitValidator.validate(request, entityLimitMap) checks TransactionLimit (min/max).
PeriodicLimitValidator.validate(request, entityLimitMap) checks PeriodicLimit and reads utilized limits via DataBase.getUtilizedLimit(...).
DurationalLimitValidator.validate(request, entityLimitMap) checks DurationLimit and reads utilized limits via DataBase.getUtilizedLimit(...) if required.

If any validator returns false → return LimitCheckResponse(isAllowed=false).

If all return true:
Call validator.utilizeLimit(request) for relevant validators → DataBase.updateLimitUtilization(...).

Return LimitCheckResponse(isAllowed=true).

Note : If transaction fails then utilized limits will be rolled back from db.
    -  
## Step 6 : Implementation
Example : com.systemdesign.lld.financiallimitsystem.BankingApplication

## Step 7 : API Design

### Interface to validate limit
Facade :
com.systemdesign.lld.financiallimitsystem.ILimitEvaluationService
~~~

Request :
{
  "userId": "user-123",
  "transaction": {
    "id": "IMPS",
    "name": "IMPS",
    "description": "Immediate Payment Service transfer"
  },
  "amount": {
    "amount": 25000,
    "currency": "INR"
  },
  "payeeId": "payee-987"
}

Response : 
{
  "allowed": true
}
~~~

BankingApplication → ILimitEvaluationService.validate(request) → LimitPackageFetchers.fetchLimitPackage()  → ILimitValidators.validate(request, entityLimitPackageMap) → (if allowed) ILimitValidators.utilizeLimit(request) → LimitCheckResponse

## Step 8 : Concurrency and Locking
Main risk: two concurrent requests both read same utilization, both pass, then both update → limit exceeded.

Do this:

Lock per “utilization bucket” key: (partyType, partyValue, periodicType, date, transactionId)
Under the lock: read → validate → update (atomic), then unlock.

But as this is financial limit it is unlikely that same user will try transaction from 2 places at same time.


## Step 9 : Final comments
The design avoids conditional logic, follows SOLID principles using appropriate design patterns, and keeps the REST layer thin