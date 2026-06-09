# Design Patterns

This project is created to provide an understanding of design patterns.

## Before Design Patterns

Before design patterns, let us understand some basic concepts.

### 1. Inheritance (`is-a`)

Inheritance shows a parent-child relationship, also known as an `is-a` relationship.

Example:

```text
Dog is an Animal
```

### 2. Association (`has-a`)

Association is known as a `has-a` relationship. When a class contains an instance of some other class, it is known as association.

It has two types:

#### 2.1. Aggregation

Aggregation is a weak association. The inner object can exist independently even if the outer object gets destroyed.

Example:

```text
Library and Book
```

A library has many books. If we destroy the library object, even then the book can exist somewhere else.

#### 2.2. Composition

Composition is a strong association. The inner object cannot exist independently without the outer object.

The lifetime of the contained object is bound to the lifetime of the container object.

Example:

```text
Car and Engine
```

An engine cannot exist without a car object. So, once the car object gets destroyed, the engine will also get destroyed.

Arrow to be used:

```text
designPatterns/src/main/resources/Arrows_UML.png
```

## What Is a Design Pattern?

A design pattern is a solution to commonly occurring problems in software design. They are like pre-made blueprints that you can customize to solve design problems in your code.

## SOLID Principles in Software Design

These are the design principles that need to be considered while designing software.

SOLID with real examples:

- SRP: separating business logic vs orchestration
- OCP: adding features without modifying existing code
- LSP: correct inheritance usage
- ISP: avoiding fat interfaces
- DIP: programming to abstractions

### 1. Single Responsibility Principle

A class should have only one reason to change. That is, a class should have only one job to do, one task, or one purpose.

Example:

```text
com.patterns.solid.SingleResponsibilityViolation
com.patterns.solid.SingleResponsibilityCorrect
```

### 2. Open/Closed Principle

A class should be open for extension but closed for modification.

Suppose you have a `PaymentProcessor` that contains code to process credit card payments. In the future, if we want to add PhonePe payment and add that code in the same class, then it is a violation of the open/closed principle.

Instead, we should write a parent interface, `PaymentProcessor`, and implement it using new payment processors. In this way, it is easy to add new payment methods without changing existing classes.

Example:

```text
com.patterns.solid.OpenClosedViolation
com.patterns.solid.OpenClosedCorrect
```

### 3. Liskov Substitution Principle

It states that an object of a superclass can be replaced by an object of a subclass without affecting the correctness of a program.

Example:

```text
com.patterns.solid.LiskovSubstitutionShapeCorrect
com.patterns.solid.LiskovSubstitutionShapeViolation
```

#### Case 1: Rectangle and Square

Suppose we have a `Rectangle` class with `width` and `height` variables and setter methods.

It has an `area()` method to return the area of a rectangle:

```text
width * height
```

Now consider that we have a `Square` class which is a rectangle. So, we will end up extending `Rectangle` for the `Square` class to reuse the `area()` method and setter methods.

For a rectangle, width and height are independent. But for a square, they are dependent. So exposing separate setters for width and height for the `Square` class is not right.

Problem/Violation:

```text
Square violates the Liskov Substitution Principle because it changes the behavior of Rectangle's setters.
Rectangle assumes width and height are independent, but Square makes them dependent.
So when a Square is substituted for a Rectangle, client expectations break.
```

Fix/Solution:

```text
Redesign this and use the correct approach to avoid inheritance and use a common abstraction like Shape.
```

Also, if the parent class has a method which the child class cannot use or override, then LSP is broken.

#### Case 2: Bird, Sparrow, and Penguin

Suppose we have a `Bird` interface with methods `eat` and `fly`. Whoever implements `Bird` will have to implement both methods.

Suppose we have `Sparrow` and `Penguin` classes. As we know both are birds, we will end up implementing the `Bird` interface for both classes. Then they will have to override both methods.

Problem/Violation:

```text
The LSP violation occurs because the Bird interface forces all birds to implement fly().
Penguin cannot fly, so it throws an exception, which breaks the contract.
When the client substitutes a Sparrow with a Penguin, behavior changes, violating LSP.
```

Fix/Solution:

```text
The fix is to redesign interfaces to separate behavior into smaller interfaces like Flyable.
```

Example:

```text
com.patterns.solid.LiskovSubstitutionBirdViolation
com.patterns.solid.LiskovSubstitutionBirdCorrect
```

### 4. Interface Segregation Principle

This principle states that interfaces should be segregated properly. This principle is similar to the single responsibility principle.

One interface should not contain all methods just for the sake of having them. It should have only required and relevant methods.

Methods should be segregated into multiple interfaces as per requirement.

Example:

```text
com.patterns.solid.InterfaceSegregationViolation
com.patterns.solid.InterfaceSegregationCorrect
```

### 5. Dependency Inversion Principle

It states that High-level class should not directly depend on low-level concrete class. Both should depend on abstraction.

Consider Ther is a `Car` class which has `Engine` interface as an instance variable, instead of directly creating `PetrolEngine` or `DieselEngine`.

At runtime, the required engine implementation object is passed to `Car`, so `Car` can work with any engine without changing its code.

Example:

```text
com.patterns.solid.DependencyInversionViolation
com.patterns.solid.DependencyInversionCorrect
```

---

## Categories of Design Patterns

There are three categories of design patterns:

```text
https://www.digitalocean.com/community/tutorials/java-design-patterns-example-tutorial
https://www.youtube.com/watch?v=4ff_KZdvJn8
```

## 1. Creational Design Patterns

Creational design patterns give us the best possible way to instantiate an object in a specific situation.

### 1.1. Singleton Design Pattern - Good to know

The Singleton design pattern restricts instantiation of an object. It allows only one instance of a class inside a Java Virtual Machine.

Example:

```text
com.patterns.creational.singleton.EagerInitialization
com.patterns.creational.singleton.EagerInitializationIssueExample
com.patterns.creational.singleton.StaticBlockInitialization
com.patterns.creational.singleton.LazyInitialization
com.patterns.creational.singleton.ThreadSafeInitialization
com.patterns.creational.singleton.StaticInnerClassInitialization
```

Reflection, deserialization, and cloning are three ways to break a singleton.

#### Reflection

In reflection, we can make the constructor accessible by making its visibility true.

To overcome the reflection issue, we can use `Enum`, or we can throw an exception from the constructor if the instance variable is not null.

#### Deserialization

When we do deserialization of a serialized object, it creates a new object.

To overcome this issue, we can use the `readResolve` method.

#### Cloning

In cloning, we can create a clone of an object using the clone method.

We can overcome this by overriding the clone method and throwing a `CloneNotSupportedException`.

Example:

```text
com.patterns.creational.singleton.FinalSingleton
```

### 1.2. Factory Pattern / Factory Method - Mandatory

When we have a situation where we have a superclass and multiple subclasses, and based on input we have to return one of the subclasses, we can use this pattern.

This pattern takes the responsibility of instantiation of subclasses from client code to the factory class.

We can use the Singleton pattern on the factory class or make the factory method static.

Example:

```text
com.patterns.creational.factoryPattern.ComputerTest
```

### 1.3. Abstract Factory - Good to know

It adds another layer on top of the Factory pattern.

Abstract Factory returns a factory class. This returned factory class will return the desired object.

Example:

```text
com.patterns.creational.abstractFactory.ShapeTest
https://www.youtube.com/watch?v=5hXZnI86E2Y
```

### 1.4. Builder Design Pattern - Mandatory

The Builder design pattern helps in the creation of complex objects.

Builder design pattern should be used in the following scenarios:

1. When we want to create complex objects, such as objects with a large number of constructor parameters, possibilities of having new properties or attributes in the future, and not all constructor arguments are mandatory.
2. When we want an object similar to an immutable object.

Example:

```text
https://www.youtube.com/watch?v=4ff_KZdvJn8
com.patterns.creational.builder.URLWithBuilder
```

### 1.5. Prototype Design Pattern

If we have a design requirement where we have to create copies of objects of a class, then we can use the Prototype design pattern.

If we do not use it, then we will have to write a lot of redundant code to make copies of objects.

Implement the `Cloneable` interface where we have to achieve the Prototype pattern. Classes that implement `Cloneable` are known as prototypes.

Cloning is nothing but copying. There are two types of copy: shallow and deep.

#### Shallow Copy

If we implement the `Cloneable` interface and override the clone method, then the default implementation will give shallow copy behavior.

Example:

```text
com.patterns.creational.prototype.CompanyShallowExample
```

#### Deep Copy

If we implement the `Cloneable` interface and override the clone method, then along with the default implementation, clone all non-primitive and non-immutable fields explicitly.

Example:

```text
com.patterns.creational.prototype.CompanyDeepExample
```

We can create a copy using copy constructors as well. This is more advantageous than implementing `Cloneable`.

The following is the most useful example of Prototype:

```text
com.patterns.creational.prototype.copyConstructor.PrototypeTest
```

## 2. Structural Design Patterns

Structural design patterns provide different ways to create a class structure. For example, using inheritance and composition to create large objects from small objects.

### 2.1. Adapter Design Pattern - Mandatory

Adapter design pattern is a structural design pattern that allows objects with different interfaces to collaborate.

This is useful when we want to reuse existing classes without modifying their code, but their interface does not match the requirements of the client.

It extends the target interface and creates association with the adaptee interface. The target interface is the one which is dealing with the client.

Example:

```text
com.patterns.structural.adapter.Client
```

### 2.2. Bridge Design Pattern

It is used to decouple abstraction from implementation.

That is, you are removing some functionality from an existing abstract class, creating a new interface for that functionality, creating new implementation classes, and adding that functionality to it. Then add this new interface inside the abstract class and provide its instance at runtime to the constructor of the abstract class.

#### Problem Solved by Bridge

1. Suppose we have a requirement to make a system to draw different shapes. What will you do? You will make a shape interface. Then you will add its implementations. Consider initially you have created two shapes: `Triangle` and `Square`.
2. Now consider that in the future, the client wants red-colored and blue-colored shapes. Then, in order to reuse the shape-making code, you will create new classes that will extend the existing shapes. That is, you will make `RedTriangleShape` which will extend `TriangleShape`, `BlueTriangleShape` which will extend `TriangleShape`, `RedSquareShape` which will extend `SquareShape`, and `BlueSquareShape` which will extend `SquareShape`.
3. Till now, you will feel like you have designed a great system, but wait. What if in the future the client adds more shapes and more colors?

Consider even if the client demands two more shapes and two more colors, then you will end up having:

```text
total shapes = 4
total colors = 4
total colored shape classes = 16, i.e. Cartesian product
```

Then you will realize how messy the system is.

Example:

```text
com.patterns.structural.bridge.problem.Client
```

#### Solution

1. First understand the problem.
2. Here, drawing a shape and coloring it are two different tasks. This we can decouple.
3. Create a `Color` interface and create its implementations.
4. Use association over inheritance, i.e. add a color object inside shape.
5. Make shape an abstract class so that it can contain `Color`, and write its implementations.
6. Pass the color object at the time of creating the shape.
7. Here, if you want to add two more colors, then you will have to create only two color classes, not more. You can pass the color object to the shape at the time of creation. If you want to add two more shapes, then also you will have to create only two classes.

#### Elements of Bridge Design Pattern

1. Implementor: Functionality which we are separating out/decoupling. Here, `Color` is the implementor.
2. Concrete implementation: Concrete classes which implement the implementor. Here, `RedColor` and `BlueColor`.
3. Abstraction: Core of the Bridge design pattern. It holds the instance of implementor. It provides the abstract method which is used by the client. Here, the `Shape` class. `draw()` is the method which is providing abstraction to the client. Also, the `Shape` class delegates the `draw()` call to the color object.
4. Concrete abstraction: Concrete classes which extend the abstract class. It uses concrete implementor objects to do work. Here, it uses `RedColor` or `BlueColor` to fill color.

Example:

```text
com.patterns.structural.bridge.solution.Client
```

### 2.3. Composite Design Pattern - Good to know

It helps you compose objects into a tree-like structure to represent a part-whole hierarchy. With the help of this design, you can interact with objects uniformly as they implement the same interface.

Part-whole hierarchy means there is an object which is a container and it contains all the parts.

Key concepts:

1. Component interface: It is an interface which contains operations implemented by leaf or composite.
2. Leaf: It is an individual object with no children.
3. Composite/container: This object contains one or more children, which can be leaf or another composite.
4. Client: It interacts with the component to perform operations.

Each part of the composite structure is able to provide its result independently. This is the key advantage.

Example:

We can create a file system using the Composite design pattern, where we can have files and directories.

Both of them can implement a common interface, `FileSystem`, which can have `getSize()`. Each file is associated with some size.

A directory contains other directories or files. So `Directory` is composite and `File` is leaf.

Using this pattern, any directory can tell us its size by delegating its call to the `getSize()` method.

The client will interact with the component operation, i.e. `getSize()`.

Example:

```text
com.patterns.structural.composite.Client
```

### 2.4. Decorator Design Pattern - Mandatory

When you want to add new behavior or responsibilities to an existing object, then you can use this.

This pattern provides a flexible alternative to subclassing when you want to extend an object's functionality.

You can decorate an object by wrapping it with another object that adds extra functionality to it without changing the original class.

Key concepts:

1. Component interface: Interface to declare core functionality. (`Coffee`)
2. Concrete component class: Class which provides basic core functionality. (`SimpleCoffee`)
3. Abstract decorator class: Abstract decorator class which implements component and contains base component instance. (`CoffeeDecorator`)
4. Concrete decorator class: Concrete decorator class which provides additional functionalities. (`MilkDecorator`, `SugarDecorator`)

This design pattern avoids multi-level inheritance. Without decorator, the coffee system would have looked like the following:

```text
SugarCoffee extending MilkCoffee extending SimpleCoffee and so on.
```

To avoid this, we should use Decorator. Now you can add any new behavior and use existing behavior without modifying it.

You can add `HoneyClass` just by extending the abstract decorator.

Example:

```text
com.patterns.structural.decorator.Client
```

Note:

Design-wise, Bridge and Decorator may look the same, but Bridge does not solve the same purpose. Shape and color are different. In Decorator, the purpose is the same, i.e. `getPrice` and `prepare`. In Decorator, there is one base and around that we decorate. Like `SimpleCoffee`, then we add sugar and milk, etc.

### 2.5. Facade Design Pattern - Mandatory

It helps in restructuring the code. If you feel that you have a complex system and you have exposed too many functions or APIs to the client, then you can add a facade layer to it.

Facade is just another layer with a simplified/minimum number of APIs/functions. Facade takes care of calling required functions/APIs/subsystems on behalf of the client.

In simple words, if you have ended up exposing a number of APIs to do a task, then Facade groups those tasks into a single or more APIs, based on requirement. The client can use this new facade API, and then facade internally will call a number of APIs.

- Facade simplifies a complex system.
- It exposes fewer, cleaner APIs to the client.
- It calls multiple subsystems on behalf of the client.
- The client should not know internal complexity.
- Facade is an orchestration layer.

Example:

```text
com.patterns.structural.facade.problem.Client
com.patterns.structural.facade.solution.Client
```

### 2.6. Flyweight Design Pattern

- It is used to save memory and improve performance by sharing as much data as possible among the same type of objects.
- It is used in a system where a large number of objects get created with some common properties.
- Object data can be divided into two types:

1. Intrinsic data: This data is common between multiple objects and is not going to change at runtime. It is the immutable part of an object.
2. Extrinsic data: This data is specific to every object and cannot be shared.

Steps to use Flyweight design pattern:

1. Find out intrinsic and extrinsic data of a class. (`Tree`)
2. Pull out intrinsic data from the class and create a new flyweight class which will store this intrinsic data. (`TreeType`)
3. Create a flyweight class with intrinsic data and add the same operation by passing extrinsic data to it: `display(positionX, positionY)`.
4. Add this newly created flyweight class instance inside the original class.
5. Now the original class method will not do the operation. Instead, it will delegate the task to the flyweight class by passing extrinsic data.
6. Create a flyweight factory. This will contain a cache. This factory will return the flyweight object from the cache, i.e. the flyweight object will get created once for each key. (`TreeTypeFactory`)
7. The client will create the original class instance by passing the flyweight object to it from the factory.

Example:

```text
com.patterns.structural.flyweight.problem.Client
com.patterns.structural.flyweight.solution.Client
```

### 2.7. Proxy Design Pattern - Mandatory

- In the Proxy design pattern, we provide an object (`UserServiceProxy`) which controls access to another object (`UserService`).
- The purpose of Proxy is to control access to an object. As part of that control, it can add cross-cutting concerns like caching, logging, security, or lazy initialization.

Difference between Proxy and Facade:

```text
Proxy controls access to a single object.
Facade is used to control multiple subsystems.
```

Example:

```text
com.patterns.structural.proxy.Client
```

## 3. Behavioral Design Patterns

It focuses on object interactions. It shows how objects should interact with each other efficiently and flexibly. It promotes loose coupling.

### 3.1. Chain of Responsibility Design Pattern - Mandatory

- It is used when we have multiple handlers which process a request sequentially. Each handler can either process the request or pass it to the next handler.
- We can implement this using one abstract handler class which will contain the abstract method `handle(request)`. It will also contain a reference to `nextHandler`. It will contain the `setHandler(handler)` method to set the next handler.
- Each handler will extend this abstract class and call the next handler if present; otherwise, it will return the result.

Example:

```text
com.patterns.behavioral.chainOfResponsibility.Client
```

### 3.2. Command Design Pattern - Good to know

- In command design, we use actions as objects rather than having them as methods and calling them directly from the client.
- This design pattern allows actions as objects to decouple the request operation. Generally, we make actions/operations as methods and call them. But in this pattern, we make actions/operations as objects and pass them to the invoker.
- Consider `Bulb` as a receiver which has two operations: turn on and turn off. Then we can make two command classes for it.
- These commands know how to perform that action.
- The client will only need to use this with remote control.

Components:

1. Receiver: The object which will be controlled by commands. (`Bulb`)
2. Command interface: It defines `execute()` and `undo()` methods. (`Command`)
3. Concrete command: It implements the `Command` interface. It contains the receiver object. (`TurnOn`, `TurnOff`)
4. Invoker: Stores the commands, executes them, and undoes them. (`RemoteControl`)
5. Client: Creates commands and executes them with the help of the invoker.

Here, instead of the client directly calling the `Bulb` methods, the client uses a command object to turn the bulb on or off. In this way, we are decoupling the client and bulb.

Example:

```text
com.patterns.behavioral.command.Bulb
```

### 3.3. Iterator Design Pattern - Good to know

- It provides a way to access elements of a collection, such as an array, tree, or list, sequentially without exposing their underlying structure.

Components:

1. Iterator interface: It provides methods to access elements sequentially.
2. Concrete iterator: It implements the iterator interface and provides a way to access elements sequentially.
3. Iterable interface: Collection which we do not want to expose will implement this interface. It returns a concrete iterator.
4. Concrete iterable: It returns an iterator.

Example:

```text
com.patterns.behavioral.iterator.Client
```

### 3.4. Mediator Design Pattern

- It introduces a mediator so that different objects can talk to each other without direct dependency, i.e. objects will not communicate directly; instead, they will use a mediator.
- It helps with loose coupling.
- Objects are not tightly coupled with each other.
- Just like airplanes communicate with each other through an air traffic control tower, which acts as a mediator.

Example:

```text
com.patterns.behavioral.mediator.ChatRoomSimulator
```

### 3.5. Observer Design Pattern - Mandatory

- Suppose there are two screens which are showing the current temperature. If the temperature is updated, these screens should also show the updated value.
- To achieve this, screens have to check the status of the weather station from time to time. But it is not a good solution. Instead, if the weather station gets updated, then it should update every screen about its change. This problem is solved by the Observer design pattern.
- In this pattern, the subject, i.e. weather station, keeps a list of observers, i.e. screens, and updates them once the temperature gets updated.
- In this pattern, when the subject/observable changes state, all its observers/listeners get notified and update automatically.

Components:

1. Subject: It is observable which is being observed by observers. It maintains a list of observers.
2. Observers: They update themselves when notified by the subject.

### 3.6. Memento Design Pattern

- It is used to capture an object's state and restore it later.
- It is used to implement features like undo/redo, snapshots, history tracking, etc.
- It is used when direct access to an object's state is not allowed due to encapsulation.
- In the notepad memento example, we are creating a new memento with the current state of notepad. This memento we are passing to caretaker. Caretaker keeps history of mementos. On undo, caretaker pops memento from history and uses it for the undo operation.

Components:

1. Originator: The object whose state needs to be saved and restored later. (`Notepad`)
2. Memento: It stores the originator's state. (`NotepadMemento`)
3. Caretaker: Keeps track of mementos but does not modify them. It can restore the originator using memento. (`NotepadCaretaker`)

Example:

```text
com.patterns.behavioral.memento.User
```

### 3.7. State Design Pattern - Good to know

- It allows an object to change its behavior based on a change in its state.
- It is used when an object's behavior depends on its current state and needs to change as its state changes.
- Basically, in this design pattern, we are delegating the object's responsibility to each state. How the object will behave on some action will be determined by its state. So each state will have all the methods as the main object.
- Consider a coffee machine. It can have three states: idle, selection, and dispense state.
- It can have three actions/buttons: `insertCoin`, `selectCoffee`, and `dispense`.
- Coffee machine behavior depends on the current state. For example, if the state is idle, you cannot directly select coffee. First, you will have to insert a coin. Once you are in selection state, again you cannot insert a coin, and so on.
- So each state will contain all these three methods: `insertCoin`, `selectCoffee`, and `dispense`, and they will provide respective implementation accordingly.

Components:

1. State interface: This will declare all possible actions of the main object.
2. Concrete state: This will implement the state interface and will provide the definition for all actions for this state.
3. Main object: This is the main object which will contain all states along with the current state. This will contain all actions and delegate calls to the current state to perform the given action.

Example:

```text
com.patterns.behavioral.state.Client
```

### 3.8. Strategy Design Pattern - Mandatory

- It is used when you have multiple algorithms/strategies that can be used interchangeably.
- It is used when you want to avoid a lot of if-else in code.
- It promotes the open/closed principle.
- It is used when you do not want to tightly couple the implementation, and when implementation will be available at runtime.
- There can be multiple payment methods: credit card, UPI, debit card, etc. Also, a new payment method can be introduced in the future. Hence, we need to design code in such a way that we can achieve this. This can be achieved using Strategy.
- Need to create one strategy interface. Implement this interface for new payment methods like credit card, debit card, etc.
- Need to create a class which will hold the strategy interface. This class will use that interface for payment. At runtime, based on the user's choice, the payment method object will be injected in that strategy interface.

Example:

```text
com.patterns.behavioral.strategy.Client
```

### 3.9. Visitor Design Pattern

- It is used when we want to keep algorithm/behavior separate from object.
- Also, when you want to perform operations on a set of objects without modifying them.
- Also, new behavior can be added without changing the object.
- In this, the individual element is responsible for calling the respective visitor method.
- If new behaviors are frequently added, then this is useful.
- If new elements are frequently added, then this is not useful, as adding a new element means changing the visitor by adding a `visit(newElement)` method. This is costly since all the existing behaviors will need to be updated. This will violate the open/closed principle.
- Suppose we have a system where we are going to work on two shapes: triangle and square. If we are sure that no new shape will be added to the system but operations on these shapes will get added, then we can use Visitor.

Components:

1. Visitor interface: Defines operations to be performed on different object types. (`ShapeVisitor`)
2. Concrete visitor: It implements visitor. (`DrawShape`)
3. Element: Interface for object. It defines the `void accept(Visitor)` method. (`Shape`)
4. Concrete element: It implements the `Element` interface. (`Triangle`, `Square`)

Example:

```text
com.patterns.behavioral.visitor.Client
```

### 3.10. Template Method Design Pattern - Mandatory

- It defines the skeleton of an algorithm in a superclass and allows subclasses to override specific steps.
- Servlet in a web server is a good example of this. We have created one abstract servlet which has a service method/template method. This method will act as a template method and determine how each request should be processed. This template method calls `doGet` and `doPost` methods depending on some conditions. This class also provides default implementation for `doGet` and `doPost`, but it is the client's responsibility to provide the correct implementation.
- In normal terms, you can say that in the Template Method design pattern, we provide one method which calls other methods based on requirement, and implementation of those other methods will be given by the client, i.e. subclass.
- It follows the open/closed principle.
- The client can add new servlets to handle `doGet` and `doPost`.

Example:

```text
com.patterns.behavioral.templateMethod.Application
```

### 3.11. Interpreter Design Pattern

- It is used to define grammar for a language and provides an interpreter to evaluate expressions in that language.
- Like SQL query parsing, compiler, etc.
