package com.rs.Stream;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Count_Char_From_List_Of_String {



    public void usinfSplit(List<String> stringList) {
        System.out.println("1 Using Split =========================");

        stringList.forEach(st -> {
            System.out.println(st);
            Stream.of(st.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .forEach((k, v) -> System.out.println("Character: " + k + ", Count: " + v));

        });

        System.out.println("Time Complexity: O(n*m) where n is number of strings and m is average length of strings");
    }

    public void usingChars(List<String> stringList) {
        System.out.println("2=========================");


        stringList.forEach(st -> {
            System.out.println(st);
            st.chars() // Convert each string to IntStream of chars
                    .mapToObj(c -> (char) c)      // Convert int to Character
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    )).forEach((k, v) -> System.out.println("Character: " + k + ", Count: " + v));
            ;
        });
    }

    public void usingFlatMapToInt(List<String> stringList) {
        System.out.println("3=========================");

        stringList.stream().peek(System.out::println)
                .flatMapToInt(String::chars)  // Convert each string to IntStream of chars
                .mapToObj(c -> (char) c)      // Convert int to Character
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .forEach(entry ->
                        System.out.println("Character: " + entry.getKey() + ", Count: " + entry.getValue())
                );
    }

    public static void main(String[] args) {
        List<String> s = List.of("rohit", "manasa");

        Count_Char_From_List_Of_String obj = new Count_Char_From_List_Of_String();
        obj.usinfSplit(s);
        obj.usingChars(s);
        obj.usingFlatMapToInt(s);
    }
}
