package com.rs.Stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindPalindromesFromString {

//    Given a string and a number depicting the size of all the palindromes,
//    find all the palindromes from that string which are present as substrings

//    "aahhhhxyxtptndyyhb"

    private boolean ispalindrom(String s) {
        boolean state = true;
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) == s.charAt(s.length()-1-i))) {
                state = false;
            }
        }
        return state;
    }

    // Check if a string is palindrome
    public static boolean isPalindrome(String s) {
        return IntStream.range(0, s.length() / 2)
                .allMatch(i -> s.charAt(i) == s.charAt(s.length() - i - 1));
    }

    public List<String> filterNewWay(String a, int i){
        List<String> l = new ArrayList<>();
        for (int j = 0; j < a.length(); j++) {
            String b = a.substring(j);
            if(ispalindrom(b)){
                l.add(b);
            }
            for (int k = 0; k < b.length(); k++) {
                String c = b.substring(0,b.length()-k);
                if(ispalindrom(c)){
                    l.add(c);
                }
            }
        }
       return  new HashSet<>(l).stream().filter(s-> s.length() == i).peek(System.out::println).toList();

    }

    public static void main(String[] args) {

        String input = "aahhhhxyxtptndyyhb";
        System.out.println(isPalindrome("abba"));
//        new FindPalindromesFromString().filterNewWay(input,3);
//
//        // Find all palindromic substrings
//        List<String> palindromes = findAllPalindromicSubstrings(input);
//        System.out.println("All palindromic substrings: " + palindromes);
//
//        // Find distinct palindromic substrings
//        List<String> distinctPalindromes = findDistinctPalindromicSubstrings(input);
//        System.out.println("Distinct palindromic substrings: " + distinctPalindromes);
//
//        // Group by length
//        Map<Integer, List<String>> byLength = groupPalindromesByLength(input);
//        System.out.println("Palindromes grouped by length: " + byLength);
    }

    public static List<String> findAllPalindromicSubstrings(String str) {
        return IntStream.range(0, str.length())
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i + 1, str.length())
                        .mapToObj(j -> str.substring(i, j)))
                .filter(FindPalindromesFromString::isPalindrome)
                .collect(Collectors.toList());
    }

    // Find distinct palindromic substrings
    public static List<String> findDistinctPalindromicSubstrings(String str) {
        return IntStream.range(0, str.length())
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i + 1, str.length())
                        .mapToObj(j -> str.substring(i, j)))
                .filter(FindPalindromesFromString::isPalindrome)
                .distinct()
                .collect(Collectors.toList());
    }

    // Group palindromes by their length
    public static Map<Integer, List<String>> groupPalindromesByLength(String str) {
        return IntStream.range(0, str.length())
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(i + 1, str.length())
                        .mapToObj(j -> str.substring(i, j)))
                .filter(FindPalindromesFromString::isPalindrome)
                .distinct()
                .collect(Collectors.groupingBy(String::length));
    }


}
