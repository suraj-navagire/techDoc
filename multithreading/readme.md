# Multithreading Interview Preparation

Thread is the smallest lightweight process.

---

## Life Cycle

1. **New state** – When we create a thread.  
   `Thread t = new Thread();`
2. **Runnable and Running** – When we start a thread, it enters the runnable state; when selected by the JVM scheduler, it may actually run (running state).  
   `t.start();`
3. **Blocked state** – When thread tries to acquire a lock but it's not available.  
   `synchronized(Object) {}`
4. **Waiting state** – When thread is waiting for notification.  
   `object.wait();`
5. **Timed waiting state** – When thread is waiting for a definite amount of time.  
   `object.wait(1000);`
6. **Terminated state** – When thread completes its task or if any exception occurs.

---

## 2 Ways to Create Threads

### 1. Extends Thread class

- **Starting thread:**  
  ```java
  NewThreadClass t1 = new NewThreadClass();
  t1.start();
  ```
- **Example:**  
  `org.example.ThreadCreationExample1`

### 2. Implement Runnable interface

- **Starting thread:**  
  ```java
  Thread t = new Thread(new NewRunnableThread());
  t.start();
  ```
- **Example:**  
  `org.example.ThreadCreationExample2`

---

## Thread vs Runnable

- **Thread** represents the actual thread which runs and it has all the methods supported by threads.
- **Runnable**, on the other hand, represents the task.
- When we use threads, we cannot differentiate tasks; we have to do tasks within thread only. This might affect quality of code and usability. With runnable, we can separate out task from Thread.
- Resource sharing between multiple threads becomes easier with runnable.
- If you want to enhance Thread or override any property or method, that time we should think of using extending threads, otherwise use runnable.

---

## Join

- `thread.join()` method will tell the currently executing thread to wait for this thread (thread on which join method is called) to finish its job.
- **Example:**  
  `org.example.JoinExample`

---

## Volatile Keyword

- **Volatile** means read from main memory, not from CPU registers, since in multicore systems each CPU has its own register to store data.

  Sometimes, on some systems, it might happen that some shared data or data which is going to be updated by another thread is cached by a running thread and never checked for updated value. Or code might be interpreted in such a way that the system might feel that data which is shared or getting updated by another thread is useless and because of this, system might never check its updated value. In such case, we should use the `volatile` keyword on those shared data. It can happen on some systems; it's not always true for all systems.

- **Example:**  
  `org.example.VolatileExample`

- [Read more](https://jenkov.com/tutorials/java-concurrency/volatile.html)

---

## synchronized Keyword in Java

- Every object in Java has an intrinsic lock. At a time, only one thread can acquire this lock. When we mark any method synchronized, the thread has to acquire this intrinsic lock in order to do any processing on that object.
- When we use the `synchronized` keyword on a method, the Java Memory Model guarantees that changes to shared variables made by one thread are visible to other threads accessing those variables inside other synchronized methods or blocks on the same lock. Thus, you do not need to mark those variables as `volatile` if all access is properly synchronized.

---

### Instance Methods
If a class has two synchronized instance methods and two threads try to access them on the same object, only one thread at a time can execute either method—the object’s intrinsic lock ensures exclusive access. If both threads use different object instances, then they can each acquire the lock on their respective objects independently, and both synchronized methods can run concurrently (one per object).

```java
void synchronized method1() {
    // do stuff
}

void synchronized method2() {
    // do stuff
}
```

---

### Solution (Separate Object Locks)

To solve this, we can use synchronized lock on separate objects:

```java
Object o1 = new Object();
Object o2 = new Object();

void method1() {
    synchronized(o1) {
        // do stuff
    }
}

void method2() {
    synchronized(o2) {
        // do stuff
    }
}
```

---

### Static Methods

The intrinsic lock used is associated with the Class object itself (e.g., `ClassName.class`). This means that only one thread at a time can execute any static synchronized method for that class, regardless of how many different object instances exist. Even if two threads call static synchronized methods using different instances of the same class, they will still compete for the same lock and cannot execute concurrently.

---

### Instance and Static Method Behavior (1 Object, 2 Threads)

If one thread tries to access a synchronized instance method and another thread tries to access a synchronized static method (even on the same object), both threads can execute their respective methods concurrently without blocking each other.

- A synchronized instance method locks on the specific object instance (`this`).
- A synchronized static method locks on the Class object (`ClassName.class`).

These are completely separate locks. So, acquiring a lock on the instance does not interfere with acquiring a lock on the class object, and vice versa.

---

**Example:**  
Follow example present in package  
`src/main/java/org/example/synchronizedExample`

- synchronized method example present in following class: Example1, Example2, SynchronizedMethod
- synchronized block (object lock) example in: Example3, Example4, Example5, Example6, Example7
- synchronized block (class level lock) example in: Example8, Example9

---

## Executor Framework

- It is created to use existing threads rather than creating new threads every time. It works on concept of thread pooling. It uses existing thread from thread pool to execute task.
- **Executor** and **ExecutorService** are interfaces.
- **Executors** is a class which provides factory methods to create thread pools.

**4 Types of ExecutorService are available:**
- SingleThreadPool: Thread pool with single thread.
- FixedThreadPool: Thread pool with fixed number of threads.
- CachedThreadPool: Creates thread pool which creates new thread if required, else will use an existing one. If no thread is available in thread pool then it creates new one.
- ScheduledExecutor: We can use this when task needs to be run at a regular interval or if we want to delay a task.

**Example:**  
`src/main/java/org/example/threadpool`

**Future object:**  
We can access result of a task submitted to executor using `Future<>` object. If we want result then we should implement `Callable` interface instead of `Runnable` for task.

**Example:**  
`org.example.threadpool.FuturesExample`

**shutdown method in ExecutorService:**  
If we don't call it, then executors keeps running in background. Once we call this, executor will not accept new tasks and shuts down.

**awaitTermination:**  
Program waits at this for given amount of time or until all tasks get completed. When time gets completed, program resumes from this point but in background executors stays alive. So to kill or shut down executor threads, we need to call shutdown method.

**Example:**  
`org.example.threadpool.AwaitTerminationExample`

---

## CountDownLatch

- It helps if any thread wants to wait for other n number of threads to complete their tasks. When we create countDown latch, we provide number, then we pass this latch object to all those threads on which we are dependent. Then once thread completes their tasks, it decrements count by 1. Like this, all threads decrement latch by 1. When latch reaches to zero, thread which is waiting resumes and starts running.

**Example:**  
`org.example.CountDownLatchExample`

---

## Producer Consumer Problem

Producer consumer problem is best solved by using blocking queue.

**Wait and Notify:**  
When we call `wait` method on an object on which we want to wait to notify it, then current thread goes to wait state.
Then another thread which is waiting for intrinsic lock of that object gets lock and enters synchronized block.
When this thread calls `notify` method on object whose intrinsic lock is getting used, then other thread which is in wait state gets notification and starts running once that other thread exits synchronized block.
When we use wait notify, we are actually using resources efficiently; otherwise, we can achieve same using synchronization also but that time we will end up tracking some flag in loop which will use cpu resource unnecessarily.

**Example:**  
`org.example.producerconsumer.ProducerConsumerUsingQueue`

---

## Re-entrant Lock

It's used when we don't want to use synchronized method or block where we try to get intrinsic lock of provided object, but still we want to achieve synchronization.

- **lock:** When we call this method only one thread can get lock, other threads go in waiting state.
- **unlock:** When we call this method, current thread releases lock and other threads which are waiting for lock, one of them gets lock.
- **await:** Used like `wait` method.
- **signal:** Used like `notify` method.
- **tryLock:** Same as lock method, only difference is if acquires lock then returns true else false.

**Example:**  
`org/example/locks`

---

## DeadLock

Deadlock occurs when one Thread has acquired lock on one resource and is waiting for lock on another resource, but at the same time another thread has acquired lock on the resource for which the first thread is waiting, and this second thread also wants the lock on the resource whose lock is taken by the first thread.
A deadlock generally requires four conditions: mutual exclusion, hold and wait, no preemption, and circular wait.

---

## Semaphore

Semaphore is used to acquire permit. The number provided to semaphore, that many number of threads can acquire permit and proceed further. If there is no available permit, then threads will wait.

- **acquire:** Used to acquire permit. If permit not available, then thread will wait.
- **release:** Used to release permit. Then available permit count increases.

- **availablePermits():** Used to check available permit count.

**Example:**  
`org/example/semaphore`

---

## Interrupt

If one thread calls the `interrupt()` method on another thread and that thread is currently sleeping, waiting, or otherwise blocked on a method that responds to interrupts, it will immediately throw an `InterruptedException`. If the thread is running or not blocked in such a method, the interrupt flag is set and can be checked later using `Thread.interrupted()` or `isInterrupted()`.

**Example:**  
`org.example.InterruptExample`
