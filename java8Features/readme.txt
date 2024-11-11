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

