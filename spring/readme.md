# Spring Interiew preparation

## What is Spring?
Spring is an open source framework which helps in depelopment of java application. It is known for its IOC (inversion of control), DI (Dependency injection) and AOP (aspect oriented programming) features.

## What is Inversion of control (IOC)?
In this object creation and its lifecycle is managed by container and not by the application code.

## What is Dependency Injection (DI)?
Dependency Injection is a design pattern in which spring container creates and injects object into the bean object. It can be constructor injection, setter injection, field injection using @Autowired annotation

- Constructor injection is usually preferred for mandatory dependencies.
- Setter injection is often used for optional or changeable dependencies.
- Field injection is optional injection.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private Engine engine;             // Constructor injection
    private Transmission transmission; // Setter injection

    @Autowired
    private Wheels wheels;            // Field injection

    // Constructor injection
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    // Setter injection
    @Autowired
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void startCar() {
        engine.start();
        transmission.shift();
        wheels.rotate();
        System.out.println("Car started successfully!");
    }
}
```
```java
import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void start() {
        System.out.println("Engine started.");
    }
}

@Component
public class Transmission {
    public void shift() {
        System.out.println("Transmission shifted.");
    }
}

@Component
public class Wheels {
    public void rotate() {
        System.out.println("Wheels are rotating.");
    }
}
```

## What are the different types of bean scope in spring ?
- Singleton : One instance per spring container (Default)
- Prototype : New instance each time its requested.
- Request : One instance per http request (Web context only)
- session : One instance per http session
- application : One instance is created for an entire web application 
- websocket : One instance per websocket.

```java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Scope;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Singleton instance created");
    }
}

@Component
@Scope("prototype")
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("Prototype instance created");
    }
}

@Component
@Scope("request")
public class RequestScopedBean {
    public RequestScopedBean() {
        System.out.println("Request instance created");
    }
}

@Component
@Scope("session")
public class SessionScopedBean {
    public SessionScopedBean() {
        System.out.println("Session instance created");
    }
}

@Component
@Scope("application")
public class ApplicationScopedBean {
    public ApplicationScopedBean() {
        System.out.println("Application instance created");
    }
}

@Component
@Scope("websocket")
public class WebSocketScopedBean {
    public WebSocketScopedBean() {
        System.out.println("WebSocket instance created");
    }
}
```

## What are the different types of beans in spring?
| Annotation     | Purpose                                    | Layer of Application   |
|----------------|--------------------------------------------|-------------------------|
| `@Component`   | General-purpose Spring bean. This is the base for Service, Repository and Controller beans                | Any layer               |
| `@Service`     | Specilized form of component. Marks class as service bean (service layer contains business logic)       | Service layer           |
| `@Repository`  | Specilized form of component. Marks class as DAO beans (DAO). Automatically converts database exceptions into DataccessException.              | Data access layer (DAO) |
| `@Controller`  | Specilized form of component. Marks class as web controllers (Spring MVC)         | Web controller layer    |
| `@RestController` | Specilized form of controller. Marks class as REST controllers (API endpoints)  | Web API layer           |
| `@Configuration` | Marks class as configuration class which contains bean definitions. | Configuration class |
| `@Autowired`   | Dependency injection                       | Anywhere in the app     |
| `@Value`       | Inject property values                     | Anywhere in the app     |

## What are the different annotations used at REST controller layer in Spring?

| Annotation         | Description |
|--------------------|-------------|
| `@RestController`  | Marks the class as a REST controller (`@Controller` + `@ResponseBody`) |
| `@RequestMapping`  | Maps HTTP requests to controller methods (can be used at class or method level) |
| `@GetMapping`      | Shortcut for `@RequestMapping(method = RequestMethod.GET)` |
| `@PostMapping`     | Shortcut for `@RequestMapping(method = RequestMethod.POST)` |
| `@PutMapping`      | Shortcut for `@RequestMapping(method = RequestMethod.PUT)` |
| `@DeleteMapping`   | Shortcut for `@RequestMapping(method = RequestMethod.DELETE)` |
| `@PatchMapping`    | Shortcut for `@RequestMapping(method = RequestMethod.PATCH)` |
| `@RequestBody`     | Binds the HTTP request body to a method parameter object |
| `@ResponseBody`    | Sends the method return value directly in the HTTP response (usually as JSON) |
| `@PathVariable`    | Binds a path parameter to a method parameter |
| `@RequestParam`    | Binds a query parameter to a method parameter|
| `@RequestHeader`   | Binds a value from the request header to a method parameter |
| `@ResponseStatus`  | Sets the HTTP status code for the response |
| `@ExceptionHandler`| Handles exceptions at the controller level |
| `@CrossOrigin`     | Enables Cross-Origin Resource Sharing (CORS) |
| `@Valid` / `@Validated` | Triggers validation on incoming request body or params |

```java
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allow requests from any domain
//@CrossOrigin(origins = "http://example.com") allows requests only from http://example.com"
public class UserController {

    @Autowired
    private UserService userService;

    // GET: /api/users?id=1
    @GetMapping
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // GET: /api/users/5
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    // POST: /api/users
    //@ResponseBody Here we dont need this annotation as we are using @RestController annotation. IT is the combination of @controller and @ResponseBody annotation.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    // PUT: /api/users/5
    //@valid annotation will be used to check all conditions present inside User class. It might be @NotNull etc.
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.update(id, updatedUser));
    }

    // DELETE: /api/users/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    // When any controller methods throws this 'UserNotFoundException' exception for example from service then this method will catch that and it will excute following code.
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```
```java
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

//As we are using @RequestBody annotation on controller we dont need it here
public class User {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    // Getters and Setters
}
```
## Spring bean configuration types?
- XML based approach
- Java based approach
- Hybrid approach

### XML approach
This is the old approach to configure beans. In this case beans are defined in beans.xml or applicationContext.xml file.

Follwoing is the example of beans.xml where we are defining signle-single beans.
```java
package com.example;

public class UserRepository {

    public UserRepository() {
        System.out.println("UserRepository instance created!");
    }

    public void save() {
        System.out.println("UserRepository save method called.");
    }
}


package com.example;

public class UserService {

    // The variable name here does not have to match the bean ID
    private UserRepository userRepository;

    // Setter injection for UserRepository
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void process() {
        System.out.println("UserService process method called.");
        userRepository.save();  // Calls save method of the singleton UserRepository
    }
}
```
```java
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Defining the userRepository bean with singleton scope -->
    <bean id="userRepository" class="com.example.UserRepository" scope="singleton" />

    <!-- Defining the userService bean with prototype scope -->
    <bean id="userService" class="com.example.UserService" scope="prototype">
        <property name="userRepository" ref="userRepository" />
    </bean>

</beans>
```
We should avoid defining beans one by one in beans.xml file. It will make the file bulky. Instead we should use annotations like @Controller, @Service etc. on individual classes and give the package path to beans.xml to load all these beans.

We can use 'component-scan' and provide package name to it. Then it will scan all the beans from that path.

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc">

    <!-- Enable component scanning to detect annotations like @RestController and @Service -->
    <context:component-scan base-package="com.example.demo" />

</beans>
```

### Java based approach
We can use java class annoted with @Configuration annotation.

Follwoing is the example where we are defining signle-single beans.

```java
package com.example.demo.service;

public class UserService {

    public String getUserName(Long id) {
        return "User " + id;
    }
}


package com.example.demo.controller;

import com.example.demo.service.UserService;

public class UserController {

    private final UserService userService;

    // Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String getUser(Long id) {
        return userService.getUserName(id);
    }
}
```
```java
package com.example.demo.config;

import com.example.demo.controller.UserController;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // This marks the class as a configuration class
public class AppConfig {

    // Defining the UserService bean manually
    @Bean
    public UserService userService() {
        return new UserService();  // Instantiate the UserService bean
    }

    // Defining the UserController bean manually
    @Bean
    public UserController userController() {
        return new UserController(userService());  // Inject UserService into the UserController
    }
}
```

We should avoid defining beans one by one in configuration class. It will make the class bulky. Instead we should use annotations like @Controller, @Service etc. on individual classes and give the package path to configuration class to load all these beans.

We can use 'component-scan' and provide package name to it. Then it will scan all the beans from that path.
```java
package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // Marks this as a configuration class
@ComponentScan(basePackages = "com.example.demo")  // Scans the given package for Spring components
public class AppConfig {
    // Spring will automatically register the beans defined in the specified package
}
```
## What is ApplicationContext in spring?
It is a container which creates and manages bean lifecycle. Provides methods to retrieve beans.

## What are the widely used Application context implementations?
- ClassPathXmlApplicationContext : Used for xml based configuration
- AnnotationConfigApplicationContext : Used for annotation based configuration.
```java
package com.example;

import com.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;

public class App {
    public static void main(String[] args) {
        // Load XML-based context
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("beans.xml");

        // Load Java-based context
        ApplicationContext javaContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the UserRepository bean from the XML context
        UserRepository userRepository = (UserRepository) xmlContext.getBean("userRepository");

        // Retrieve the UserService bean from the Java context
        UserService userService = javaContext.getBean(UserService.class);

        // Set the UserRepository in UserService using setter injection
        userService.setUserRepository(userRepository);

        // Use the service to create a user
        userService.createUser("John Doe");

        // Close the contexts
        ((ClassPathXmlApplicationContext) xmlContext).close();
        ((AnnotationConfigApplicationContext) javaContext).close();
    }
}
```

## What is Aspect Oriented Programming (AOP)?
Aspect Oriented Programming allows us to seperate cross-cutting conserns from main business logic.

Cross-cutting conserns are the code which affect multiple part of application code.

So if we dont use AOP then we will have to write same code in multiple locations like logging code, security code etc along with business logic.

With the help of AOP we can seperate out these cross cutting code into single place.

Common examples of cross-cutting concerns:
- Logging
- Security / Authentication / Authorization
- Error handling
- Performance monitoring / Metrics
- Transaction management
- Caching
- Validation

### Key concepts of AOP
- Aspect : Aspect is class which contains cross-cutting conserns like logging, error handling etc.
    - We can use @Order(1) to set the order in case multiple aspects are present.
- Advice : Advice is a method which gets executed at a specific join point. This method contains cross cutting code. Advice is a part of aspect class.

| **Advice Type**      | **When It Runs**                                       |
|----------------------|--------------------------------------------------------|
| `@Before`            | Before method execution                                |
| `@After`             | After method finishes (whether it returns or throws)   |
| `@AfterReturning`    | After method returns successfully                      |
| `@AfterThrowing`     | If the method throws an exception                      |
| `@Around`            | Around the method execution (before + after + control) |

- Join Point : Join point is a specific point during execution of a program, like method call or exception, where aspect's advice can be applied.
```java
@Aspect
@Component
public class JoinPointDemoAspect {

    @Before("execution(* com.example.service.MyService.*(..))")
    //The JoinPoint object is a snapshot of the execution context at the point where the advised method is invoked
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("🔹 @Before: Method = " + joinPoint.getSignature().getName());
        System.out.println("🔹 Args = " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.example.service.MyService.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("🔹 @After: Completed method = " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
        pointcut = "execution(* com.example.service.MyService.*(..))",
        returning = "result"
    )
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        System.out.println("🔹 @AfterReturning: Method = " + joinPoint.getSignature().getName());
        System.out.println("🔹 Returned = " + result);
    }

    @AfterThrowing(
        pointcut = "execution(* com.example.service.MyService.*(..))",
        throwing = "ex"
    )
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("🔹 @AfterThrowing: Method = " + joinPoint.getSignature().getName());
        System.out.println("🔹 Exception = " + ex.getMessage());
    }
}
```
```java
//ProceedingJoinPoint only meant for Around advice.
@Around("execution(* com.example.service.MyService.sayHello(..))")
public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("🌀 @Around: Method = " + joinPoint.getSignature().getName());
    Object[] args = joinPoint.getArgs();
    // Optionally modify args here
    Object result = joinPoint.proceed(args);
    System.out.println("🌀 @Around: Result = " + result);
    return result;
}

```
- Pointcut : Pointcut is a rule to define which joinpoint to intercept.
    - execution pointcut 
    - @annotation pointcut
```
Structure of execution pointcut expression :

execution(access-modifier-pattern? return-type-pattern classNameOrInterface-name-pattern? method-name-pattern(param-pattern) throws-pattern?)

option1 : 
//Most specific expresion to intercept exact same method.
execution(public String com.example.aopdemo.service.MyService.sayHello(String) throws java.lang.Exception)

option2:
//As access modifier is optional we will only add only one star before class name for return type.
execution(* com.example.aopdemo.service.MyService.sayHello(..) throws *)

option3:
//Here we are providing only mandatory regex i.e. only for ReturnType, MethodName and MethodParameter. This expresssion will intercept all the methods in application.
execution(* *(..))

option4:
//This expression will intercept all methods in service package. This is widely used and realistic expression.
execution(* com.example.aopdemo.service.*(..))
```

Example of annotation pointcut :
```java

//Creating custom annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggingAspect {
}


@Service
public class MyService {

    @LoggingAspect
    public void doSomething() {
        System.out.println("Doing something...");
    }
}


@Aspect
@Component
public class MyAspect {

    @Before("@annotation(MyCustomAspect)")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }
}
```

- Weaving : Weaving is the process of applying aspects to application code either during compile time or run time .

### Example of AOP :
```java
package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    //This is the joinpoint. advices will be applied before, after etc as per defined in aspect class.
    public String sayHello(String name) {
        System.out.println("Inside sayHello()");
        if (name.equals("error")) {
            throw new RuntimeException("Simulated error");
        }
        return "Hello, " + name;
    }
}
```
```java
package com.example.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//LoggingAspect is a Aspect
//In Spring Aspects are managed by container thats why need to add @component otherwise spring will not recognise
@Aspect
@Component
@Order(1)
public class LoggingAspect {

    //beforeAdvice is a advice method because it is annoted with @Before 
    //Following is the pointcut expression : execution(* com.example.aopdemo.service.MyService.sayHello(..))
    @Before("execution(* com.example.aopdemo.service.MyService.sayHello(..))")
    public void beforeAdvice() {
        System.out.println("@Before: Method is about to run");
    }

    @After("execution(* com.example.aopdemo.service.MyService.sayHello(..))")
    public void afterAdvice() {
        System.out.println("@After: Method has finished (success or exception)");
    }

    //returning = "result" this name should match with method afterReturningAdvice parameter name.
    @AfterReturning(pointcut = "execution(* com.example.aopdemo.service.MyService.sayHello(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("@AfterReturning: Method returned – " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.aopdemo.service.MyService.sayHello(..))", throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        System.out.println("@AfterThrowing: Exception thrown – " + ex.getMessage());
    }

    //In this we have to call proceed method to execute method.
    //Around advice gets ProceedingJoinPoint method argument by default.
    @Around("execution(* com.example.aopdemo.service.MyService.sayHello(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //Do some processing before method execution
        System.out.println("@Around: Before method");

        // Proceed with method execution
        Object result = joinPoint.proceed();

        //Do some processing after method execution
        System.out.println("@Around: After method");
        return result;
    }
}
```

