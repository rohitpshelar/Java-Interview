package com.rs.Stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GenerateNumbers {
	public static void main(String[] args) {

        int n = 20;

        System.out.println("--- Even Number : e%3==0 ---");
        IntStream.rangeClosed(1, n).filter(e-> e%2==0).forEach(System.out::println);

        System.out.println("--- Odd Number : e%3==0 ---");
        IntStream.rangeClosed(1, n).filter(e-> e%3==0).forEach(System.out::println);

        System.out.println("--- Unique Number : e%3!=0 ---");
        IntStream.rangeClosed(1, n).filter(e-> e%3!=0).filter(e-> e%2!=0).forEach(System.out::println);

        System.out.println("--- Consecutive Odd Number : e%2!=0 ---");
        IntStream.rangeClosed(1, n).filter(e-> e%2!=0).forEach(System.out::println);

        System.out.println("--- Generate letter N times  ---");
        Stream.generate(() -> "A").limit(n).forEach(System.out::println);

        System.out.println("--- Double number N times : e%2!=0 ---");
        Stream.iterate(1, ni -> ni * 2).limit(n).forEach(System.out::println);

	}
}