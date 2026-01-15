package com.rs.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPeek {

    public static void main(String[] args) {
        List<String> products = Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor");

// Using peek for debugging pipeline
        List<String> filteredProducts = products.stream()
                .peek(p -> System.out.println("1. Original: " + p))
                .filter(p -> p.length() > 5)
                .peek(p -> System.out.println("2. After length filter: " + p))
                .map(String::toUpperCase)
                .peek(p -> System.out.println("3. After uppercase: " + p))
                .sorted()
                .peek(p -> System.out.println("4. After sorting: " + p))
                .collect(Collectors.toList());

        System.out.println("Final result: " + filteredProducts);
    }
}
