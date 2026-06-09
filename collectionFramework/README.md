# Collection Framework

## Collection

A group of individual objects that represents a single entity is known as a collection.

## Basics

### Array

1. An array is a collection of homogeneous elements. In Java, there is a class for every array object.
2. The class name of a one-dimensional integer array is `[I`. These classes are not accessible to us.
3. An array can be created by:

```java
int[] a = new int['a'];
```

Allowed data types to provide size are `byte`, `short`, `char`, and `int`, as these can be promoted to `int`.

4. We cannot pass a negative value as size.
5. Size should be within `int` size.

### Array of Arrays

Array of arrays can be one-dimensional, two-dimensional, and so on, up to 51 dimensions.

#### 2D Arrays

```java
int[][] a = new int[2][]; // This is an array of arrays
a[0] = new int[1];
a[1] = new int[1];
a[0][0] = 1;
a[1][0] = 2;
```

#### 3D Arrays

```java
int[][][] a = new int[1][][]; // This is an array of 2D arrays
a[0] = new int[1][];
a[0][0] = new int[1];
a[0][0][0] = 1;
```

### Anonymous Arrays

Arrays without reference variables are anonymous arrays.

Example:

```java
new int[]{2, 5, 8}
```

This can only be used for passing to a method.

Example:

```text
org.example.ArrayExample
org.example.MultiDimensionalArrayExample
```

## Array vs Collection

1. Arrays are fixed in size. Collections are growable in nature.
2. Arrays can hold primitive and object type data. Collections can only hold object type data.
3. An array itself is a data structure but does not have utility methods or algorithms. Collection uses an underlying data structure to hold data, has utility methods, and uses algorithms for better performance.

## Collections Framework

It contains interfaces, classes, and algorithms to solve collection-of-data-related problems.

It has nine dominant interfaces:

### 1. Collection Interface

Parent interface to hold a group of objects as a single entity.

### 2. List Interface

Represents a group of objects as a single entity where insertion order is preserved and duplicates are allowed.

Implementations:

1. `ArrayList`
2. `LinkedList`
3. `Vector` (Legacy class)
    1. `Stack` (Legacy class)

### 3. Set Interface

Represents a group of objects as a single entity where insertion order is not preserved and duplicates are not allowed.

Implementations:

1. `HashSet`
    1. `LinkedHashSet`
2. `SortedSet` interface
    1. `NavigableSet` interface
        1. `TreeSet`

### 4. Queue Interface

Represents a group of individual objects prior to processing. It follows FIFO order.

Implementations:

1. `PriorityQueue`
2. `BlockingQueue` interface
    1. `PriorityBlockingQueue`
    2. `LinkedBlockingQueue`

### 5. Map Interface

Parent interface to hold key-value pairs.

Implementations:

1. `HashMap`
    1. `LinkedHashMap`
2. `WeakHashMap`
3. `IdentityHashMap`
4. `Hashtable` extends `Dictionary`
    1. `Properties`
5. `SortedMap` interface
    1. `NavigableMap` interface
        1. `TreeMap`

## Collection Interface Methods

`Collection` provides the most commonly used methods that can be used by child classes.

```java
boolean add(Object obj)
boolean addAll(Collection c)
boolean remove(Object obj)
boolean removeAll(Collection c)
boolean retainAll(Collection c)
void clear()
boolean contains(Object obj)
boolean containsAll(Collection obj)
boolean isEmpty()
int size()
Iterator iterator()
Object[] toArray()
```

## Collection vs Collections

1. `Collection` is an interface. It is the parent interface for most of the classes of the collection framework.
2. `Collections` is a utility class which provides several methods like sorting, searching, and shuffling for collection objects.

Collections methods:

```java
sort()
shuffle()
reverse()
binarySearch()
disjoint()
```

## Generics

Generics provide type safety, solve problems related to type casting, and help developers write less redundant code.

## Collection

It is the root interface of the collection framework.

## List

It is an interface present in the `java.util` package. It extends the `Collection` interface.

### Methods in List

```java
add()
get()
remove()
contains()
indexOf()
lastIndexOf()
set()
```

### List Implementations

### 1. ArrayList

It is based on an array. Default capacity is 10. When capacity is full, it increases capacity. The new size becomes 50% more.

```java
newSize = oldSize + (oldSize >> 1)
```

Properties:

- Serializable
- Cloneable
- RandomAccess interface: This is a marker interface which tells that any random element can be accessed with the same speed/time complexity.

Advantages:

1. Dynamic size
2. Easy to use
3. Fast retrieval
4. Insertion order is preserved

Disadvantages:

1. Not thread-safe
2. Slower than arrays
3. Performance decreases as size increases
4. Inserting at a position is slower
5. Deletion at a position is slower

### 2. LinkedList

It is based on a doubly linked list data structure.

Properties:

- Serializable
- Cloneable

Advantages:

1. Manipulation is fast as compared with ArrayList, as no bit shifting is needed.
2. Good memory utilization.
3. Insertion at a specific index is fast.
4. It can act as a list and deque, as it implements both interfaces.
5. Deletion at a specific index is fast.
6. Insertion order is preserved.

Disadvantages:

1. More memory gets used to store the same amount of data compared with ArrayList.
2. Retrieval is slow.

### 3. Vector

It is based on an array. Initial capacity is 10, same as `ArrayList`. When capacity is full, size increases. Size gets doubled.

```java
newSize = oldSize * 2
```

Properties:

- Serializable
- Cloneable
- RandomAccess interface

Advantages:

1. Same as `ArrayList`.
2. It is thread-safe. All methods are synchronized. `ArrayList` is not.
3. It is a legacy class. It contains a few redundant methods like `addElement` and `removeElement`.

Disadvantages:

1. Insertion is costly in a multi-threaded environment as it is synchronized.
2. Read is also costly since it is synchronized.
3. All methods are synchronized, so it takes more time than `ArrayList`.

Example:

```text
org.example.List.VectorExample
```

### 4. Stack

`Stack` extends `Vector`.

Methods:

```java
push(Element)
Element pop()
Element peek()
int search(element)
```

Properties:

- Based on LIFO
- Insertion order is preserved
- `search(element)` returns the position of the given element with respect to LIFO order. If the element is at the top, it will return 1. If it is last, then it returns `n`.

Example:

```text
org.example.List.StackExample
```

## Set

It is an interface present in the `java.util` package. It extends the `Collection` interface.

Example:

```text
org.example.set.SetExample
```

### Set Implementations

### 1. HashSet

Internally, it uses the Hashtable data structure. Searching is fast. Duplicates are not allowed. We can add a null value. Initial capacity is 16.

The default load factor is 0.75. Load factor means at what size the capacity should be increased. By default, `HashSet` increases its size when 75% capacity is used. Then it creates a new object with double the capacity and copies all the old elements to the new one. Then the old object becomes available for garbage collection.

Properties:

- Serializable
- Cloneable

#### Internal Working

- Same as `HashMap`.
- `HashSet` uses `HashMap`, i.e. Hashtable data structure, to store elements.
- `HashMap` is key-value pair based.
- `HashMap` considers the given element as key and stores it using the `put()` method, where the element becomes key and null becomes value.

#### HashSet Implementation

### LinkedHashSet

It extends the `HashSet` class. It maintains insertion order.

### 2. SortedSet

It is an interface. It stores elements based on their natural sorting order.

Methods:

```java
E first()
E last()
SortedSet headSet(element)
SortedSet tailSet(element)
SortedSet(firstElement, lastElement)
Comparator comparator()
```

`comparator()` returns the comparator if used. It returns null if natural sorting is used.

### Child Interface/Class

### NavigableSet

This is an interface. It contains methods used for navigation.

Methods:

```java
E floor(e)
E lower(e)
E ceiling(e)
E higher(e)
E pollFirst()
E pollLast()
E descendingSet()
```

Method meanings:

1. `floor(e)`: Returns the greatest element from the set which is less than or equal to the given element; else null.
2. `lower(e)`: Returns the greatest element from the set which is less than the given element; else null.
3. `ceiling(e)`: Returns the smallest element from the set which is greater than or equal to the given element; else null.
4. `higher(e)`: Returns the smallest element from the set which is greater than the given element; else null.
5. `pollFirst()`: Returns and removes the smallest element from the set; else null.
6. `pollLast()`: Returns and removes the greatest element from the set; else null.
7. `descendingSet()`: Returns the navigable set in reverse order.

### TreeSet

This is an implementation class of `NavigableSet`. Internally, it uses `TreeMap`, which uses a self-balanced binary tree.

#### Internal Working

- The first element will be the root element.
- This root will have a left child and right child.
- The next element will be left or right based on the comparison result. If the current element is less than the root, then it will go left; otherwise, it will go right. And so on.
- For comparison, elements must be comparable to provide natural sorting. We can also pass a comparator for custom comparison.
- We can pass a null element only once, i.e. at the start. If we insert the next element, then it will throw `NullPointerException`.
- Elements get traversed using inorder traversal.

Example:

```text
org.example.set.TreeSetExample
```

## Queue

It is a group of objects that are about to be processed. Queue follows the first-in-first-out concept.

Methods:

```java
boolean add(E e)
boolean offer(E e)
E remove()
E poll()
E element()
E peek()
```

Method meanings:

1. `add(E e)` and `offer(E e)`: Both are used to add an element at the tail of the queue or as per priority.
2. `remove()`: Removes and returns the head of the queue. Throws `NoSuchElementException` if the queue is empty.
3. `poll()`: Removes and returns the head of the queue. Returns null if the queue is empty.
4. `element()`: Returns the head of the queue. Throws `NoSuchElementException` if the element is not present.
5. `peek()`: Returns the head of the queue. Returns null if the queue is empty.

### Queue Implementations

### 1. LinkedList

### 2. PriorityQueue

Represents a group of objects about to be processed based on some priority. Elements get added and deleted according to some priority.

Priority can be default natural sorting order or custom sorting order. Insertion order is not preserved and duplicates are not allowed.

Default size is 11 and default priority order is natural sorting order.

### 3. BlockingQueue

This is thread-safe as all the methods are atomic. It blocks the producer if the queue is full and blocks the consumer if the queue is empty.

If attempts are made to insert a null element, it throws `NullPointerException`.

It has two types: bounded and unbounded. A bounded blocking queue cannot increase size. An unbounded blocking queue can increase size, e.g. `LinkedList`.

Implementations:

#### PriorityBlockingQueue

It is an unbounded blocking queue that uses the same ordering rules as class `PriorityQueue`. Default initial capacity is 11. It orders its elements according to their natural ordering. Since it is unbounded, capacity increases as the number of elements increases. Adding elements may result in `OutOfMemoryException`.

#### LinkedBlockingQueue

It can be bounded if its capacity is given; otherwise, it will be unbounded.

Example:

```text
org.example.queue.PriorityQueueExample
org.example.queue.BlockingQueueExample
```

## Map

It is a group of objects used to store key-value pairs. When we have a requirement where searching is required, we should use a map.

- It does not allow duplicate keys, but values can be duplicate.
- Only one null key is allowed.
- Both key and values are objects and support any data type.
- Each key and value is considered an entry object. Thus, it is also known as a collection of entry objects.

### Map Methods

```java
size()
isEmpty()
containsKey(object)
containsValue(object)
put(key, value)
remove(key)
putAll(mapObject)
clear()
Set keySet()
Collection values()
Set<Map.Entry<key, value>> entrySet()
V getOrDefault(key, defaultValue)
V putIfAbsent(key, val)
computeIfAbsent(key, k -> val)
computeIfPresent(key, val)
```

Method meanings:

1. `size()`: Returns the number of entry objects, i.e. number of key-value pairs.
2. `putIfAbsent(key, val)`: If value for the given key is missing, then insert this value and return; otherwise, return the existing value.
3. `computeIfAbsent(key, k -> val)`: If key is missing, insert this value.
4. `computeIfPresent(key, val)`: If key is present, then only replace this value.

### Map Implementations

### 1. HashMap

Internally, `HashMap` uses the Hashtable data structure.

- Insertion order is not preserved.
- Duplicate keys are not allowed, but duplicate values are allowed.
- Only one null key can be inserted.
- Null values can be inserted.
- Implements Serializable and Cloneable.
- Extends an abstract class, `AbstractMap`.
- Best choice for searching.
- Default capacity is 16.
- Default load factor is 0.75.
- Threshold = capacity * load factor.
- Doubles the size once the threshold is reached.

#### Internal Working of HashMap

- `HashMap` uses the hashing technique to store the data.
- Internally, it comprises an array of the `Node` class.
- `Node[]`: Note that `Node` is nothing but a linked list node.
- `Node` class contains: hash, key, value, and `Node`.

#### `put(key, value)` Call

1. First, it calculates the hashcode of the key.
2. Then it calculates the index on which this key will get stored. This index will be within the capacity of the array.
3. Then it goes to that index location. If this location is empty:
    1. Then it creates an object of `Node` and adds it to this location.
    2. `Node` has four properties. Hash will contain the hashcode of key, key will contain key, value will contain value, and `Node` will be null.
4. If the index location from step 3 is not null:
    1. Then it compares the hashcode of the given key with the hash of the existing `Node`. If it matches, then it compares the key object with `Node`'s key using the `equals` method.
    2. If both are equal, then it replaces `Node`'s value with the new value.
    3. If hashcode or `equals` method comparison is false, then it creates a new `Node` object with the given key and value and adds it to the `Node`'s next node attribute if the next node is null.
    4. If the next node attribute is not null, then it keeps comparing all upcoming nodes like steps 3 and 4 until it inserts the new key and values.

#### `get(key)` Call

1. First, it calculates the hashcode of the key.
2. Then it calculates the index.
3. Then it goes to that index location. If the location is empty, then it returns null.
4. If the location is not empty, then it compares the hashcode of the given key with `Node`'s hash. If it matches, then it compares the key object with `Node`'s key using the `equals` method.
5. If both are equal, then it returns `Node`'s value.
6. If hashcode or `equals` method comparison is false, then it goes to the next node and does the same comparison mentioned in steps 4 to 6 until it visits all the nodes.

#### Hash Collision

Hash collision is when two different keys land on the same index and the second one might replace the first one.

To avoid this, `HashMap` uses the `Node` structure. With the help of `Node`, `HashMap` can store both keys at the same index.

### 2. LinkedHashMap

It maintains insertion order. Internally, it works the same as `HashMap`, i.e. it uses the Hashtable data structure to store data, i.e. array of nodes. Along with this, it uses a linked list structure to maintain insertion order.

### 3. IdentityHashMap

It works the same as `HashMap`, but it uses the `==` operator to compare keys, whereas `HashMap` uses the `equals` method to compare keys.

It should be used where we want to store different key object references which might contain the same values inside those key objects.

### 4. WeakHashMap

1. This contains a weak reference of the key object. `HashMap` and other Java classes use strong reference, which is the default reference.
2. Whenever the reference used to create the key object becomes null and no other references are present to that key, then garbage collection removes it from `WeakHashMap`. But if the reference becomes null in `HashMap`, then garbage collection does not remove it from `HashMap`.
3. It means garbage collection dominates `WeakHashMap` as it uses weak reference to keys.
4. It does not implement Serializable and Cloneable.

Example:

```text
org.example.map.WeakHashMapExample1
org.example.map.WeakHashMapExample2
```

## SortedMap

It is used to store a collection of key-value pairs in sorted order using key. By default, it uses the key's natural sorting order, but it can use custom sorting as well using a comparator. Null key and value are not allowed.

Methods:

```java
SortedMap subMap(fromKey, toKey)
SortedMap headMap(toKey)
SortedMap tailMap(fromKey)
K firstKey()
K lastKey()
Set keySet()
Collection values()
Set<Map.Entry<k, v>> entrySet()
Comparator comparator()
```

Method meanings:

1. `headMap(toKey)`: Returns a sorted map having keys less than the given key.
2. `tailMap(fromKey)`: Returns a sorted map having keys greater than the given key.
3. `comparator()`: Used to return the comparator used to order the keys.

## NavigableMap

Sub-interface of `SortedMap`. It contains methods used to provide navigation functionality.

Example:

```text
org.example.map.NavigableMapExample
```

## TreeMap

Internally, it uses Red-Black Tree. Red-Black Tree is a self-balanced binary search tree.

It is the implementation class of `SortedMap` and `NavigableMap`. Insertion order is not preserved. Elements get added based on the key's natural sorting.

It can also provide custom sorting using a comparator. Null keys are not allowed. Null values are allowed.

## Need of Concurrent Collections

1. Old collections, i.e. `ArrayList`, `HashMap`, `HashSet`, etc., are not synchronized.
2. We can use `Vector` and `Hashtable` as these are synchronized, but they lock the entire object. So performance is poor in this case.
3. We can use the following to get synchronized objects of old collection classes:
    1. `Collections.synchronizedList(list)`
    2. `Collections.synchronizedSet(set)`
    3. `Collections.synchronizedMap(map)`
4. But these returned objects are also poor in performance as all methods are synchronized.
5. Old Java collections are fail-fast.

To overcome all these issues, we need concurrent collections.

## ConcurrentModificationException

This exception is thrown by the iterator of a collection when a shared collection gets updated by another thread while iterating on this shared collection.

Each collection, i.e. `ArrayList`, `LinkedList`, `HashMap`, etc., returns an iterator. Iterator implementation is also given by these collections only. While returning `next()`, it checks for count. If it finds that the current count of the collection is not the same as the start of iteration, then it throws this exception.

This is also known as fail-fast. It is one of the problems with old Java collections.

Example:

```text
org.example.concurrent.ConcurrentModificationExceptionExample
```

## How Concurrent Collections Solve Problems of Old Collections

In old collections, the full object gets locked. In new concurrent collections, the entire collection gets divided into a number of segments.

Lock gets applied on an individual segment. So multiple threads can work on different segments simultaneously.

## `java.util.concurrent` Package

## Map

### ConcurrentMap

### ConcurrentHashMap

- This class can be used for `HashMap` if a multithreaded environment is there and we want the best performance.
- This is fail-safe, i.e. it does not throw `ConcurrentModificationException`.

Example:

```text
org.example.concurrent.FailSafeExample
```

- Null is not allowed as key or value in this.

#### Internal Working

1. Internally, it uses `Node[]`, i.e. array of `Node`, the same as `HashMap`.
2. This array gets divided into 16 buckets by default. These buckets are also known as segments.
3. So 16 threads can work in parallel on this map. This is also known as concurrency level. Here concurrency level is 16 by default.
4. Read operation does not require a lock.
5. Lock gets applied at the time of updating the map, i.e. `put`, `remove`, etc.
6. This lock gets applied on that bucket only. Other buckets are free for other threads.
7. Depending on the number of cores and other factors, we can increase or decrease the concurrency level at the time of invoking the constructor, but ideally, we should not touch the default as it might decrease performance.

## Collection

## List

### CopyOnWriteArrayList

- Multiple threads can perform read operations at the same time.
- At the time of update, it creates a copy of the main object, performs the update on that copied object, and makes this copied object the main object.
- Read happens on the main object.
- So if we do 100 updates, then 100 copies will get created. That is why we should use this only if fewer updates and more reads are required.

Difference over `ArrayList`:

1. Iterator of this is fail-safe.
2. In `ArrayList`, iterator can remove elements, but here it cannot. If we try, we will get `UnsupportedOperationException`.

## Set

### CopyOnWriteArraySet

- Multiple threads can perform read operations at the same time.
- At the time of update, it creates a copy of the main object, performs the update on that copied object, and makes this copied object the main object.
- Read happens on the main object.
- So if we do 100 updates, then 100 copies will get created. That is why we should use this only if fewer updates and more reads are required.

Difference over `ArrayList`:

1. Iterator of this is fail-safe.
2. If we try, we will get `UnsupportedOperationException`.

## Cursor

Cursor is an object which is used to traverse over a collection or list of objects.

### Types of Cursors

### 1. Enumeration

This is present in legacy classes like `Vector`.

Methods:

```java
hasMoreElements()
nextElement()
```

This type of cursor is only meant for read-only. This cannot add, remove, or replace elements from the collection. It moves in one direction only.

We can get this object using the `elements()` method of the `Vector` class.

Example:

```text
org.example.cursor.EnumerationExample
```

### 2. Iterator

This is the most widely used cursor. All collection classes use this. It is also known as a universal cursor.

Elements can be removed from the collection but cannot be added or replaced.

Methods:

```java
hasNext()
next()
remove()
```

It moves in one direction only. We can get this object using the `iterator()` method of the `Collection` interface.

Example:

```text
org.example.cursor.IteratorExample
```

### 3. ListIterator

It is a bidirectional cursor. It is only used for `List` class objects. It is a child of `Iterator`.

Elements can be added, removed, and replaced using this.

Methods:

```java
hasNext()
next()
remove()
add()
set()
hasPrevious()
previous()
```

We can get this object using the `listIterator()` method of the `List` interface.

Example:

```text
org.example.cursor.ListIteratorExample
```

### 4. Spliterator

It is used by streams. It is used to give better performance as we can split streams and create multiple spliterators, which we can pass to different threads to process in parallel.

It has `tryAdvance()` and `trySplit()` methods. `trySplit()` will split it equally. `tryAdvance()` is the same as `hasNext()` and `next()`.

Example:

```text
org.example.cursor.SpliteratorExample
```

## Comparable and Comparator

These are used for comparison, i.e. in sorting.

### 1. Comparable

It provides natural sorting order. So a class which wants natural sorting should implement this.

```java
int compareTo(Object obj2)
```

```java
obj1.compareTo(Object obj2)
```

Here, `obj2` is the inserted element and `obj1` is the element to be inserted.

- If this returns 0, then both objects are same.
- If it returns -1, then `obj1` object, i.e. object to be inserted, is less than the inserted object. It will go as the left child in the tree.
- If it returns 1, then `obj1` object, i.e. object to be inserted, is greater than the inserted object. It will go as the right child in the tree.

Example:

```text
org.example.set.Employee
```

### 2. Comparator

It is used to provide custom sorting. It is used when we want some sorting other than the object's natural sorting.

```java
int compare(obj1, obj2)
```

Here, `obj1` is the element to be inserted and `obj2` is the inserted element.

- If this returns 0, then both objects are same.
- If it returns -1, then `obj1` object, i.e. object to be inserted, is less than the inserted object. It will go as the left child in the tree.
- If it returns 1, then `obj1` object, i.e. object to be inserted, is greater than the inserted object. It will go as the right child in the tree.

Example:

```text
org.example.set.ComparatorExample
```

## Hashing

- Hashing is a way to convert an object into a number. This can be achieved with a hash function. A hash function takes an object as input and converts that object into a number. This function must return the same number for the same object.
- In Java, this can be achieved using the `hashCode()` method. The `hashCode()` method works as a hash function.
- If two objects are same, their hash code must be the same. If two objects have the same hashcode, they might or might not be the same.
- We should use the attribute which is used to show uniqueness of that object, like employee ID in the case of employee.

## Java HashMap vs Java Hashtable

1. `HashMap` is not synchronized, but `Hashtable` is synchronized.
2. `HashMap`: One null key is allowed and multiple null values are allowed. `Hashtable`: Null key and values are not allowed.
3. `HashMap`: Added in version 1.2. `Hashtable`: It is a legacy class.
4. `HashMap`: Extends `AbstractMap`. `Hashtable`: Extends `Dictionary`.

## Strong Reference and Weak Reference

1. By default, Java uses strong reference. But `WeakHashMap` uses weak reference.
2. Reference on which an object is created: if we create a weak reference of this reference, make the original reference null, and no other reference is present for the object, then the weak reference object gets garbage collected.
3. In order to use any reference as a weak reference, we can use the `java.lang.ref.WeakReference` class.

Example:

```text
org.example.map.WeakHashMapExample2 is an example of weak reference.
```
