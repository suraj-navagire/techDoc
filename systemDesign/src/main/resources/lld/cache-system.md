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
2. CacheEntry
3. InMemoryCache
4. EvictionPolicy
5. LRUEvictionPolicy
6. DoublyLinkedList

## Step 3 : Identify responsibility of each entity
Now let's understand responsibility of each entity
1. Cache : Main class/interface which will expose get(), put() functions
2. CacheEntry : This will be used as Value for HashMap. It will store value and expiryTime. 
   Expiry time will be stored to support TTL mechanism. Please not we have used CacheEntry as value and not as key,
   because key will be passed by user and based on that we have to get value in O(1) time. In future we can store any metadata inside this CacheEntry class.
3. InMemoryCache : It will contain key value data. EvictionPolicy strategy and Capacity.
4. EvictionPolicy : It will track accessed keys and will return key for eviction.
5. LRUEvictionPolicy : It will use doubly Linked list. Head will be considered as recently used and tail will be considered as least used.
    Also it will use Map to store key-node pair. So that node of key will be accessed in O(1) time complexity. And using this we can update doubly linked list.
6. DoublyLinkedList : It is used because node position can be changed in O(1) time complexity. It is required as every time key gets accessed it will come first.

## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system

1. How element will get removed when capacity is full?
   - Here we can use strategy pattern and can use LRU or LFU based on requirement


## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram

InMemoryCache ..|> Cache
InMemoryCache --> EvictionPolicy
InMemoryCache --> CacheEntry

class Cache {
	<<interface>>
	+ get(key : K) : V
	+ put(key : K, value : V) : void
}
	
class InMemoryCache {
	- map : HashMap<K, CacheEntry<V>>
	- capacity : int
	- timeToLive : long
	- evictionPolicy : EvictionPolicy<K>
	+ get(key : K) : V
	+ put(key : K, value : V)
}

LRUEvictionPolicy ..|> EvictionPolicy
LRUEvictionPolicy --> DoublyLinkedList

class EvictionPolicy {
	<<interface>>
	+ keyAccessed(key : K) : void
	+ evictKey() : K
	
}

class CacheEntry {
    - value : V
    - expiryTime : long
}

class LRUEvictionPolicy{
	- doublyLinkedList : DoublyLinkedList<K>
	- map : HashMap<K, Node<K>>
	+ keyAccessed(key : K) : void
	+ evictKey() : K
}
	

DoublyLinkedList --> Node

class DoublyLinkedList{
	- head : Node<K>
	- tail : Node<K>
	+ addToFirst(key : K) : Node<K>
	+ moveToFirst(node : Node<K>)
	+ removeLast() : Node<K>
}

class Node{
	- key : K
	- prev : Node
	- next : Node
}
~~~

Explain class diagram in flow :-
Client will use Cache.put() to add key-value -> InMemoryCache.put() will be used -> It will store key-value inside HashMap
-> EvictionPolicy.keyAccessed() will be called. -> If HashMap is full then EvictionPolicy.evictKey() will be called.

Client will use Cache.get() to fetch value -> InMemoryCache.get() will be used -> It will return value from HashMap ->
EvictionPolicy.keyAccessed() will be called.

Here we can use eager expiration.
A background daemon thread can periodically scan the cache and can remove expired entries.
get() does not check TTL to keep the read path extremely fast.
The tradeoff is that expired entries may be visible for a short window until cleanup runs.

## Step 6 : Implementation
com.systemdesign.lld.cachesystem.Client

## Step 7 : API Design
Not Applicable
~~~

~~~

## Step 8 : Concurrency and Locking
Synchronization is required while updating map and eviction policy.
Note that eviction policy will be called under Cache lock so separate lock is not required.

## Step 9 : Final comments
This design expose get and put method to client with the help of Cache interface (Abstraction).
Get and Put have O(1) time complexity.
In future we can have any implementation for Cache. As of now its InMemory
Here we have separated out Cached user data and Eviction policy data. Eviction policy can work independently.
Also we can change eviction policy as per requirement.