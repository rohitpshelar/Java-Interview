# Java-Interview
Java Interview Question with Answers and Solution with Code

<details>
<summary>1. Factory design pattern in Java</summary>


> Ref : [Facrory_Design_pattern.java](core/src/main/java/com/rs/Facrory_Design_pattern.java)

</details>

<details>
<summary>2.  Hashmap vs concurrent hash map</summary>

 
> HashMap: Not thread-safe, fast for single-threaded apps
> 
> ConcurrentHashMap: Thread-safe, better for multi-threaded environments
> 
> Key Differences:
> | Feature | HashMap | ConcurrentHashMap |
> |---------|---------|-------------------|
> | Thread Safety | ❌ Not thread-safe | ✅ Thread-safe |
> | Null Support | ✅ Allows null keys/values | ❌ No null keys/values |
> | Performance | Fast single-threaded | Optimized multi-threaded |
> | Locking Mechanism | No internal locking | Bucket-level locking |
> | Iterator Behavior | Fail-fast | Weakly consistent |
> | Concurrent Access | Data corruption risk | Safe concurrent access |
> | Memory Consistency | No guarantees | Happens-before guarantees |
> | Use Case | Single-threaded apps | Multi-threaded apps |
> | Synchronization | Requires external sync | Built-in synchronization |
> | Java Version | Since 1.2 | Since 1.5 |
> | Internal Structure | Array + LinkedList/Tree | Segments/Buckets |
> 
> When to Use:
> - HashMap: Single-threaded applications
> - ConcurrentHashMap: Multi-threaded applications with concurrent access

</details>

<details>
<summary>3. Can we Do Multiple sorting using comparable ? >> No - Only with Comparator</summary>
 
```java
  // Sort by Name (ascending), then by Grade (descending)
  System.out.println("\nSorted by Name (ASC) then Grade (DESC):");
  students.sort(Comparator
      .comparing(Student::getName)
      .thenComparing(Comparator.comparing(Student::getGrade).reversed())
  );
  students.forEach(System.out::println);
  
  // Sort by Age (ascending), then by Name (ascending)
  System.out.println("\nSorted by Age (ASC) then Name (ASC):");
  students.sort(Comparator
      .comparing(Student::getAge)
      .thenComparing(Student::getName)
  );
  students.forEach(System.out::println);
  ```
  </details>

<details>
<summary>4. How HashSet Works in Java</summary>

> **HashSet** is a collection that implements the Set interface and uses a **HashMap** internally to store elements. It doesn't allow duplicate elements and provides constant-time performance for basic operations.
>
> ### 2. **Add Operation Flow**
> | Step | Description |
> |------|-------------|
> | 1 | Calculate hashCode() of object |
> | 2 | Find bucket using hash & (n-1) |
> | 3 | Check if bucket is empty |
> | 4 | If not empty, use equals() to compare |
> | 5 | Add if unique, reject if duplicate |
>
> ## Key Characteristics
>
> | Feature | HashSet |
> |---------|---------|
> | Duplicates | ❌ Not allowed |
> | Null Elements | ✅ Allows one null |
> | Order | ❌ No guaranteed order |
> | Thread Safety | ❌ Not thread-safe |
> | Performance | O(1) average case |
>
> ## Complete Example
> 
> ```java
> import java.util.HashSet;
> 
> public class HashSetExample {
>     public static void main(String[] args) {
>         // Create HashSet
>         HashSet<String> fruits = new HashSet<>();
>         
>         // Add elements
>         fruits.add("Apple");
>         fruits.add("Banana");
>         fruits.add("Orange");
>         fruits.add("Apple"); // Duplicate - will be ignored
>         
>         // Display
>         System.out.println("HashSet: " + fruits);
>         
>         // Check existence
>         System.out.println("Contains Apple: " + fruits.contains("Apple"));
>         
>         // Remove element
>         fruits.remove("Banana");
>         System.out.println("After removal: " + fruits);
>         
>         // Iterate
>         for (String fruit : fruits) {
>             System.out.println("Fruit: " + fruit);
>         }
>     }
> }
> ```
> ## When to Use
>
> ✅ **Use HashSet when:**
> - Need unique elements only
> - Don't care about order
> - Fast lookups are important
> 
> ❌ **Avoid HashSet when:**
> - Need insertion order (use LinkedHashSet)
> - Need sorting (use TreeSet)
> - Thread-safety required

</details>

<details>
<summary>5. Executor Framework in Java</summary>

> **Executor Framework** is a powerful framework for asynchronous task execution and management in Java, providing a higher-level replacement for working with threads directly.
> ## Core Components
> 
> | Component | Description |
> |-----------|-------------|
> | Executor | Interface for executing tasks |
> | ExecutorService | Extended interface with lifecycle methods |
> | ThreadPoolExecutor | Most common implementation |
> | ScheduledExecutorService | For delayed and periodic execution |
> | Future | Represents result of asynchronous computation |
> 
> ## Thread Pool Types
> 
> | Thread Pool | Description | Use Case |
> |-------------|-------------|----------|
> | FixedThreadPool | Fixed number of threads | CPU-intensive tasks |
> | CachedThreadPool | Creates threads as needed | Short-lived asynchronous tasks |
> | SingleThreadExecutor | Single worker thread | Sequential task execution |
> | ScheduledThreadPool | For scheduled tasks | Periodic or delayed execution |
> 
> ## Basic Usage Examples
> 
> ### Example 1: FixedThreadPool
> ```java
> import java.util.concurrent.*;
> 
> public class FixedThreadPoolExample {
>     public static void main(String[] args) {
>         // Create fixed thread pool with 4 threads
>         ExecutorService executor = Executors.newFixedThreadPool(4);
>         
>         // Submit tasks for execution
>         for (int i = 1; i <= 10; i++) {
>             final int taskId = i;
>             executor.submit(() -> {
>                 System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
>                 try {
>                     Thread.sleep(1000); // Simulate work
>                 } catch (InterruptedException e) {
>                     Thread.currentThread().interrupt();
>                 }
>             });
>         }
>         
>         // Shutdown the executor
>         executor.shutdown();
>     }
> }
> ```
> 
> ### Example 2: Using Callable and Future
> ```java
> import java.util.concurrent.*;
> import java.util.*;
> 
> public class CallableFutureExample {
>     public static void main(String[] args) throws Exception {
>         ExecutorService executor = Executors.newFixedThreadPool(3);
>         
>         // Submit Callable tasks that return results
>         Future<Integer> future1 = executor.submit(new SumTask(10, 20));
>         Future<Integer> future2 = executor.submit(new FactorialTask(5));
>         Future<String> future3 = executor.submit(new StringTask("Hello"));
>         
>         // Get results (blocks until available)
>         System.out.println("Sum: " + future1.get());
>         System.out.println("Factorial: " + future2.get());
>         System.out.println("String: " + future3.get());
>         
>         executor.shutdown();
>     }
> }
> 
> class SumTask implements Callable<Integer> {
>     private int a, b;
>     
>     public SumTask(int a, int b) {
>         this.a = a;
>         this.b = b;
>     }
>     
>     @Override
>     public Integer call() throws Exception {
>         Thread.sleep(1000); // Simulate computation
>         return a + b;
>     }
> }
> 
> class FactorialTask implements Callable<Integer> {
>     private int n;
>     
>     public FactorialTask(int n) {
>         this.n = n;
>     }
>     
>     @Override
>     public Integer call() throws Exception {
>         int result = 1;
>         for (int i = 1; i <= n; i++) {
>             result *= i;
>             Thread.sleep(100); // Simulate work
>         }
>         return result;
>     }
> }
> 
> class StringTask implements Callable<String> {
>     private String input;
>     
>     public StringTask(String input) {
>         this.input = input;
>     }
>     
>     @Override
>     public String call() throws Exception {
>         Thread.sleep(500);
>         return input.toUpperCase() + " processed by " + Thread.currentThread().getName();
>     }
> }
> ```
> 
> ### Example 3: ScheduledExecutorService
> ```java
> import java.util.concurrent.*;
> 
> public class ScheduledExecutorExample {
>     public static void main(String[] args) throws InterruptedException {
>         ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
>         
>         // Schedule task to run after 3 seconds delay
>         ScheduledFuture<?> future = scheduler.schedule(() -> {
>             System.out.println("Task executed after 3 seconds");
>         }, 3, TimeUnit.SECONDS);
>         
>         // Schedule task to run every 2 seconds after initial 1 second delay
>         scheduler.scheduleAtFixedRate(() -> {
>             System.out.println("Repeating task: " + System.currentTimeMillis());
>         }, 1, 2, TimeUnit.SECONDS);
>         
>         // Let it run for 10 seconds then shutdown
>         Thread.sleep(10000);
>         scheduler.shutdown();
>     }
> }
> ```
> 
> ## Advanced Features
> 
> ### 1. invokeAll - Execute Multiple Tasks
> ```java
> import java.util.concurrent.*;
> import java.util.*;
> 
> public class InvokeAllExample {
>     public static void main(String[] args) throws Exception {
>         ExecutorService executor = Executors.newFixedThreadPool(3);
>         
>         List<Callable<String>> tasks = Arrays.asList(
>             () -> { Thread.sleep(2000); return "Task 1 completed"; },
>             () -> { Thread.sleep(1000); return "Task 2 completed"; },
>             () -> { Thread.sleep(3000); return "Task 3 completed"; }
>         );
>         
>         // Execute all tasks and wait for all to complete
>         List<Future<String>> futures = executor.invokeAll(tasks);
>         
>         for (Future<String> future : futures) {
>             System.out.println(future.get());
>         }
>         
>         executor.shutdown();
>     }
> }
> ```
> 
> ### 2. invokeAny - Execute and Get First Result
> ```java
> import java.util.concurrent.*;
> import java.util.*;
> 
> public class InvokeAnyExample {
>     public static void main(String[] args) throws Exception {
>         ExecutorService executor = Executors.newFixedThreadPool(3);
>         
>         List<Callable<String>> tasks = Arrays.asList(
>             () -> { Thread.sleep(2000); return "Result from Task 1"; },
>             () -> { Thread.sleep(1000); return "Result from Task 2"; },
>             () -> { Thread.sleep(1500); return "Result from Task 3"; }
>         );
>         
>         // Returns the result of the first completed task
>         String firstResult = executor.invokeAny(tasks);
>         System.out.println("First completed: " + firstResult);
>         
>         executor.shutdown();
>     }
> }
> ```
> 
> ## Best Practices
> 
> ### 1. Proper Shutdown
> ```java
> ExecutorService executor = Executors.newFixedThreadPool(4);
> 
> try {
>     // Submit tasks
>     executor.submit(() -> System.out.println("Task running"));
> } finally {
>     // Always shutdown executor
>     executor.shutdown();
>     try {
>         // Wait for tasks to complete
>         if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
>             executor.shutdownNow(); // Force shutdown
>         }
>     } catch (InterruptedException e) {
>         executor.shutdownNow();
>         Thread.currentThread().interrupt();
>     }
> }
> ```
> 
> ### 2. Custom ThreadPoolExecutor
> ```java
> import java.util.concurrent.*;
> 
> public class CustomThreadPool {
>     public static void main(String[] args) {
>         // Create custom thread pool
>         ThreadPoolExecutor executor = new ThreadPoolExecutor(
>             2, // core pool size
>             5, // maximum pool size  
>             60, // keep alive time
>             TimeUnit.SECONDS,
>             new LinkedBlockingQueue<>(10), // work queue
>             new ThreadPoolExecutor.CallerRunsPolicy() // rejection policy
>         );
>         
>         // Use the executor
>         for (int i = 0; i < 15; i++) {
>             final int taskId = i;
>             executor.submit(() -> {
>                 System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
>             });
>         }
>         
>         executor.shutdown();
>     }
> }
> ```
> 
> ## When to Use Executor Framework
> 
> ✅ **Use Executor Framework when:**
> - Need to manage multiple threads efficiently
> - Want to control thread creation and resource usage
> - Need task scheduling capabilities
> - Working with asynchronous computations
> - Building server applications
> 
> ❌ **Avoid when:**
> - Simple single-threaded applications
> - Very small number of tasks
> - When complete control over thread lifecycle is needed
> 
> ## Benefits
> 
> | Benefit | Description |
> |---------|-------------|
> | **Thread Reuse** | Reduces thread creation overhead |
> | **Resource Management** | Controls maximum concurrent threads |
> | **Task Queueing** | Handles task submission spikes |
> | **Lifecycle Management** | Provides clean startup/shutdown |
> | **Error Handling** | Better exception handling with Futures |</details>
