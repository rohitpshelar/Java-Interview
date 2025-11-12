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
