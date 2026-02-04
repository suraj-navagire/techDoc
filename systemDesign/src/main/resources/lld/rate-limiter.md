# Design a Rate limiter system
A rate limiter is a system that controls how many requests a user, IP address, or API key can make to an application within a specified time window.
It allows requests within the configured limit and rejects or delays excess requests to protect the system from abuse, ensure fair usage, and maintain overall system stability.

A rate limiter only cares about how many requests happen over a period of time (per second, minute, hour).
It does not care how many requests are running simultaneously.

### Why do we need Rate Limiting?
Interviewers love the “why”.

1. Prevent API abuse (DDOS, bots)
2. Protect backend resources (DB, CPU)
3. Ensure fair usage among users
4. Avoid cascading failures

### Where is Rate Limiter used?

1. API Gateway
2. Backend service
3. Login / OTP endpoints

## Step 1 : Requirement gathering
Gather following information from interviewer (While asking these requirement we should tell upfront what we are assuming)
1. The system should limit the number of requests to the application.
2. What to limit? 
   - Number of requests per user / IP / API key in a specified time.
3. Behavior on limit exceed
   - Reject further requests (fail fast).
4. The system should support different limits per key if required (Useful when used time based rate limiting. Like normal user can request 100 req/min but premium user 1k req/min).
5. Each request should be approved or rejected in O(1) time.
6. The solution must be thread-safe and support high concurrency.
7. Limits should be configurable without code changes.
8. There should be a pluggable design to allow changing the rate-limiting technique in the future.
9. Which algorithm and why?

   | Algorithm          | Description                                                                             |
   | ------------------ | --------------------------------------------------------------------------------------- |
   | **Fixed Window**   | Counts requests in fixed time windows. Simple, but burst can happen at window boundary. |
   | **Sliding Window** | Uses a moving time window to count requests, so boundary burst is avoided.              |
   | **Token Bucket**   | Uses tokens to allow requests. Supports small bursts but controls average rate.         |


### Simple example (10 requests per 1 hour)

#### 1. Fixed Window

**Time windows:**
- 10:00 – 11:00
- 11:00 – 12:00

**If:**
- 10 requests come at **10:59** → allowed
- 10 requests come at **11:01** → allowed

👉 **20 requests in few minutes → burst problem**

---

#### 2. Sliding Window

The window keeps moving.

- At **11:30**, check requests from **10:30 – 11:30**
- If requests ≥ 10 → reject
- Else → allow

👉 **Requests are spread evenly**

---

#### 3. Token Bucket

**Configuration:**
- Bucket size = **10 tokens**
- Refill rate = **10 tokens per hour**

**How it works:**
- Initially, the bucket has **10 tokens**
- When a request comes:
    - If a token is available → allow and remove **1 token**
    - If no token is available → reject
- Tokens are added **slowly over time** (not all at once)

Note : 
Time-based rate limiting focuses on fairness over time.
Concurrent rate limiting focuses on resource protection.


## Step 2 : Identify core entities (nouns)
Lets identify core entities for this system
1. RateLimiter
2. RateLimiterConfig 
3. Bucket 
4. RateLimiterType

## Step 3 : Identify responsibility of each entity
Now let's understand responsibility of each entity
1. RateLimiter : This will contain method which will accept or reject the request
2. RateLimiterConfig : This will contain configurations required for rate limiter.
3. Bucket : This will contain request counts/tokens
4. RateLimiterType : Supported rate limiter algorithms types

## Step 4 : Identify where and which design pattern we can use in this system.
Interviewer may ask this. So we should know what all design patterns we can use in this system
1. RateLimiter : This will be strategy interface. Application can decide which implementation to use.
2. RateLimiterFactory : Factory to return RateLimiter based on provided type.

## Step 5 : Define main classes with key attributes (Class diagram)
mermaid formate
~~~
classDiagram

TokenBucketRateLimiter ..|> RateLimiter
TokenBucketRateLimiter --> Bucket
TokenBucketRateLimiter --> RateLimiterConfig


class RateLimiter{
	<<interface>>
	+ isRequestAllowed(key : String) : boolean
}

class TokenBucketRateLimiter{
	- keyTokenMap : Map<String, Bucket>
	- config : RateLimiterConfig
	+ isRequestAllowed(key : String) : boolean
}

class RateLimiterFactory{
	+ getRateLimiter(type : RateLimiterType, config : RateLimiterConfig) : RateLimiter
}

class RateLimiterType{
	<<enum>>
	FIXED_WINDOW
	SLIDING_WINDOW
	TOKEN_BUCKET
}

class RateLimiterConfig{
	- capacity : double
	- refillRate : double
}

class Bucket{
	- capacity : double
	- refillRate : double
	- avaiableTokens : double
	- lastRefillTimeStamp : long
	+ consume() : boolean
	- refill() : void
}
~~~

Explain class diagram in flow :-

Users will try to access application -> all request will land on RateLimiter.isRequestAllowed() ->
TokenBucketRateLimiter.isRequestAllowed() -> Bucket will be fetched for key -> Bucket.consume() ->
return true or false based on token availability

Bucket.consume() method does the work of refilling token on every request. Lazy refilling.

## Step 6 : Implementation
com.systemdesign.lld.ratelimiter.Client

## Step 7 : API Design
Not Applicable
~~~

~~~

## Step 8 : Concurrency and Locking
Used ConcurrentHashMap to store key and Bucket. So that if same key comes concurrently even then it will be thread safe.

Same key user will get same bucket.

Bucket.consume() uses Lock so that token use will be synchronized.

## Step 9 : Final comments
