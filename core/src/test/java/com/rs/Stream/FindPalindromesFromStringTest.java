package com.rs.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class FindPalindromesFromStringTest {
    FindPalindromesFromString t = new FindPalindromesFromString();

    @ParameterizedTest
    @CsvSource({
            "aahhhhxyxtptndyyhb, 1, 9",
            "aahhhhxyxtptndyyhb, 2, 3",
            "aahhhhxyxtptndyyhb, 3, 3",
            "aahhhhxyxtptndyyhb, 4, 1",
            "aahhhhxyxtptndyyhb, 5, 0"
    })
    void filterNewWay(String s, int i, int expected) {
        var l = t.filterNewWay(s,i);
        Assertions.assertEquals(expected,l.size());
    }

    @ParameterizedTest
    @CsvSource({
            "aahhhhxyxtptndyyhb,  28"
    })
    void findAllPalindromicSubstrings(String s, int expected) {
        var l = FindPalindromesFromString.findAllPalindromicSubstrings(s);
        Assertions.assertEquals(expected,l.size());
    }

    @ParameterizedTest
    @CsvSource({
            "aahhhhxyxtptndyyhb,  16"
    })
    void findDistinctPalindromicSubstrings(String s, int expected) {
        var l = FindPalindromesFromString.findDistinctPalindromicSubstrings(s);
        Assertions.assertEquals(expected,l.size());
    }

    @ParameterizedTest
    @CsvSource({
            "aahhhhxyxtptndyyhb,  4"
    })
    void groupPalindromesByLength(String s, int expected) {
        var l = FindPalindromesFromString.groupPalindromesByLength(s);
        Assertions.assertEquals(expected,l.size());
    }
}