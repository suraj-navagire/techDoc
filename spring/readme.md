# Spring Interiew preparation

## What is Spring?
Spring is an open source framework which helps in depelopment of java application. It is known for its IOC (inversion of control), DI (Dependency injection) and AOP (aspect oriented programming) features.

## What is Dependency Injection ?
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

## What are the different types of spring bean scope ?
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

## What the different types of beans?
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

## What are the different annotations used at REST controller layer?
