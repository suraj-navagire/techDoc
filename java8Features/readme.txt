1. Lambda Expression :-
    Lambda expression is an anonymous function that provide a way to represent one method interface using an expression.

    Syntax :-
        (parameter) -> (expression)

    Types of lambda expressions :-
            1. No parameter : () -> System.out.println("Hello");
            2. One parameter : str -> System.out.println(str);
            3. Multiple parameter : (str1, str2) -> System.out.println(str1+str2);
            4. Multiple statement : (str1, str2) -> {
                                                        System.out.println(str1);
                                                        System.out.println(str2);
                                                    }

    Before lambda expression anonymous inner classes were used to provide implementation for functional interfaces.

    Anonymous inner class :- It is a class without name. Only one object is created for this. It is used to provide implementation for a method of interface/abstract class or
                        overriding method of concrete class.
                        Example :- org.example.lambda.AnonymousInnerClassExample

    Functional interface :- It is an interface with only one abstract method. Java provide built in functional interfaces in package java.util.function
             1. Predicate :- This functional interface takes one parameter and returns boolean value. method name => test()
             2. Function :- This functional interface takes 1 parameter and produces output. method name => apply()
             3. Consumer :- This functional interface takes 1 parameter but returns no output. method name => accept()
             4. Supplier :- This functional interface takes no parameter but produces output . method name => get()
             Example :- org.example.lambda.Car


    Example :- https://medium.com/@marcelogdomingues/java-lambda-expressions-techniques-for-advanced-developersava-lambda-expressions-techniques-for-c1d71c30bb1f,
            org.example.lambda.JavaBuiltInFunctionalInterfaces

2. Stream :-
    In java 8 stream functionality i.e. java.util.stream is introduced which contains classes and interfaces to process sequence of elements.
    Stream operations don't change original source.

    Ways to create stream :
        Stream can be created using different sources like collections or arrays using stream() or of() methods.

        1. String[] arr = new String[]{"a", "b", "c"};
           Stream<String> stream = Arrays.stream(arr);

        2. Stream<String> stream = Stream.of("a", "b", "c");

        3. List<String> list = new ArrayList<>();
           Stream<String> stream = list.stream();

    Example :- org.example.streams.StreamCreationExample

    There are 2 types of stream operations :-
        1. Intermediate operations : It returns Stream.
        2. Terminal operations : It returns result of definite type

        long count = list.stream().distinct().count();

        In above example distinct() is intermediate operation and count is terminal operation.

    Operations on Stream :-
        1. Filter :- org.example.streams.FilterDemo
        2. Sort :- org.example.streams.SortDemo
        3. Map :- org.example.streams.MapDemo
        4. GroupBy :- org.example.streams.GroupByDemo
        5. Collect :- org.example.streams.CollectDemo
        6. ToArray :- org.example.streams.StreamToArrayDemo
        7. FlatMap :- org.example.streams.FlatMapDemo
        8. Reduce :- org.example.streams.ReduceDemo
        9. Match :- org.example.streams.MatchDemo
        10. FindFirst :- org.example.streams.FindFirstDemo
        11 Max, Min, Distinct, limit, Skip:- org.example.streams.AdditionalFunctions

3. Method reference :-
    Method reference is special type of lambda expression which can be used, using :: operator

    There are 4 kinds of method references :
        1. Reference to Static method
        2. Reference to instance method of particular object
        3. Reference to an instance method of an arbitrary object of a particular type
        4. Constructor reference
    Example :- org.example.lambda.MethodReferenceExample

4. Static/Default method in interface:-
    In java 8 we can write implementation for a method using default and static keyword. This is introduced to support legacy system. If we have
    a requirement where we have to decide between interface and abstract class then we should use abstract class where we have to provide common method
    and its definition.
        1. Default method - We can write default method and provide definition for the same. This default method then we can override in subclass.
        2. Static method - We can write static method and provide definition for the same. This static method we cannot override in subclass.

5. Optional Class :-
    Optional class is introduced to handle null pointers. Before this we had to check for null to avoid null pointer. But now we can use optional
    class, and we can use its functions to check if non-null value present or not or if null then we can return some default values.

    Example :- org.example.optional.OptionalDemo

6. forEach method in Iterable class :-
    In java 8 forEach method is added in Iterable interface. Its default method and its definition is also given in iterable class. It executes
    an action on each element in collection.

7. Date / Time API :-
    Drawbacks of Old classes:-
        1. Not thread safe : java.util.Date and Calendar APIs are not thread safe. Developers are required to add additional logic to make it thread safe.
        2. Poor design : Old Date and calendar APis are poorly designed and has less date operations.
        3. Difficult time Zone handling : Developers had to write lot of code to deal with time zone issues.

    Advantages of new Date/Time classes :-
        1. All the drawbacks are not there in new classes. New classes are immutable.

    New classes are present in java.time package
        1. Local :- Simplified date/time API with no complexity of timezone handling
            LocalDate, LocalTime, LocalDateTime
        2. Zoned :- Specialized date/time API to deal with timezone
            ZonedDateTime

        To find differences in date/time 2 new classes is introduced
            1. Period :- It deals with date based amount of time
            2. Duration :- It deals with time bases amount of time

    Example :- https://www.tutorialspoint.com/java/java_datetime_api.htm
        org.example.datetime.LocalDateTimeDemo, org.example.datetime.ZonedDateTimeDemo