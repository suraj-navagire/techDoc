# Java 8 Features

## 1. Lambda Expression

A lambda expression is an anonymous function that provides a way to represent a one-method interface using an expression.

### Syntax

```java
(parameter) -> (expression)
```

### Types of Lambda Expressions

1. No parameter:

```java
() -> System.out.println("Hello");
```

2. One parameter:

```java
str -> System.out.println(str);
```

3. Multiple parameters:

```java
(str1, str2) -> System.out.println(str1 + str2);
```

4. Multiple statements:

```java
(str1, str2) -> {
    System.out.println(str1);
    System.out.println(str2);
}
```

Before lambda expressions, anonymous inner classes were used to provide implementation for functional interfaces.

### Anonymous Inner Class

It is a class without a name. Only one object is created for this. It is used to provide implementation for a method of an interface/abstract class or to override a method of a concrete class.

Example:

```text
org.example.lambda.AnonymousInnerClassExample
```

### Functional Interface

It is an interface with only one abstract method. Java provides built-in functional interfaces in the package `java.util.function`.

1. Predicate: This functional interface takes one parameter and returns a boolean value. Method name: `test()`
2. Function: This functional interface takes one parameter and produces output. Method name: `apply()`
3. Consumer: This functional interface takes one parameter but returns no output. Method name: `accept()`
4. Supplier: This functional interface takes no parameter but produces output. Method name: `get()`

Example:

```text
org.example.lambda.Car
```

Example:

```text
https://medium.com/@marcelogdomingues/java-lambda-expressions-techniques-for-advanced-developersava-lambda-expressions-techniques-for-c1d71c30bb1f
org.example.lambda.JavaBuiltInFunctionalInterfaces
```

## 2. Stream

In Java 8, stream functionality, i.e. `java.util.stream`, is introduced. It contains classes and interfaces to process a sequence of elements.

Stream operations do not change the original source.

### Ways to Create a Stream

A stream can be created using different sources like collections or arrays using `stream()` or `of()` methods.

1. Using an array:

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
```

2. Using `Stream.of()`:

```java
Stream<String> stream = Stream.of("a", "b", "c");
```

3. Using a list:

```java
List<String> list = new ArrayList<>();
Stream<String> stream = list.stream();
```

Example:

```text
org.example.streams.StreamCreationExample
```

### Types of Stream Operations

There are two types of stream operations:

1. Intermediate operations: These return a `Stream`.
2. Terminal operations: These return a result of a definite type.

Example:

```java
long count = list.stream().distinct().count();
```

In the above example, `distinct()` is an intermediate operation and `count()` is a terminal operation.

### Operations on Stream

1. Filter:

```java
list.stream().filter(name -> name.startsWith("A"));
```

```text
org.example.streams.FilterDemo
```

2. Sort:

```java
list.stream().sorted();
```

```text
org.example.streams.SortDemo
```

3. Map:

```java
// Method reference
list.stream().map(String::toUpperCase);

// Equivalent lambda
list.stream().map(str -> str.toUpperCase());
```

```text
org.example.streams.MapDemo
```

4. GroupBy:

```java
// Method reference
list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

// Equivalent lambda
list.stream().collect(Collectors.groupingBy(employee -> employee.getDepartment()));
```

```text
org.example.streams.GroupByDemo
```

5. Collect:

```java
list.stream().filter(n -> n > 10).collect(Collectors.toList());
```

```text
org.example.streams.CollectDemo
```

6. ToArray:

```java
// Method reference
String[] array1 = list.stream().toArray(String[]::new);

// Equivalent lambda
String[] array2 = list.stream().toArray(size -> new String[size]);
```

```text
org.example.streams.StreamToArrayDemo
```

7. FlatMap:

```java
// Method reference
listOfLists.stream().flatMap(List::stream);

// Equivalent lambda
listOfLists.stream().flatMap(list -> list.stream());
```

```text
org.example.streams.FlatMapDemo
```

8. Reduce:

```java
// Method reference
int sum1 = numbers.stream().reduce(0, Integer::sum);

// Equivalent lambda
int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
```

```text
org.example.streams.ReduceDemo
```

9. Match:

```java
boolean result = list.stream().anyMatch(name -> name.startsWith("A"));
```

```text
org.example.streams.MatchDemo
```

10. FindFirst:

```java
Optional<String> first = list.stream().findFirst();
```

```text
org.example.streams.FindFirstDemo
```

11. Max, Min, Distinct, Limit, Skip:

```java
// Method reference
numbers.stream().max(Integer::compareTo);
numbers.stream().min(Integer::compareTo);

// Equivalent lambda
numbers.stream().max((a, b) -> a.compareTo(b));
numbers.stream().min((a, b) -> a.compareTo(b));

numbers.stream().distinct();
numbers.stream().limit(5);
numbers.stream().skip(2);
```

```text
org.example.streams.AdditionalFunctions
```

## 3. Method Reference

Method reference is a special type of lambda expression which can be used with the `::` operator.

There are four kinds of method references:

### 3.1. Reference to a Static Method

```java
// Lambda
Function<String, Integer> lambda = str -> Integer.parseInt(str);

// Method reference
Function<String, Integer> methodReference = Integer::parseInt;
```

### 3.2. Reference to an Instance Method of a Particular Object

```java
String prefix = "Hello ";

// Lambda
Supplier<String> lambda = () -> prefix.toUpperCase();

// Method reference
Supplier<String> methodReference = prefix::toUpperCase;
```

### 3.3. Reference to an Instance Method of an Arbitrary Object of a Particular Type

```java
// Lambda
Function<String, String> lambda = str -> str.toUpperCase();

// Method reference
Function<String, String> methodReference = String::toUpperCase;
```

### 3.4. Constructor Reference

```java
// Lambda
Supplier<ArrayList<String>> lambda = () -> new ArrayList<>();

// Method reference
Supplier<ArrayList<String>> methodReference = ArrayList::new;
```

Example:

```text
org.example.lambda.MethodReferenceExample
```

## 4. Static/Default Method in Interface

In Java 8, we can write implementation for a method using the `default` and `static` keywords. This is introduced to support legacy systems.

If we have a requirement where we have to decide between an interface and an abstract class, then we should use an abstract class where we have to provide a common method and its definition.

1. Default method: We can write a default method and provide a definition for the same. This default method can then be overridden in a subclass.
2. Static method: We can write a static method and provide a definition for the same. This static method cannot be overridden in a subclass.

## 5. Optional Class

The Optional class is introduced to handle null pointers. Before this, we had to check for null to avoid null pointers.

But now we can use the Optional class, and we can use its functions to check whether a non-null value is present or not. If the value is null, then we can return some default values.

Example:

```text
org.example.optional.OptionalDemo
```

## 6. `forEach` Method in Iterable Class

In Java 8, the `forEach` method is added in the `Iterable` interface. It is a default method and its definition is also given in the `Iterable` class. It executes an action on each element in a collection.

## 7. Date/Time API

### Drawbacks of Old Classes

1. Not thread-safe: `java.util.Date` and `Calendar` APIs are not thread-safe. Developers are required to add additional logic to make them thread-safe.
2. Poor design: Old `Date` and `Calendar` APIs are poorly designed and have fewer date operations.
3. Difficult timezone handling: Developers had to write a lot of code to deal with timezone issues.

### Advantages of New Date/Time Classes

1. All the drawbacks are not there in the new classes. New classes are immutable.

New classes are present in the `java.time` package.

### Local

Simplified date/time API with no complexity of timezone handling.

```text
LocalDate
LocalTime
LocalDateTime
```

### Zoned

Specialized date/time API to deal with timezone.

```text
ZonedDateTime
```

To find differences in date/time, two new classes are introduced:

1. Period: It deals with a date-based amount of time.
2. Duration: It deals with a time-based amount of time.

Example:

```text
https://www.tutorialspoint.com/java/java_datetime_api.htm
org.example.datetime.LocalDateTimeDemo
org.example.datetime.ZonedDateTimeDemo
```
