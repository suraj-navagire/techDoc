# REST Client in Microservices (Microprofile framework)

REST Client is used to invoke/call remote REST resource from a client application.

## Normal REST Resource Example

``` java
package com.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Helidon!";
    }
}
```
## REST Client Example to call REST Resource in Microprofile
``` java
<dependency>
        <groupId>io.helidon.microprofile.rest-client</groupId>
        <artifactId>helidon-microprofile-rest-client</artifactId>
        <version>3.2.0</version>
</dependency>
```

``` java
package com.example.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8080/api")
@Path("/hello")
public interface HelloClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getMessage();
}
```
``` java
package com.example.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class RestClientService {

    @Inject
    @RestClient
    private HelloClient helloClient;

    public String callService() {
        return helloClient.getMessage();
    }
}
```
``` java
package com.example.resource;

import com.example.client.RestClientService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/client")
public class ClientResource {

    @Inject
    private RestClientService restClientService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String invokeClient() {
        return restClientService.callService();
    }
}
```
Add property file : src/main/resources/META-INF/microprofile-config.properties
``` java
com.example.client.HelloClient/mp-rest/url=http://localhost:8080/api
com.example.client.HelloClient/mp-rest/scope=javax.inject.Singleton
```

## Adding Fault Tollerence in REST Client

``` java
<dependency>
    <groupId>io.helidon.microprofile.fault-tolerance</groupId>
    <artifactId>helidon-microprofile-fault-tolerance</artifactId>
    <version>3.2.0</version>
</dependency>
```
``` java
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

@ApplicationScoped
public class RestClientService {

    @Inject
    @RestClient
    private HelloClient helloClient;

    @Retry(maxRetries = 3, delay = 1000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 2000)
    public String callService() {
        return helloClient.getMessage();
    }
}
```
### Explaination of Microprofile Fault Tolerence Annotations

1. @Retry : It is part of microprofile fault tolerence. It is used to automatically retry a method or service call when it fails due to an exception.
    1. maxRetries=3 : The method will retri 3 times after failure. This means total of 4 attempts.
    2. delay=1000 : The time interval between each retry 1000 ms i.e. 1 second.
    
    Use : 
    * It is used to handle network issues.
    * Time out issues. 
    * service unavailable issues.
    
2. @CircuitBreaker : It is used to avoid excessive calls to a failing service. It works by :
    *  Monitoring success and failure rate of a method.
    * Opening the circuit when certain failure threashold is reached.
    * Blocking further calls for a specified time.

    1. requestVolumeThreshold=4 : Circuit breaker evaluates success/failure rate after 4 request. It means after 4 request for each next subsequent requests it eveluates rate using last 4 request.
    2. failureRatio=0.5 : If 50% or more request fail then circuit opens.
    3. delay=2000 : Circuit stays open till 2000 ms i.e. for 2 second before attempting to reset.
    
    Behavior :
    * Closed state : Normal operations. All requests pass through.
    * Open state : No further requests are made. Request fails immediately.
    * Half open state : After 2 second circuit allows limited number of test requests. 
        * If the test request succeed. Then circuit closes.
        * It the test request fail. Then circuit reopens.

    Use :
    * Preventing cascading failures.
    * Reduce load on unstable system.
