package com.rs.Stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
//    private static final  Logger logger = Logger.getLogger(StreamTest.class.getName());
//    https://docs.google.com/forms/d/e/1FAIpQLSdkVpvXCBwes45F0-FykV0otYLcUa36MS9fGnntUJGjrs2D2Q/viewform
//
//    Q1.
//public void m1(String str){
//        logger.log(Level.INFO, "str");
//    }
//    private void m1(Object obj){
//        logger.log(Level.INFO, "obj");
//    }
//    p s v main(String[] args){
//        m1(null);
//    }
//    What will be the output of the above code?
//            *
//    a) str
//    b) obj
//    c) ambiguity error
//    d) Compile-time error
//    e) NullPointerException

//    Q2.
//    
//        public static void main(String[] args) {
//            Stream<Integer> infiniteStream = Stream.iterate(1, i -> i + 1);
//            int sum = infiniteStream.filter(i -> i % 2 == 0)
//                    .limit(5)
//                   // .sum(); // this is for primitives like IntStream, LongStream, or DoubleStream and not  Stream<Integer>
//                    .reduce(0, Integer::sum);
//
//            System.out.println(sum);
//        }

//    What will be the output of the above code?
//    a) 0
//    b) 6
//    c) 20
//    d) Compile-time error
//    e) 30 -------------

//    Q3.
//    
//        public static void main(String[] args) {
//            List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
//
//            String result = words.stream()
//                    .collect(Collector.of(
//                            StringBuilder::new,
//                            (sb, s) -> sb.append(s).append(" "),
//                            StringBuilder::append,
//                            StringBuilder::toString
//                    ));
//            System.out.println(result);
//
////            OR
//
//            String result2 = String.join(" ", words);
//            System.out.println(result2);
//
//        }

//    What will be the output of the above code?
//    a) apple, banana, cherry, date
//    b) apple banana cherry date -----------------------------
//    c) applebanana cherrydate
//    d) Compile-time error

//    Q4.
//    
        public static void main(String[] args) {
        // Parallel Stream
            List<Integer> numbers = Arrays.asList(1,5 );
            int result = numbers.parallelStream()
                    .reduce(3, (a, b) -> a * b);
            System.out.println(result); // Output: 243
// OR
            int result2 = 1;
            for(int i=0;i<numbers.size();i++){
                result2 = (numbers.get(i) * 3) * result2;
            }
            System.out.println(result2); // Output: 243
//--------------------------------------------------------------------------
        // Sequential Stream
            int result4 = numbers.stream()
                    .reduce(3, (a, b) -> a * b);
            System.out.println(result4);
// OR
            int result5 = 1;
            for (Integer number : numbers) {
                result5 = number * result5;
            }
            result5 = 3 * result5;
            System.out.println(result5);



        }
//    }
//    What will be the output of the above code?
//    a) 120 ------------------------------------------------------
//    b) Undefined, due to parallel processing
//    c) The result might vary, but it will always be greater than 5
//    d) 0

//    Q5.
//    
//        public static void main(String[] args) {
//            Optional<String> name = Optional.of("John");
//            Optional<String> upperName = name.map(String::toUpperCase);
//            Optional<Optional<String>> nestedName = upperName.map(Optional::of);
//            Optional<String> flatName = nestedName.flatMap(opt->opt);
//
//            System.out.println(flatName.orElse("Not found"));
//        }
//    }
//    What will be the output of the above code?
//    a) JOHN  ---------------------------------------------
//    b) Optional[JOHN]
//    c) Not found
//    d) Compile-time error

//    Q6.
//    
//        public static void main(String[] args) {
//            List<String> fruits = Arrays.asList("apple", "banana", "cherry", "apple", "banana", "apple");
//
//            Map<String, Long> result = fruits.stream()
//                    .collect(Collectors.groupingBy(fruit -> fruit,
//                            Collectors.counting()));
//
//            System.out.println(result);
//        }
//    }
//    What will be the output of the above code?
//    a) {banana=2, cherry=1, apple=3}-------------
//    b) {apple=1, banana=1, cherry=1}
//    c) Compile-time error
//    d) {apple=2, banana=2, cherry=2}

//    Q7.
//    
//        public static void main(String[] args) {
//            List<String> names = Arrays.asList("Tom", "Jerry", "Spike");
//
//            List<String> immutableList = names.stream()
//                    .collect(Collectors.collectingAndThen(Collectors.toList(),
//                            Collections::unmodifiableList));
//
//            immutableList.add("Max");
//            System.out.println(immutableList);
//        }
//    }
//    What will happen when this code is executed?
//
//    a) Max will be added to the list and it will print [Tom, Jerry, Spike, Max]
//    b) A runtime error will occur due to an unsupported operation ----------------
//    c) The code will compile but not print anything
//    d) Compile-time error

//    Q8.
//    
//        public static void main(String[] args) {
//            ForkJoinPool forkJoinPool = new ForkJoinPool(2);
//
//            forkJoinPool.submit(() -> {
//                IntStream.range(1, 10)
//                        .parallel()
//                        .forEach(i -> System.out.println(Thread.currentThread().getName() + " : " + i));
//            }).join();
//        }

//    Which of the following is true about the output of the above code?
//    a) The output will be ordered from 1 to 9
//    b) The output will be unordered due to parallel execution
//    c) All elements will be printed by the same thread
//    d) Compile-time error

//    Q9.
//    
//        public static void main(String[] args) {
//            List<Integer> numbers = Arrays.asList(3, 4, 5, 6, 7);
//
//            boolean anyMatch = numbers.stream().anyMatch(n -> n > 6);
//            boolean allMatch = numbers.stream().allMatch(n -> n < 10);
//            boolean noneMatch = numbers.stream().noneMatch(n -> n < 3);
//
//            System.out.println(anyMatch + " " + allMatch + " " + noneMatch);
//        }
//    }
//    What will be the output of the above code?
//    a) true true true
//    b) false true false
//    c) true true false
//    d) true false true

//    Q10.
//    
//        public static void main(String[] args) throws Exception {
//            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
//            CompletableFuture<String> processedFuture = future.thenApply(str -> str + " World");
//
//            System.out.println(processedFuture.get());
//        }
//    }
//    What will be the output of the above code?
//    a) Hello
//    b) Hello World
//    c) NullPointerException
//    d) Compile-time error
//    Q1:Given an integer array nums of unique elements, return all possible subsets.
//
//    The solution set must not contain duplicate subsets. Return the solution in any order.
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//    Example 2:
//
//    Input: nums = [0]
//    Output: [[],[0]]

//    Q2:Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
//            Input: intervals = [[1,3],[5,10],[7,20]]
//    Output: 2
//    Input: intervals = [[2,4],[7,10]]
//    Output: 1
//    Q3:Valid Palindrome
//    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//    Given a string s, return true if it is a palindrome, or false otherwise.
//
//            Example 1:
//    Input: s = "A man, a plan, a canal: Panama"
//    Output: true
//    Explanation: "amanaplanacanalpanama" is a palindrome.
//
//            Example 2:
//    Input: s = "race a car"
//    Output: false
//    Explanation: "raceacar" is not a palindrome.
//
//    Example 3:
//    Input: s = " "
//    Output: true
//    Explanation: s is an empty string "" after removing non-alphanumeric characters.
//    Since an empty string reads the same forward and backward, it is a palindrome.

//            Q4:Kth Largest Element in an Array
//    Given an integer array nums and an integer k, return the kth largest element in the array.
//    Note that it is the kth largest element in the sorted order, not the kth distinct element.
//    Can you solve it without sorting?
//
//    Example 1:
//    Input: nums = [3,2,1,5,6,4], k = 2
//    Output: 5
//
//    Example 2:
//    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//    Output: 4

//    public static void main(String[] args) {
//        System.out.println(" ");
//    }
}
