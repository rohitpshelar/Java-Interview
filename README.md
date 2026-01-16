# Java-Interview
Java Interview Question with Answers and Solution with Code

## JAVA

### - STREAM

<details>
<summary>1. Find Palindromes In String</summary>

```java
IntStream.range(0, s.length() / 2)
                .allMatch(i -> s.charAt(i) == s.charAt(s.length() - i - 1));
```
> Ref : [FindPalindromesFromString.java](core/src/main/java/com/rs/Stream/FindPalindromesFromString.java)

</details>

<details>
<summary>2. Generate -  Even, Odd, Unique... Numbers</summary>

> Ref : [GenerateNumbers.java](core/src/main/java/com/rs/Stream/GenerateNumbers.java)
> 
</details>

<details>
<summary>3. Model to DTO</summary>

```java
personModels.stream()
        .collect(Collectors.groupingBy(PersonModel::department))
        .entrySet().stream()
        .map(entry -> new ResponseDTO(
            entry.getKey(),
            entry.getValue().stream()
                .map(pm -> new PersonDTO(pm.firstName() + " " + pm.lastName(), pm.salary()))
                .toList()
        ))
        .toList();
```
> Ref : [ModelToDto.java](core/src/main/java/com/rs/Stream/ModelToDto.java)
</details>

<details>
<summary>4. Object Find 3rd Highest By Salary Than Name </summary>

```java
employeeList.stream()
        .sorted(Comparator.comparing(Employee::salary).reversed().thenComparing(Employee::name))
        .skip(2)
        .findFirst()
        .ifPresent(e -> System.out.println(e.name()));
```
> Ref : [Stream_Object_Find_By_Salary.java](core/src/main/java/com/rs/Stream/Stream_Object_Find_By_Salary.java)
</details>


### - Questions

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
<summary>3. Can we Do Multiple sorting using comparable ?</summary>

No - Only with Comparator
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
> | **Error Handling** | Better exception handling with Futures |
</details>

## Spring

<details>
<summary>1. @Qualifier Annotation in Spring</summary>

> **@Qualifier** is a Spring annotation used to resolve ambiguity when multiple beans of the same type are available for dependency injection.
>
> ## Problem It Solves
>
> > When multiple beans implement the same interface, Spring doesn't know which one to inject:
>
> ```java
> @Component
> class SMSService implements MessageService {
>     public void send(String message) {
>         System.out.println("Sending SMS: " + message);
>     }
> }
> 
> @Component
> class EmailService implements MessageService {
>     public void send(String message) {
>         System.out.println("Sending Email: " + message);
>     }
> }
> 
> @Service
> class NotificationService {
>     @Autowired
>     private MessageService messageService; // ❌ Which one to inject? SMSService or EmailService?
> }
> ```
>
> ## Solution with @Qualifier
>
> ### Method 1: Using @Qualifier with @Component
> ```java
> @Component
> @Qualifier("sms")
> class SMSService implements MessageService {
>     public void send(String message) {
>         System.out.println("Sending SMS: " + message);
>     }
> }
> 
> @Component
> @Qualifier("email")
> class EmailService implements MessageService {
>     public void send(String message) {
>         System.out.println("Sending Email: " + message);
>     }
> }
> 
> @Service
> class NotificationService {
>     @Autowired
>     @Qualifier("sms")  // ✅ Explicitly specifies SMSService
>     private MessageService messageService;
> }
> ```
>
> ### Method 2: Using @Qualifier with Constructor Injection
> ```java
> @Service
> class NotificationService {
>     private final MessageService messageService;
>     
>     @Autowired
>     public NotificationService(@Qualifier("email") MessageService messageService) {
>         this.messageService = messageService;  // ✅ EmailService will be injected
>     }
> }
> ```
>
> ### Method 3: Using Custom Qualifier Annotations
> ```java
> @Qualifier
> @Retention(RetentionPolicy.RUNTIME)
> public @interface SMS {}
> 
> @Qualifier
> @Retention(RetentionPolicy.RUNTIME)
> public @interface Email {}
> 
> @Component
> @SMS
> class SMSService implements MessageService {
>     // implementation
> }
> 
> @Component
> @Email
> class EmailService implements MessageService {
>     // implementation
> }
> 
> @Service
> class NotificationService {
>     @Autowired
>     @SMS  // ✅ Cleaner approach with custom qualifier
>     private MessageService messageService;
> }
> ```
>
> ## Key Points
>
> | Aspect | Description |
> |--------|-------------|
> | **Purpose** | Resolves bean ambiguity during dependency injection |
> | **Usage** | Used with @Autowired to specify which bean to inject |
> | **Alternatives** | @Primary annotation, bean names |
> | **Best Practice** | Use with constructor injection for better testability |
>
> ## When to Use @Qualifier
>
> ✅ **Use @Qualifier when:**
> - Multiple beans implement the same interface
> - You need explicit control over which bean gets injected
> - Different beans serve different purposes in different contexts
>
> ❌ **Don't use when:**
> - Only one bean of a type exists
> - Using @Primary makes more sense for default bean selection
</details>

<details>
<summary>2. IOC Container in Spring</summary>

> **IOC (Inversion of Control) Container** is the core engine of Spring Framework that manages the complete lifecycle of Spring beans - from creation to destruction.
>
> ## What is IOC Container?
>
> > Instead of you creating objects manually, the IOC container creates and manages them for you:
>
> ```java
> // Without IOC Container (Traditional)
> UserService userService = new UserService();
> UserRepository userRepository = new UserRepository();
> userService.setRepository(userRepository);
> 
> // With IOC Container (Spring)
> @Component
> class UserService {
>     @Autowired
>     private UserRepository userRepository;
>     // Spring automatically creates and injects dependency
> }
> ```
>
> ## Key Responsibilities
>
> | Responsibility | Description |
> |----------------|-------------|
> | **Bean Creation** | Instantiates beans using reflection |
> | **Dependency Injection** | Injects dependencies automatically |
> | **Lifecycle Management** | Manages bean lifecycle (init/destroy) |
> | **Configuration** | Applies configurations and profiles |
> | **Scope Management** | Manages singleton, prototype scopes |
>
> ## Types of IOC Containers
>
> ### 1. BeanFactory (Basic)
> ```java
> // Legacy - lighter container
> Resource resource = new ClassPathResource("beans.xml");
> BeanFactory factory = new XmlBeanFactory(resource);
> MyBean bean = (MyBean) factory.getBean("myBean");
> ```
>
> ### 2. ApplicationContext (Advanced - Most Used)
> ```java
> // Modern - enterprise features
> ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
> // OR
> ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
> MyService service = context.getBean(MyService.class);
> ```
>
> ## How It Works
>
> ### Step-by-Step Process:
> 1. **Scan** - Scans for @Component, @Service, @Repository annotations
> 2. **Create** - Instantiates beans using default/no-arg constructor
> 3. **Configure** - Applies @Value, @Autowired configurations
> 4. **Inject** - Resolves and injects dependencies
> 5. **Initialize** - Calls @PostConstruct methods
> 6. **Ready** - Bean is ready for use
> 7. **Destroy** - Calls @PreDestroy methods during shutdown
>
> ## Example
>
> ```java
> // Configuration
> @Configuration
> @ComponentScan("com.example")
> public class AppConfig {
> }
> 
> // Bean classes
> @Service
> class UserService {
>     private final UserRepository userRepository;
>     
>     @Autowired
>     public UserService(UserRepository userRepository) {
>         this.userRepository = userRepository;
>     }
> } 
> 
> @Repository  
> class UserRepository {
>     // Data access code
> }
> 
> // Usage
> public class Main {
>     public static void main(String[] args) {
>         ApplicationContext context = 
>             new AnnotationConfigApplicationContext(AppConfig.class);
>         
>         UserService userService = context.getBean(UserService.class);
>         userService.processUser(); // IOC container manages everything!
>     }
> }
> ```
>
> ## Benefits
>
> ✅ **Loose Coupling** - Components don't create dependencies  
> ✅ **Easy Testing** - Can easily mock dependencies  
> ✅ **Configuration Management** - Centralized configuration  
> ✅ **Lifecycle Management** - Automatic initialization and destruction  
> ✅ **AOP Support** - Easy aspect-oriented programming
>
> ## Key Points
>
> > - **IOC** = Inversion of Control (Framework controls object creation)
> > - **DI** = Dependency Injection (Method of achieving IOC)
> > - **Container** = Runtime environment that manages beans
> > - **ApplicationContext** is the most commonly used IOC container
</details>

## SQL

<details>
<summary>1. Fetching orders placed within the last month using SQL</summary>

```sql
    -- MySQL/MariaDB
    SELECT * FROM orders 
    WHERE date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) 
    AND date <= CURDATE();
    
    -- PostgreSQL
    SELECT * FROM orders
    WHERE date >= CURRENT_DATE - INTERVAL '1 month'
    AND date <= CURRENT_DATE;
    
    -- SQL Server
    SELECT * FROM orders
    WHERE date >= DATEADD(month, -1, GETDATE())
    AND date <= GETDATE();
```
</details>

<details>
<summary>2. Find the number of cases where emails have attachments </summary>   

The Schema:
case: caseld, emailld (email ID)

email: emailld, fileld (file ID)

file: emailid, fileld (file ID, email ID it belongs to)

```sql
    -- Count of cases that have emails with attachments
    SELECT COUNT(DISTINCT c.caseld) as cases_with_attachments
    FROM case c
    INNER JOIN email e ON c.emailld = e.emailld
    INNER JOIN file f ON e.fileld = f.fileld
    WHERE f.emailid = e.emailld;
    
    -- Alternative (simpler if file table has emailid)
    SELECT COUNT(DISTINCT c.caseld) as cases_with_attachments
    FROM case c
    INNER JOIN file f ON c.emailld = f.emailid
    WHERE f.fileld IS NOT NULL;
    
    -- Count cases where their email has at least one file attachment ( PERFORMANCE )
    SELECT COUNT(DISTINCT c.caseld) as cases_with_attachments
    FROM case c
    WHERE EXISTS (
    SELECT 1 
    FROM email e 
    INNER JOIN file f ON e.fileld = f.fileld
    WHERE e.emailld = c.emailld
    AND f.emailid = e.emailld
    );
    
    -- Count attachments per case
    SELECT 
    c.caseld,
    COUNT(DISTINCT f.fileld) as attachment_count
    FROM case c
    LEFT JOIN email e ON c.emailld = e.emailld
    LEFT JOIN file f ON e.fileld = f.fileld AND f.emailid = e.emailld
    GROUP BY c.caseld
    HAVING COUNT(DISTINCT f.fileld) > 0;
```
</details>

<details>
<summary>3. Employees whose dependent's age is less than 10 years</summary>

employee - id, name, dependent

dependent - id, name, DOB, gender

```sql
    -- MySQL/PostgreSQL/SQL Server
    SELECT DISTINCT e.* 
    FROM employee e
    INNER JOIN dependent d ON e.id = d.employee_id
    WHERE TIMESTAMPDIFF(YEAR, d.DOB, CURDATE()) < 10;
    
    -- PostgreSQL alternative
    SELECT DISTINCT e.* 
    FROM employee e
    INNER JOIN dependent d ON e.id = d.employee_id
    WHERE EXTRACT(YEAR FROM AGE(CURRENT_DATE, d.DOB)) < 10;
    
    -- SQL Server alternative
    SELECT DISTINCT e.* 
    FROM employee e
    INNER JOIN dependent d ON e.id = d.employee_id
    WHERE DATEDIFF(YEAR, d.DOB, GETDATE()) < 10;
```

</details>

