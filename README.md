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
