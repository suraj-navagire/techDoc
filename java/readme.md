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

## 