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

Collection :- It is a root interface of collection framework.
    List :- It is an interface present in java.util package. It extends Collection interface.
        Methods in List :-
            1.add() 2.get() 3.remove() 4.contains() 5.indexOf() 6.lastIndexOf() 7.set()
        List implementations :-
            1. ArrayList :- It is based on array. Default capacity is 10. Load factor is 0.75. 10 * 0.75 = 7 Means whenever we try to add 7th
                element it will increase the size to double.
                Advantages :-
                    1. Dynamic size
                    2. Ease to use
                    3. Fast access
                    4. Ordered collection

                Disadvantages :-
                    1. Not thread safe
                    2. Slower than arrays
                    3. Performance decreases as size increases

            2. LinkedList :- It is based on doubly linked list data structure.
                Advantages :-
                    1. Manipulation is fast as compared with array list as no bits shift needed
                    2. Good memory utilization
                    3. Insertion at specific index is fast
                    4. It can act as list and dequeue as it implements both interfaces
                    5. Deletion operation is fast

                Disadvantages :-
                    1. More memory gets used to store same amount of data compared with array list
                    2. Will not give better performance when storing data and retrieving are often operations

            3. Stack :-

            4. Vector

