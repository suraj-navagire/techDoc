# What is object-oriented programming system (OOPS).

This programming system is based on the concept of object.

Object contains data(attributes) and methods.

## What are the 4 main OOPS concept or OOPS principle

1. Encapsulation : Binding data and methods into single unit(class). It is achieved with the help of class and access
   modifiers.

   Goal : Protect the data from outside inheritance and misuse.

   Example : Using private with the help of public getter/setter to restrict access.

~~~java
class Student {
        private int age;

        public void setAge(int a) {
                this.age = a;
        }

        public int getAge() {
                return age;
        }
}
~~~

2. Inheritance : A child class can inherit data and methods from parent class. It can be achieved with extends and
   implements keywords.

   Goal : Reuse the existing data and code. Establish relationship between classes.

   Example : Dog inherits sound method.

~~~java
class Animal {
        void sound() {
                System.out.println("Animal makes a sound");
        }
}

class Dog extends Animal {
        void bark() {
                System.out.println("Dog barks");
        }
}

class Main {
        public static void main(String[] args) {
                Dog d = new Dog();
                //Here dog can use sound because of inheritance.
                d.sound();
                d.bark();
        }
}
~~~

3. Polymorphism : One method behaves differently based on object.

   Goal : Same method but different implementations.

   Two types of polymorphism :
    - Compile time (Method overloading)
    - Run time (Method overriding)

   Example :

~~~java
// Overloading
class MathUtils {
        int add(int a, int b) {
                return a + b;
        }

        int add(int a, int b, int c) {
                return a + b + c;
        }
}

// Overriding
class Animal {
        void sound() {
                System.out.println("Animal sound");
        }
}

class Dog extends Animal {
        void sound() {
                System.out.println("Dog barks");
        }
}
~~~

4. Abstraction :  Hiding complex details showing only required features.

   Goal : Simplify the interface for user.

   Example : Using Abstract class or Interface

~~~java
interface Vehicle {
        void move();
}

class Car extends Vehicle {
        void move() {
                System.out.println("Car moves on roads");
        }
}
~~~

# What is interface and its features

Interface is a collection of abstract methods and constants.

- Contains only abstract methods.
- From java 8 it can contain default/static methods.
- All methods are 'public abstract' by default.
- All variables are constants i.e. 'public static final' by default.
- No constructors allowed.
- A class can implement multiple interfaces.
- Interface can extend another interface.
- Cannot have instance block or static block.
- Interface with no method is known as 'Marker Interface'.
- Interface with single method is known as 'Functional Interface'.

# What is class and its features

Class is blueprint to create and object. It contains variables and methods.

- A class binds variables and methods into single unit.
- A class is blueprint for an object. No memory gets created until we create an object.
- Class access can be controlled with the help of access modifiers
  'private, protected, Default, public'
- Class can inherit another class.
- A class can have constructors to initialize objects when created.
- Can have method overloading
- Can have method overriding
- Class can implement multiple interfaces.
- A class can have static variable and methods.

# What is access modifiers

Access modifiers controls visibility of a class, variable, method and constructor.

| Modifier | Where it can be accessed |
|:---------|:--------------------------|
| public   | Everywhere (any class, any package) |
| private  | Only inside the same class |
| protected| Inside the same package + subclasses in other packages |
| default (no keyword) | Only inside the same package (package-private) |

# What is diamond problem

The diamond problem is an ambiguity issue that arises in object-oriented programming when a class inherits from two classes that have a common ancestor. If the common ancestor class defines a method that is overridden by both parent classes, and the child class does not override the method, it becomes unclear which version of the method the child class should inherit.
Diamond problem arises In following:

~~~java
class A {
        display() {
                print("A");
        }
}

class B extends A {
        display() {
                print("B");
        }
}

class C extends A {
        display() {
                print("C");
        }
}

class D extends B, C {

}

class Main {
        main() {
                A a = new D();
                //Here it is unclear which display method to print. i.e. B's or C's method.
                a.display();
        }
}
~~~

## Solution to diamond problem
Java does not support multiple inheritance of classes. Only for interfaces allowed.

Interface does not contain any implementation. So child will only give. That's why there is no ambiguity.

From java 8 we can add default method. because of this issue can come. we can solve this by overriding such methods in child if there is ambiguity.

~~~java
interface InterfaceA {
    default void display() {
        System.out.println("Interface A");
    }
}

interface InterfaceB {
    default void display() {
        System.out.println("Interface B");
    }
}

class MyClass implements InterfaceA, InterfaceB {
    @Override
    public void display() {
        // Resolve the ambiguity by providing an implementation
        InterfaceA.super.display(); // Call InterfaceA's default method
        InterfaceB.super.display(); // Call InterfaceB's default method
        System.out.println("MyClass");
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.display();
    }
}
~~~

## Types of inheritance
- Single inheritance : A extends B.
- Multi-level inheritance : A extends B.  B extends C
- Multiple inheritance : Only interfaces allowed. 
   interface A
   interface B
   class V implements A, B

## Static class
Static class is a nested class with static keyword. It is associated with outer class. Not with the object of outer class.
- Static class can access only static members (variables/methods) of an outer class.
- Static class object is created with the help of outer class without the object of outer class.
- Static class can have its own static fields and methods
- Where to use : Builder design pattern. Where we want to group builder with outer class.
~~~java
class Outer {
    static int outerStaticVar = 10;
    
    static class StaticNested {
        void show() {
            System.out.println("Outer static variable: " + outerStaticVar);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // Instantiating static nested class without an instance of Outer
        Outer.StaticNested nestedObj = new Outer.StaticNested();
        nestedObj.show();  // Output: Outer static variable: 10
    }
}
~~~

## What is inner class (Non-static)
Inner class is nested class which can access both static and instance variables/methods of an outer class.
To create object of inner class we need object of out class.
Where to use : Iterator inside ArrayList<>();
~~~java
class Outer {
    int outerVar = 10;  // Instance variable of outer class

    // Inner class
    class Inner {
        void show() {
            System.out.println("Outer variable from inner class: " + outerVar);  // Can access outer instance variable
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // Creating an instance of the outer class
        Outer outerObj = new Outer();

        // Creating an instance of the inner class
        Outer.Inner innerObj = outerObj.new Inner();

        // Calling a method of the inner class
        innerObj.show();  // Output: Outer variable from inner class: 10
    }
}
~~~

## What is method local inner class
It is defined inside method. We can use it inside that method only.

We can write it inside static as well as instance method. But if we are writing inside static then only static members can be accessed inside this class.
~~~java
class Outer {
    void outerMethod() {
        class LocalInner {
            void display() {
                System.out.println("Inside method-local inner class");
            }
        }

        LocalInner inner = new LocalInner();
        inner.display();  // Can only be used within the method
    }
}

public class Test {
    public static void main(String[] args) {
        Outer outerObj = new Outer();
        outerObj.outerMethod();
    }
}
~~~

## What is anonymous inner class
Anonymous inner class is a class without name. It is used when we want to provide a class which is not going to be used else where.
~~~java
interface Greeting {
    void sayHello();
}

public class Test {
    public static void main(String[] args) {
        // Anonymous inner class implementing Greeting interface
        Greeting greeting = new Greeting() {
            public void sayHello() {
                System.out.println("Hello from Anonymous Inner Class!");
            }
        };
        greeting.sayHello();  // Output: Hello from Anonymous Inner Class!
    }
}
~~~

# Method Overloading
Method overloading is when a class has multiple methods with the same name but different parameters 

(different type, number, or order of parameters).

It is a type of compile-time polymorphism.

## How to achieve method overloading
You can overload methods by changing:
- Number of parameters
- Type of parameters
- Order of parameters

Return type and Exception thrown are ignored for overloading.

## Can we overload static method
Yes. 

## Can we overload method in child
Yes.

## Can we overload main() method in Java?
Yes, But JVM will only call 'public static void main(String[] args)'.

## What happens if two overloaded methods have ambiguous calls?
If the compiler can't decide which overloaded method to call, you get a compile-time error.
~~~java
void show(int a, float b) { }
void show(float a, int b) { }

show(10, 10);  // ❌ Error: ambiguous method call
~~~

## Can constructors be overloaded?
Yes

## Tricky programs

1. Example : Java looks for the most specific match first. So it will call int, int
   Output : int-int
~~~java
class Test {
   void show(int a, int b) {
      System.out.println("int-int");
   }

   void show(long a, long b) {
      System.out.println("long-long");
   }

   public static void main(String[] args) {
      Test obj = new Test();
      obj.show(10, 20);
   }
}
~~~
2. Example : 10L and 20L are long literals. So it will call long, long
   Output : long-long
~~~java
class Test {
   void show(int a, int b) {
      System.out.println("int-int");
   }

   void show(long a, long b) {
      System.out.println("long-long");
   }

   public static void main(String[] args) {
      Test obj = new Test();
      obj.show(10L, 20L);
   }
}

~~~

3. Example : Answer : Compile-time error: ambiguous method call 
~~~java
class Test {
    void show(int a, long b) {
        System.out.println("int-long");
    }

    void show(long a, int b) {
        System.out.println("long-int");
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.show(10, 20); //Ambiguous method call.
    }
}
~~~

4. Example : Null can be injected to object and String. But String child of Object i.e. most specific one. So String will get called.
   Output : String method
~~~java
class Test {
    void display(Object obj) {
        System.out.println("Object method");
    }
    
    void display(String str) {
        System.out.println("String method");
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.display(null);
    }
}
~~~

5. Example : Since here 10 don't have int method to call which is most specific method. So it will automatically get widened to long.
   Output : long version
~~~java
class Test {

    void show(long a) {
        System.out.println("long version");
    }

    void show(Integer a) {
        System.out.println("Integer version");
    }

    void show(Long a) {
        System.out.println("Long version");
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.show(10);  // Passing an int literal
    }
}
~~~

6. Example :  Here null can be assigned to Integer and Long as they are at same level of hierarchy i.e. both extends Number.
   Output : Compile time error. : reference to show is ambiguous
~~~java
class Test {
    void show(int a) {
        System.out.println("Called int version: " + a);
    }

    void show(Integer a) {
        System.out.println("Called Integer version: " + a);
    }

    void show(Long a) {
        System.out.println("Called Long version: " + a);
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.show(null); 
    }
}
~~~

7. Example : Here it will call Integer as Integer is close to null. i.e. Integer extends Number and child is always most close.
~~~java
class Test {
    void show(int a) {
        System.out.println("Called int version: " + a);
    }

    void show(Integer a) {
        System.out.println("Called Integer version: " + a);
    }

    void show(Number a) {
        System.out.println("Called Number version: " + a);
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.show(null); 
    }
}
~~~
## Java always tries to match method calls in following order

| Concept                        | Explanation |
|---------------------------------|-------------|
| **Exact Match First**           | The compiler prefers an exact match of method signatures with the arguments passed. |
| **Widening Conversion**         | If no exact match, smaller primitive types (e.g., `int`) can be widened to larger types (e.g., `long`). |
| **Autoboxing**                  | If no primitive match, Java converts primitive types into their corresponding wrapper types (e.g., `int` ➔ `Integer`). |
| **Autoboxing is Slower than Widening** | Widening (primitive to larger primitive) is generally faster than autoboxing (primitive to wrapper class). |

# Methods Overriding
Method overriding allows a subclass to provide a specific implementation of a method that is already defined in its superclass. The overridden method in the subclass must have the same name, return type, and parameters.

## What are the rules of method overriding?
- Method must have the same name, return type, and parameters.
- Subclass method cannot be more restrictive than the superclass method.
- Only inherited methods can be overridden.
- Constructors cannot be overridden.
- Private, static, and final methods cannot be overridden.
- If overriding a method from a superclass in a different package, the overridden method in the subclass must be public or protected.

## Return type summary
| Superclass Return Type | Subclass Return Type | Allowed? | Reason |
|----------------------|----------------------|---------|--------|
| `Animal`             | `Dog`               | ✅       | Covariant |
| `String`             | `Object`            | ❌       | Not covariant |
| `int`                | `long`              | ❌       | Primitives must match |
| `void`               | `void`              | ✅       | Same return type |
| `List<Number>`       | `List<Integer>`     | ❌       | Generics are invariant |
| `long`                 | `int`                 | ❌        | Primitives must match |

## Return type example
Example 1 : Valid Example.
~~~java

class Parent {
    String getMessage() {
        return "Parent";
    }
}

class Child extends Parent {
    @Override
    String getMessage() {
        return "Child";
    }
}
~~~

Example 2 : Valid example as Child class is returning Dog which is child of Animal.
~~~java
class Animal {
		
}

class Dog extends Animal {
		
}

class Parent {
    Animal getAnimal() {
        return new Animal();
    }
}

class Child extends Parent {
    @Override
    Dog getAnimal() {
        return new Dog();
    }
}
~~~

Example 3 : Incompatible return type
~~~java
class Parent {
    String getValue() {
        return "Hello";
    }
}

class Child extends Parent {
    // ❌ Compile-time error: incompatible return type
    @Override
    Integer getValue() {
        return 42;
    }
}
~~~

Example 4 : Primitive return types: must be exactly the same
~~~java
class Parent {
    int getNumber() {
        return 10;
    }
}

class Child extends Parent {
    // ❌ Compile-time error if changed to long or float
    @Override
    int getNumber() {
        return 20;
    }
}
~~~

Example 5 : Overriding with broader return type (supertype): Not allowed
~~~java
class Parent {
    Dog getAnimal() {
        return new Dog();
    }
}

class Child extends Parent {
    // ❌ Compile-time error: Animal is broader than Dog
    @Override
    Animal getAnimal() {
        return new Dog();
    }
}
~~~

## Access Specifier Rules in Method Overriding
The overriding method cannot be more restrictive than the overridden method. It can be less restrictive, but not the other way around.

| Superclass Modifier         | Allowed Subclass Modifier(s)         |
|-----------------------------|---------------------------------------|
| `public`                    | `public` only                        |
| `protected`                 | `protected`, `public`                |
| `default` (package-private) | package-private, `protected`, `public` |
| `private`                   | Cannot be overridden (not visible)   |

# Can you override a static method?
No, static methods are bound to the class, not the object. You can hide a static method, but not override it.

~~~java
class Parent {
    static void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    static void display() {
        System.out.println("Static method in Child");
    }
}

public class Test {
   public static void main(String[] args) {
      Parent p = new Child();
	  //Method hiding. Parent method will get called as display is static.
      p.display();        // Line 1
      Child.display();    // Line 2
      Parent.display();   // Line 3
   }
}

Output :
Static method in Parent
Static method in Child
Static method in Parent

~~~

# What happens if you reduce the visibility of an overridden method?
It results in a compile-time error.

# Can you override a private method?
No. Private methods are not inherited, so overriding them is not possible. Defining a method in the subclass with the same name is method hiding, not overriding.

~~~java
class Parent {
    private void show() {
        System.out.println("Parent show()");
    }
}

class Child extends Parent {
    private void show() {
        System.out.println("Child show()");
    }

    public void callShow() {
        show();  // ✅ This is valid: calling Child's own private method
    }
}

public class Test {
    public static void main(String[] args) {
        Child c = new Child();
        c.callShow();   // ✅ Allowed
        // c.show();    // ❌ Compile-time error: show() has private access
    }
}
~~~

## Can overridden methods throw exceptions? What are the rules?
Yes, but:
- If the superclass method doesn't throw any checked exceptions, the overriding method cannot throw checked exceptions.
- If the superclass method throws a checked exception, the subclass can:
- Throw the same exception
- Throw a subclass of that exception
- Not throw any checked exception at all 
Runtime exceptions are not restricted.

## Can a subclass override a final method?
No. A final method cannot be overridden to preserve its implementation. If we try we will get compile time error.

## Can a subclass override a default method from an interface?
Yes

## What is covariant return type in overriding?
It allows the return type of overridden method to be a subtype of the original return type.

## Can we override constructor?
No

## Is it possible to call the superclass version of an overridden method?
Yes. Using 'super'
~~~java
class Parent {
    void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    void display() {
        super.display();
        System.out.println("Child display");
    }
}
~~~

## Can we call 'super' inside static method to call parents method
No. 'super' and 'this' is used to refer object. Static methods are not part of object, they are part of class. So if we want to call parents method then we should call using class name.
~~~java
class Parent {
    static void show() {
        System.out.println("Parent static show");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child static show");
    }

    static void callSuperShow() {
        super.show(); // ❌ Compile-time error
        Parent.show();//This will work
    }
}
~~~

## What is this in Java?
- 'this' is a reference to the current object. 
- It is only available inside non-static (instance) methods or constructors.

## What is super in Java?
- 'super' is a reference to the immediate parent class.
- It’s used inside a subclass to:
- Call parent class constructors
- Call parent class methods
- Access parent class fields

~~~java
class Vehicle {
    String brand = "Generic Vehicle";

    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle: " + brand);
    }
	
	void parentMethod(){
			System.out.println("Inside Parent Method");
    }
}

class Car extends Vehicle {
    String brand = "Car";

    Car(String brand) {
        super("Super-" + brand); // Call parent constructor
        this.brand = brand;
    }

    void printBrands() {
		super.parentMethod();	
        System.out.println("Child brand: " + brand);       // Car
        System.out.println("Parent brand: " + super.brand); // Super-Car
        System.out.println("Parent brand: " + this.brand); // Car
    }
}

public class Test {
    public static void main(String[] args) {
        Car c = new Car("Car");
        c.printBrands();
    }
}
~~~

## final in Java 
| Modifier Target | Meaning                                          |
|------------------|--------------------------------------------------|
| `final` variable | Value/reference can't be changed after assignment |
| `final` method   | Can't be overridden                              |
| `final` class    | Can't be extended                                |

# Primitive Data types
1. Numeric Data Types
These represent numbers and are divided into two sub-categories:
   1. Integral (Whole Numbers) Data Types
      These data types represent whole numbers (both positive and negative), including zero.
   
      | Data Type | Size     | Range                              | Default Value |
      |-----------|----------|------------------------------------|---------------|
      | `byte`    | 1 byte   | -128 to 127                        | 0             |
      | `short`   | 2 bytes  | -32,768 to 32,767                  | 0             |
      | `int`     | 4 bytes  | -2^31 to 2^31-1                    | 0             |
      | `long`    | 8 bytes  | -2^63 to 2^63-1                    | 0L            |

   3. Floating-Point (Decimal) Data Types
      These represent numbers with fractions (i.e., numbers with decimal points).
   
      | Data Type | Size     | Range                                 | Default Value |
      |-----------|----------|---------------------------------------|---------------|
      | `float`   | 4 bytes  | ±1.4E-45 to ±3.4E38                   | 0.0f          |
      | `double`  | 8 bytes  | ±4.9E-324 to ±1.8E308                 | 0.0d          |

2. Non-Numeric Data Types
   1. Character Type
      This represents a single character.
   
      | Data Type | Size     | Range                          | Default Value |
      |-----------|----------|--------------------------------|---------------|
      | `char`    | 2 bytes  | 0 to 65,535 (Unicode characters) | '\u0000'      |

   2. Boolean Type
      This represents true/false values.
   
      | Data Type | Size     | Values                       | Default Value |
      |-----------|----------|------------------------------|---------------|
      | `boolean` | 1 bit    | `true` or `false`             | false         |
   
## How many bits in 1 byte
8 bits.

## Wrapper Classes of Primitive Data Types

| Primitive Type | Wrapper Class     | Method to get primitive value|
|----------------|-------------------|------------------------------|
| `byte`         | `Byte`            |    b.byteValue();            |
| `short`        | `Short`           |    s.shortValue();           |
| `int`          | `Integer`         |    i.intValue();             |
| `long`         | `Long`            |    l.longValue();            |
| `float`        | `Float`           |    f.floatValue();           |
| `double`       | `Double`          |    d.doubleValue();          |
| `char`         | `Character`       |    c.charValue();            |
| `boolean`      | `Boolean`         |    bool.booleanValue();      |

## Ways to get wrapper class object from primitive value
~~~java
public class PrimitiveToWrapperExample {
    public static void main(String[] args) {
        
        // Primitive type
        int primitiveInt = 100;

        // 1. Autoboxing: Automatically converts primitive to wrapper
        Integer wrapperIntAuto = primitiveInt;  // Autoboxing
        System.out.println("Autoboxing: " + wrapperIntAuto);  // Output: 100

        // 2. Using valueOf() method: Converts primitive to wrapper
        Integer wrapperIntValueOf = Integer.valueOf(primitiveInt);  // Using valueOf()
        System.out.println("Using valueOf(): " + wrapperIntValueOf);  // Output: 100

        // 3. Using constructor (deprecated): Converts primitive to wrapper
        // Note: The constructor approach is deprecated, use valueOf() instead.
        Integer wrapperIntConstructor = new Integer(primitiveInt);  // Using constructor (deprecated)
        System.out.println("Using Constructor (deprecated): " + wrapperIntConstructor);  // Output: 100
    }
}

~~~

## Key Features of Wrapper Classes
1. Immutability:
Wrapper class objects are immutable, meaning their values cannot be changed once they are assigned.

2. Conversion Methods:
Wrapper classes provide methods to convert from primitive to wrapper and wrapper to primitive.

    Autoboxing & Unboxing:
   1. Autoboxing is the automatic conversion from a primitive type to a wrapper class object.
    ~~~java
   int a = 5;
   Integer obj = a;  // Autoboxing (primitive to wrapper)
   ~~~
   
    2. Unboxing is the automatic conversion from a wrapper class object to a primitive type.
   ~~~java
   Integer obj = 10;
   int b = obj;  // Unboxing (wrapper to primitive)
   ~~~
   
3. Useful Methods: parse<Type>() methods (e.g., parseInt(), parseDouble()) allow you to convert String values to primitive values.
    ~~~java
   int number = Integer.parseInt("123");  // Converts string to int
   ~~~

4. Constants: Each wrapper class has constants like MIN_VALUE, MAX_VALUE for the range of the respective primitive type.
~~~java
System.out.println(Integer.MAX_VALUE);  // Prints 2147483647
~~~

5 Nullability: Wrapper classes can be null (since they are objects), whereas primitive types cannot.
~~~java
Integer obj = null;  // Valid
int primitiveInt = null;  // ❌ Compile-time error
~~~

## How much data wrapper class object can hold
It can hold same data as its primitive type. The difference is since it is an object it will take some extra memory.

All wrapper class will take extra 24 byte memory (for 64 bit jvm)

Example 1: 
byte can hols 1 byte data.
Byte will also contain 1 byte + 24 extra memory(object overhead) = 25 byte (memory it will hold)

Example 2 :
int can hols 4 byte data.
Integer will also contain 4 byte + 24 extra memory(object overhead) = 28 byte (memory it will hold)

# Strings in java
String is a sequence of characters. It is immutable.

## Immutable Nature of Strings
Immutability means that the content of a String object cannot be changed once it's created. If you try to modify a String, a new object is created.

## String pool
Java maintains a String pool, also known as the String constant pool or String literal pool. It is part of heap memory.

~~~java
String str1 = "Hello";  // Uses string pool
String str2 = "Hello";  // Reuses the existing object from the pool

System.out.println(str1 == str2);  // true (both refer to the same object)
~~~

## intern() method of String
You can manually add strings to the pool using the intern() method, which ensures that a string with the same value is stored only once.

~~~java
String str1 = new String("Hello");
String str2 = str1.intern();
~~~

## Ways to create String objects
1. String Literal
2. Using new keyword
~~~java
String s1 = "Java";  // String literal. It stores string in String pool
String s2 = new String("Java");  // Creates a new String object outside the String pool
~~~
3. Using StringBuilder or StringBuffer
~~~java
StringBuilder sb = new StringBuilder("Java");
sb.append(" is awesome!");
String s = sb.toString();  // Converts StringBuilder back to String. It will be outside pool.
~~~

## toString() method used to create String
When we create String object using toString method by default it will not go inside pool. We have to call intern() method for that or we have to generate String object inside toString using string literal.

## String methods
1. Length and Character Access

   | **Method**                     | **Description**                                      | **Syntax**                                              | **Example**                                                    | **Output**                             |
   |---------------------------------|------------------------------------------------------|---------------------------------------------------------|---------------------------------------------------------------|----------------------------------------|
   | `length()`                      | Returns the length of the string (number of characters). | `int length()`                                          | `String str = "Hello";`<br>`int len = str.length();`          | `len` will be `5`                      |
   | `charAt(int index)`             | Returns the character at the specified index in the string. | `char charAt(int index)`                               | `String str = "Hello";`<br>`char c = str.charAt(1);`          | `c` will be `'e'`                      |
   | `substring(int start, int end)` | Returns a substring from the string, starting at `start` (inclusive) and ending at `end` (exclusive). | `String substring(int start, int end)`                  | `String str = "Hello";`<br>`String subStr = str.substring(1, 4);` | `subStr` will be `"ell"`                |

2. Searching and Matching

| **Method**                              | **Description**                                                       | **Syntax**                                                        | **Example**                                                       | **Output**                                 |
|-----------------------------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------|-------------------------------------------------------------------|--------------------------------------------|
| `contains(CharSequence sequence)`       | Checks if the string contains the specified sequence of characters.   | `boolean contains(CharSequence sequence)`                        | `String str = "Hello World";`<br>`boolean result = str.contains("World");` | `result` will be `true`                    |
| `startsWith(String prefix)`             | Checks if the string starts with the specified prefix.               | `boolean startsWith(String prefix)`                              | `String str = "Hello World";`<br>`boolean result = str.startsWith("Hello");` | `result` will be `true`                    |
| `endsWith(String suffix)`               | Checks if the string ends with the specified suffix.                 | `boolean endsWith(String suffix)`                                | `String str = "Hello World";`<br>`boolean result = str.endsWith("World");` | `result` will be `true`                    |
| `indexOf(int ch)` or `indexOf(String str)` | Finds the index of the first occurrence of a character or substring. | `int indexOf(int ch)` or `int indexOf(String str)`               | `String str = "Hello World";`<br>`int index1 = str.indexOf('o');`<br>`int index2 = str.indexOf("World");` | `index1` will be `4`, `index2` will be `6` |

3. Comparison Methods

   | **Method**                                    | **Description**                                               | **Syntax**                                                   | **Example**                                                       | **Output**                                  |
   |-----------------------------------------------|---------------------------------------------------------------|--------------------------------------------------------------|-------------------------------------------------------------------|---------------------------------------------|
   | `boolean equals(Object obj)`                  | Compares two strings for **equality** (case-sensitive).       | `boolean equals(Object obj)`                                  | `String str1 = "Hello";`<br>`String str2 = "Hello";`<br>`boolean result = str1.equals(str2);` | `result` will be `true`                       |
   | `boolean equalsIgnoreCase(String anotherString)` | Compares two strings **ignoring case** (case-insensitive).    | `boolean equalsIgnoreCase(String anotherString)`              | `String str1 = "Hello";`<br>`String str2 = "hello";`<br>`boolean result = str1.equalsIgnoreCase(str2);` | `result` will be `true`                       |
   | `int compareTo(String anotherString)`         | Compares two strings **lexicographically** (case-sensitive).   | `int compareTo(String anotherString)`                         | `String str1 = "apple";`<br>`String str2 = "banana";`<br>`int result = str1.compareTo(str2);` | `result` will be a negative number (e.g., `-1`) |
   | `int compareToIgnoreCase(String anotherString)`| Compares two strings **lexicographically**, ignoring case.    | `int compareToIgnoreCase(String anotherString)`               | `String str1 = "apple";`<br>`String str2 = "Banana";`<br>`int result = str1.compareToIgnoreCase(str2);` | `result` will be a negative number (e.g., `-1`) |

4. Modification Methods

| **Method**                                    | **Description**                                                   | **Syntax**                                                    | **Example**                                                       | **Output**                                 |
|-----------------------------------------------|-------------------------------------------------------------------|---------------------------------------------------------------|-------------------------------------------------------------------|--------------------------------------------|
| `String replace(CharSequence target, CharSequence replacement)` | Replaces all occurrences of the specified `target` sequence with the `replacement` sequence. | `String replace(CharSequence target, CharSequence replacement)` | `String str = "Hello World";`<br>`String result = str.replace("World", "Java");` | `result` will be `"Hello Java"`             |
| `String trim()`                               | Removes **leading and trailing** whitespace from the string.       | `String trim()`                                                | `String str = "   Hello World   ";`<br>`String result = str.trim();` | `result` will be `"Hello World"`            |
| `String toUpperCase()`                        | Converts all characters in the string to **uppercase**.            | `String toUpperCase()`                                         | `String str = "Hello";`<br>`String result = str.toUpperCase();`   | `result` will be `"HELLO"`                  |
| `String toLowerCase()`                        | Converts all characters in the string to **lowercase**.            | `String toLowerCase()`                                         | `String str = "HELLO";`<br>`String result = str.toLowerCase();`   | `result` will be `"hello"`                  |
| `String concat(String str)`                   | Concatenates the specified string `str` to the end of the current string. | `String concat(String str)`                                    | `String str1 = "Hello";`<br>`String str2 = " World";`<br>`String result = str1.concat(str2);` | `result` will be `"Hello World"`            |

5. Conversion Methods

| **Method**                                    | **Description**                                                   | **Syntax**                                                    | **Example**                                                       | **Output**                                 |
|-----------------------------------------------|-------------------------------------------------------------------|---------------------------------------------------------------|-------------------------------------------------------------------|--------------------------------------------|
| `String[] split(String regex)`                | Splits the string into an array of strings based on the specified delimiter (regular expression). | `String[] split(String regex)`                                 | `String str = "apple,banana,orange";`<br>`String[] result = str.split(",");` | `result` will be `["apple", "banana", "orange"]` |
| `boolean matches(String regex)`               | Checks if the string matches the given **regular expression**.    | `boolean matches(String regex)`                                | `String str = "12345";`<br>`boolean result = str.matches("\\d+");` | `result` will be `true`                    |

##  String Immutability 
~~~java
public class StringImmutabilityExample {
    public static void main(String[] args) {
        String str1 = "Java";
        str1 = str1 + " Programming";  // Creates a new String object

        System.out.println(str1);  // Output: Java Programming
    }
}
~~~

## String vs. StringBuilder vs. StringBuffer

| **Feature**          | **String**                                       | **StringBuilder**                                   | **StringBuffer**                                      |
|----------------------|--------------------------------------------------|-----------------------------------------------------|-------------------------------------------------------|
| **Mutability**       | Immutable (cannot be modified once created)     | Mutable (can be modified after creation)            | Mutable (can be modified after creation)              |
| **Thread Safety**    | Not thread-safe                                 | Not thread-safe                                     | Thread-safe (synchronized methods)                    |
| **Performance**      | Slower for frequent modifications due to immutability | Faster for string manipulation (non-synchronized)   | Slower than `StringBuilder` due to synchronization    |
| **Use Case**         | Best for short-lived, rarely modified strings   | Best for single-threaded environments where frequent string manipulation is needed | Best for multi-threaded environments where thread safety is important |
| **Internal Storage** | Uses a **char[]** array, creates new objects with every modification | Uses a **char[]** array, modifies the array directly | Uses a **char[]** array, modifies the array directly  |
| **Memory Consumption** | Higher memory usage due to immutability and frequent new object creation | More efficient memory usage as it modifies the same object | More efficient memory usage than `String`, but with synchronization overhead |
| **Common Methods**   | `length()`, `charAt()`, `substring()`, `concat()`, `replace()` | `append()`, `insert()`, `delete()`, `reverse()`, `toString()` | `append()`, `insert()`, `delete()`, `reverse()`, `toString()` |

## StringBuilder Example

~~~java
public class StringBuilderExample {
    public static void main(String[] args) {
        // Creating a StringBuilder instance
        StringBuilder sb = new StringBuilder("Hello");

        // Using append() to add more text
        sb.append(" World");
        System.out.println("After append: " + sb.toString());  // Output: "Hello World"

        // Using insert() to add text at a specific position
        sb.insert(5, " Beautiful");
        System.out.println("After insert: " + sb.toString());  // Output: "Hello Beautiful World"

        // Using delete() to remove text between two indices
        sb.delete(5, 15);
        System.out.println("After delete: " + sb.toString());  // Output: "Hello World"

        // Using deleteCharAt() to remove a character at a specific position
        sb.deleteCharAt(5); // Remove the space at index 5
        System.out.println("After deleteCharAt: " + sb.toString());  // Output: "HelloWorld"

        // Using reverse() to reverse the entire string
        sb.reverse();
        System.out.println("After reverse: " + sb.toString());  // Output: "dlroWolleH"

        // Using replace() to replace characters between specific indices
        sb.replace(0, 5, "Hello");
        System.out.println("After replace: " + sb.toString());  // Output: "HelloolleH"

        // Using setCharAt() to change a specific character at an index
        sb.setCharAt(5, ' ');
        System.out.println("After setCharAt: " + sb.toString());  // Output: "Hello olleH"

        // Using setLength() to truncate or extend the StringBuilder
        sb.setLength(5);
        System.out.println("After setLength: " + sb.toString());  // Output: "Hello"

        // Using capacity() to check the current capacity of the StringBuilder
        System.out.println("Current capacity: " + sb.capacity());  // Output: 16 (default capacity)

        // Using ensureCapacity() it ensures atleast 50 size is present after that it can increase if needed.
        sb.ensureCapacity(50);
        System.out.println("New capacity after ensureCapacity: " + sb.capacity());  // Output: 50

        // Using charAt() to get the character at a specific index
        char ch = sb.charAt(0);
        System.out.println("Character at index 0: " + ch);  // Output: 'H'
    }
}
~~~

## Performance Considerations for String concatenation.
- String concatenation using + in a loop can be inefficient because each concatenation creates a new String object. It's better to use StringBuilder or StringBuffer in such cases.
- Since String is immutable, repeated string manipulation can lead to excessive memory usage. StringBuilder or StringBuffer can help avoid creating multiple intermediate String objects.

## The Numeric/Unicode value for the lowercase letter 'A' and 'a'
It is 65 and 97 respectively
~~~java
public class CharValueExample {
    public static void main(String[] args) {
        char charCap = 'A';
        int valCap = charCap; // Implicit conversion from char to int
        System.out.println("The Unicode value of 'A' is: " + valCap);  // Output: 65
        
        char ch = 'a';
        int value = ch;  // Implicit conversion from char to int
        System.out.println("The Unicode value of 'a' is: " + value);  // Output: 97
    }
}
~~~


# Comparable Interface
The Comparable interface is used to define the natural order of objects. It provides a method called compareTo() that is used to compare the current object with another object of the same class.

Purpose: When you want to sort objects of a class in a natural order (e.g., alphabetically for strings, numerically for integers).

Method: int compareTo(T o) method:

Returns:
- Negative if the current object is less than the specified object.
- Zero if they are equal.
- Positive if the current object is greater than the specified object.
~~~java
import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return this.age - other.age;  // Sorting by age in ascending order
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 20));

        Collections.sort(people);  // Uses compareTo method of Person
        for (Person p : people) {
            System.out.println(p.name + " - " + p.age);
        }
    }
}
~~~
~~~
Bob - 20
John - 25
Alice - 30
~~~

## Comparator Interface
The Comparator interface is used when you want to define a custom order for objects, or when the class does not implement Comparable. It provides a method called compare() to compare two objects.

Purpose : When you want to define a custom sorting order, or if the class being sorted doesn't implement Comparable.

Method: int compare(T o1, T o2) method:

Returns:
- Negative if o1 is less than o2.
- Zero if they are equal.
- Positive if o1 is greater than o2.

~~~java
import java.util.*;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.age - p2.age;  // Sorting by age in ascending order
    }
}

class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.name.compareTo(p2.name);  // Sorting by name alphabetically
    }
}

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 20));

        // Sorting by age using AgeComparator
        Collections.sort(people, new AgeComparator());
        System.out.println("Sorted by Age:");
        for (Person p : people) {
            System.out.println(p.name + " - " + p.age);
        }

        // Sorting by name using NameComparator
        Collections.sort(people, new NameComparator());
        System.out.println("\nSorted by Name:");
        for (Person p : people) {
            System.out.println(p.name + " - " + p.age);
        }
    }
}
~~~
~~~
Sorted by Age:
Bob - 20
John - 25
Alice - 30

Sorted by Name:
Alice - 30
Bob - 20
John - 25
~~~

