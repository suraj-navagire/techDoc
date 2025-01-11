Collection :-
    Group of individual objects that represents single entity is known as collection.

Basics :-
    Array : 1.Array is collection of homogeneous elements. In java there is class for every array object.
            2.Class name of one dimensional integer array is "[I". These classes are not accessible to us.
            3.Array can be created by : int[] a = new int['a']  .  Allowed data types to provide size are byte, short, char and int as these can be
            promoted to int.
            4. Cannot pass -ve as size
            5. Size should be within int size

    Array of Arrays: Array of arrays are one dimensional, 2 dimensional .... upto 51 dimensions
            1. 2D arrays :- int[][] a = new int[2][]; This is Array or array
                            a[0] = new int[1];
                            a[1] = new int[1];
                            a[0][0] = 1;
                            a[1][0] = 2;
            2. 3D arrays :- int[][][] a = new int[1][][]; This is array of 2D array
                            a[0] = new int[1][];
                            a[0][0] = new int[1];
                            a[0][0][0] = 1;

    Anonymous Arrays : Arrays without reference variable. i.e "new int[]{2, 5,8}".  This can only be used for passing to method.

    Example : org.example.ArrayExample, org.example.MultiDimensionalArrayExample

Array vs Collection:-
    1. Arrays are fixed in size. Collections are growable in nature.
    2. Arrays can hold primitive and object type data. Collection can only hold object type data.
    3. Arrays itself is a data structure but don't have utility methods or algorithms. Collection uses underlying data structure to hold data and have utility methods
        and uses algorithms for better performance.

Collections framework:-
    It contains interfaces, classes and algorithms to solve collection of data related problems.
    It has 9 dominant interfaces :
        1. Collection (Interface): Parent Interface to hold group of objects as single entity.
        2. List (Interface) : Represents group of objects as single entities where insertion order gets preserved and Duplicates are allowed.
            1. ArrayList
            2. LinkedList
            3. Vector (Legacy Class)
                1. Stack(Legacy Class)
        3. Set (Interface) : Represents group of object as single entity where insertion order not gets preserved and duplicates are not allowed.
            1. HashSet
            2. LinkedHashSet
            3. SortedSet (Interface)
                1. NavigableSet (Interface)
                    1. TreeSet
        4. Queue (Interface): Represents group of individual objects as prior to processing. Follows FIFO order.
            1. PriorityQueue
            2. BlockingQueue (Interface)
                1. PriorityBlockingQueue
                2. LinkedBlockingQueue

        1. Map (Interface): Parent Interface to hold key-value pair.
            1. HashMap
                1. LinkedHashMap
            2. WeakHashMap
            3. IdentityHashMap
            4. Hashtable extends Dictionary
                1. Properties
            5. SortedMap (Interface)
                1. NavigableMap (Interface)
                    1. TreeMap

Collection :- Provides most commonly used methods that can be used by child classes.
            1. boolean add(Object obj)
            2. boolean addAll(Collection c)
            3. boolean remove(Object obj)
            4. boolean removeAll(Collection c)
            5. boolean retailAll(Collection c)
            6. void clear()
            7. boolean contains(Object obj)
            8. boolean containsAll(Collection obj)
            9. boolean isEmpty()
            10. int size()
            11. Iterator iterator()
            12. Object[] toArray()

Collection vs Collections :-
    1. Collection is interface. It is parent interface for most of the classes of collection framework.
    2. Collections is a utility class which provides several methods like sorting, searching, shuffling for collection objects.
        Collections method : sort(), shuffle(), reverse(), binarySearch(), disjoint()

Generics :- To provide type safety. To solve problem related to type casting. To help developer to write less redundant code.

Collection :- It is a root interface of collection framework.
    List :- It is an interface present in java.util package. It extends Collection interface.
        Methods in List :-
            1.add() 2.get() 3.remove() 4.contains() 5.indexOf() 6.lastIndexOf() 7.set()
        List implementations :-
            1. ArrayList :- It is based on array. Default capacity is 10. When capacity is full it increases capacity. New size becomes (oldSize * (3/2)).
                - Serializable
                - Cloneable
                - RandomAccess interface - This is marker interface which tells any random elements can be accessed with same speed time complexity.
                Advantages :-
                    1. Dynamic size
                    2. Ease to use
                    3. Fast retrieval
                    4. Insertion order is preserved

                Disadvantages :-
                    1. Not thread safe
                    2. Slower than arrays
                    3. Performance decreases as size increases
                    4. Inserting at position is slower
                    5. Deletion at position is slower

            2. LinkedList :- It is based on doubly linked list data structure.
                - Serializable
                - Cloneable
                Advantages :-
                    1. Manipulation is fast as compared with array list as no bits shift needed
                    2. Good memory utilization
                    3. Insertion at specific index is fast
                    4. It can act as list and dequeue as it implements both interfaces
                    5. Deletion at specific index is fast
                    6. Insertion order is preserved.

                Disadvantages :-
                    1. More memory gets used to store same amount of data compared with array list
                    2. Retrieval is slow

            3. Vector :- It is based on array. Initial capacity is 10 same as ArrayList. When capacity is full size increases. New size becomes (oldSize * (3/2)).
                - Serializable
                - Cloneable
                - RandomAccess interface
                Advantages:-
                    1. Same as ArrayList
                    2. It is thread safe. All methods are synchronized. ArrayList is not.
                    3. It is legacy class. Contains few redundant methods like addElement, removeElement

                Disadvantages :-
                    1. Insertion is costly in case of multi-threaded environment as its synchronized
                    2. Read also costly since its synchronized
                    3. All methods are synchronized has takes more time then ArrayList

                Example :- org.example.List.VectorExample

            4. Stack :- Extends vector. push(Element), Element pop(), Element peek(), index search(element)
                    - Based on LIFO
                    - Insertion order is preserved
                    - search(element) method returns position of given element with respect to LIFO order if element is at top it will return 1 if its last then n.

                 Example :- org.example.List.StackExample

    Set :- It is an interface present in java.util package. It extends Collection interface.
        Set Implementation :-
            Example : org.example.set.SetExample
            1. HashSet : Internally uses Hashtable data structure. Searching is fast. Duplicates are not allowed. We can add null value. Initial capacity 16.
                Default Load factor is 0.75. Load factor is nothing but at what size capacity should be increased. By-default HashSet increases its size when 75% capacity is
                used. Then it creates new object with double the capacity and copies all the old element to new one. Then old object becomes available for will be garbage
                collection.
                - Serializable
                - Cloneable

                Internal Working :-
                    - Same as HashMap. HashSet uses HashMap (i.e. Hashtable data structure) class to store elements. HashMap is key value pair. HashMap considers given
                    element as key and store it using put() method, where element becomes key and null becomes value.

                HashSet Implementation :-
                    1. LinkedHashSet : It extends HashSet class. It maintains order of insertion.

            2.SortedSet : It is an interface. It stores elements based on their natural sorting order.
                methods : E first(), E last(), SortedSet headSet(element), SortedSet tailSet(element), SortedSet (firstElement, lastElement),
                Comparator comparator() : This method will return comparator if used. Returns null if natural sorting is used.

                Child interface/classes :-
                    1. NavigableSet : This is an interface. It contains method used to navigation.
                        methods :
                        E floor(e) - Returns the greatest element from set which is less than or equal to given element. else null
                        E lower(e) - Returns the greatest element from set which is less than given element. else null.
                        E ceiling(e) - Returns the smallest element from the set which is greater than or equal to given element. else null.
                        E higher(e) - Returns the smallest element from the set which is greater than given element. else null.
                        E pollFirst() - Returns and removes the smallest element from the set. else null.
                        E pollLast() - Returns and removes the greatest element from the set . else null.
                        E descendingSet() - Returns navigable set in reverse order.

                    Implementation :-
                    1. TreeSet : This is implementation class of NavigableSet. Internally it uses TreeMap which uses self balanced binary tree.
                        Internal working :-
                            - First element will be root element.
                            - This root will have left child and right child
                            - Next element will be left or right based on comparison result. If current element is less than root than it will go left
                                else it will go right. And so on.
                            - For comparison Elements must be comparable to provide natural sorting. We can also pass comparator for custom comparison.
                            - We can pass null element only once i.e. at start. If we insert next element than it will throw null pointer.
                            - Elements gets traversed using preorder traversing.
                        Example : org.example.set.TreeSetExample

    Queue (I) :- It is a group of objects that are about to be processed. Queue follows first in first out concept.
        boolean add(E e), boolean offer(E e) - Both are used to add element at the tail of the queue or as per the priority.
        E remove() - Removes and returns head of the queue. Returns NoSuchElement exception if queue is empty.
        E poll() - Removes and returns head of the queue. Returns null if queue is empty.
        E element() - Returns head of the queue. Returns NoSuchElement exception if element is not present.
        E peek() - Returns head of the queue.  Returns null if queue is empty

        Queue implementations :-
            1. LinkedList

            2. PriorityQueue : Represents a group of objects about to be processed based on some priority. Element gets added and deleted according to some priority.
                Priority can be default natural sorting order or custom sorting order. Insertion order is not preserved and duplicate are not allowed.
                Default size is 11 and default priority order is natural sorting order.

            3. BlockingQueue (I) - This is thread safe as all the methods are atomic. Blocks the producer if queue is full and blocks the consumer if queue is empty.
                If attempts to insert null element throws null pointer exception. Has 2 types, bounded and unbounded. Bounded blocking queue can.t increase size.
                Unbounded blocking queue can increase size, e.g. LinkedList.

                Implementations :-
                    1. PriorityBlockingQueue :- It is an unbounded blocking queue that uses the same ordering rules as class PriorityQueue. Default initial capacity 11
                        that orders its elements according to their natural ordering. Since it is unbounded capacity increases as increase in number of elements
                        , adding elements may result in outOfMemoryException.

                    2. LinkedBlockingQueue :- It can be bounded, if its capacity is given, else it will be unbounded.

                    Example :-org.example.queue.PriorityQueueExample, org.example.queue.BlockingQueueExample

    Map (I) - It is a group of objects used to store key value pair. When we have requirement where searching is required  we should use map.
        - It does not allow duplicate keys, but values can be duplicate.
        - Only one null key is allowed.
        - Both key and values are object and support any data type
        - Each key and value is considered as entry-objects. Thus, it is also knows as collection of entry objects.

        methods :
            1. size() - Returns number of entry objects i.e. number of key value pairs.
            2. isEmpty()
            3. containsKey(object)
            4. containsValue(object)
            5. put(key, value)
            6. remove(key)
            7. putAll(mapObject)
            8. clear()
            9. set keySet()
            10. collection values()
            11. Set<Map.Entry<key, value>> entrySet()
            12. v getOrDefault(key, defaultValue)
            13. v putIfAbsent(key, val) - if value for given key is missing then insert this val and return else return existing val
            14. computeIfAbsent(key, k -> val) - If key is missing insert this val
            15. computeIfPresent(key, val) - If key is present then only replace this val




Cursor -
    Cursor is an object which is used to traverse over a collection or list of objects.
        Types of cursors :-
            1. Enumeration : This is present in legacy classes like Vector. hasMoreElements() , nextElement(). This type of cursor is only meant for readonly. This cannot
                add, remove or replace elements from collection. Move in one directional only. We can get this object using elements() method of vector class.
                Example :- org.example.cursor.EnumerationExample

            2. Iterator : This is most widely used cursor. All collection classes use this. Also known as universal cursor. Elements can be removed from collection
                but cannot add or replace. hasNext(), next(), remove(); Move in one direction only. We can get this object using iterator() method of collection (interface).
                Example :- org.example.cursor.IteratorExample

            3. ListIterator : It is bidirectional cursor. Only used for List class objects It is child of Iterator. Elements can be added, removed, replaced using this.
                hasNext(), next(), remove(), add(), set() , hasPrevious(), previous(). We can get this object using listIterator() method of List(interface) .
                Example :- org.example.cursor.ListIteratorExample

            4. SplitIterator :



Comparable and Comparator -
    These are used for comparison i.e. in sorting.
        1. Comparable :- It provides natural sorting order. So Class which want natural sorting should implement this.
            int compareTo(Object obj2)
            obj1.compareTo(Object obj2) - Here obj2 is inserted element and obj1 is element to be inserted.
                - So if this returns 0 than both object are same
                - If it returns -1 than obj1 object (object to be inserted) is less than inserted object. It will go as left child in tree.
                - If it returns 1 then obj1 object (object to be inserted) is greater than inserted object. It will go as right child in tree.
            Example :- org.example.set.Employee

        2. Comparator :- It is used to provide custom sorting. It is used when we want some sorting other than objects natural sorting.
            int compare(obj1, obj2) - Here obj1 is element to be inserted and obj2 is inserted element.
                 - So if this returns 0 than both object are same
                 - If it returns -1 than obj1 object (object to be inserted) is less than inserted object. It will go as left child in tree.
                 - If it returns 1 then obj1 object (object to be inserted) is greater than inserted object. It will go as right child in tree.
            Example :- org.example.set.ComparatorExample