package com.rs.Stream;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class TestCapcoFindProfitFromStock {

//    You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//    Find the maximum profit you can achieve. You may complete at most two transactions.
//
//            Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//    Example 1:
//
//    Input: prices = [3,3,5,0,0,3,1,4]
//
//    Output: 6
//
//    Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
//    Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

    public static void main(String[] args) {
        TestCapcoFindProfitFromStock t = new TestCapcoFindProfitFromStock();
        int[] prices = {3,3,5,0,0,3,1,4};
//        int[] prices = {3,5};
//IntStream.of(prices).forEach(System.out::println);
//        System.out.println(t.firstDuplicate(prices));
        System.out.println(t.findProfitFromStockStream2(prices));
    }

//    public int firstDuplicate(int[] arr) {
//        Set<Integer> seen = new HashSet<>();
//        return IntStream.of(arr)
//                .filter(x -> !seen.add(x))   // add returns false for duplicates
//                .findFirst()                 // first duplicate (second occurrence earliest)
//                .orElse(-1);                 // return -1 if none
//    }

    public int findProfitFromStock(int[] prices){
        int s = 0;
        int buy = 0;
        int buyDay = 0;
        int selDay = 0;
        boolean active = false;
        boolean day = false;
        int total = 0;
        for (int j = 0; j < prices.length; j++) {
            for (int i = 0; i < prices.length ; i++) {
                if(prices[i] < s && !active && !day){
                    buy = prices[i];
                    buyDay = i+1;
                    active = true;
                    day = true;
                }

                if(prices[i] > s && active){
                    var sum = prices[i] - buy;
                    selDay = i+1;
                    System.out.println("Buy on day "+buyDay+" (price = "+buy+") and sell on day "+selDay+" (price = "+prices[i]+"), profit = "+prices[i]+"-"+buy+" = "+sum);
                    active = false;
                    day = false;
                    s = s+1;
                    j= i;
                    total = total + sum;
                }
            }
            s = s+1;
        }
        System.out.println("Total : "+total);
        return  total;
    }

    public int findProfitFromStockStream2(int[] prices) {
        int n = prices == null ? 0 : prices.length;
        if (n < 2) return 0;

        int[] left = new int[n];      // max profit up to day i
        int[] right = new int[n + 1]; // max profit from day i to end

        // build left using streams (mutable holder for min price)
        left[0] = 0;
        final int[] min = new int[] { prices[0] };
        IntStream.range(1, n).forEach(i -> {
            min[0] = Math.min(min[0], prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min[0]);
        });

        // build right using streams (mutable holder for max price), iterate from n-2 down to 0
        right[n - 1] = 0;
        final int[] max = new int[] { prices[n - 1] };
        IntStream.iterate(n - 2, i -> i - 1).limit(n - 1).forEach(i -> {
            max[0] = Math.max(max[0], prices[i]);
            right[i] = Math.max(right[i + 1], max[0] - prices[i]);
        });

        // combine using IntStream
         return IntStream.range(0, n)
                .map(i -> left[i] + right[i])
                .max()
                .orElse(0);
    }

}
