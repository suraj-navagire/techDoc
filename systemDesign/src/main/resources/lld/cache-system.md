# Design a Cache system

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)
1. Cache should have get(key) method which will return value or null.
2. It should have put(key, value) to store elements in cache.
3. Time complexity for get and put has to be O(1).
4. What should be the capacity. Fixed or growable ?
    - Considering Fixed
5. If capacity is fixed then we have to add eviction policy to remove element from cache when capacity exceeds.
6. Which eviction policy. LRU (Least recently used) or LFU (Least frequently used) ?
    - Considering LRU
7. Thread-safe 
8. In-memory (single JVM) or Distributed (Multiple-JVM) ?
    - In-memory
9. Low latency > memory efficient (It is ok to use extra space to retrieve data faster)
10. Provision to add Time to live ? 
    - Yes
11. Do we need to consider replication ?
    - No
12. Do we need to consider persistence ?
    - No

## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system
1. Cache
2. EvictionPolicy


## Step 3 : Identify responsibility of each entity
Now let's understand responsibility of each entity
1. Cache : Main class/interface which will expose get(), put() functions
2. EvictionPolicy : It will track accessed keys and will return key for eviction.

## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system

1. How element will get removed when capacity is full?
   - Here we can use strategy pattern and can use LRU or LFU based on requirement


## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~

~~~

Explain class diagram in flow :-

## Step 6 : Implementation

## Step 7 : API Design

~~~

~~~

Controller → ElevatorSystem.assignElevator() → ElevatorScheduler.assignElevator() → ExternalResponse

## Step 8 : Concurrency and Locking

## Step 9 : Final comments
