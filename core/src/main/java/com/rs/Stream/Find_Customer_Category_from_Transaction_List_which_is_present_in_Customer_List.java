package com.rs.Stream;

import java.util.List;


public class Find_Customer_Category_from_Transaction_List_which_is_present_in_Customer_List {

    //customer - id , name , List<Transactions>
    //transaction - id , type (debit/credit), amount

    //50000 - rich
    //>20000 to <500000 - middle
    //<20000 - poorm

    public static void Find_from_SUM_amount(List<Customer> customers){
        customers.forEach(c -> {
            double totalAmount = c.txns().stream()
                    .mapToDouble(Transaction::amount)
                    .sum();

            String category = switch (totalAmount) {
                case int n when n > 50000 -> "rich";
                case int n when n >= 20000 -> "middle";
                default -> "poor";
            };

            System.out.println("Customer: " + c.name() + ", Total Amount: " + totalAmount + ", Category: " + category);
        });
        System.out.println("Time Complexity: O(n*m) where n is number of customers and m is average number of transactions per customer");
    }

    public static void Find_from_MAX_amount(List<Customer> customers){
        customers.forEach(c -> {
            double totalAmount = c.txns().stream()
                    .mapToDouble(Transaction::amount)
                    .max().orElse(0.0);


            String category;
            if (totalAmount > 50000) {
                category = "rich";
            } else if (totalAmount >= 20000) {
                category = "middle";
            } else {
                category = "poor";
            }

            System.out.println("Customer: " + c.name() + ", Total Amount: " + totalAmount + ", Category: " + category);
        });
    }

    public static void Find_from_SUM_credit_amount(List<Customer> customers){
        customers.forEach(c -> {
            double totalAmount = c.txns().stream()
                    .filter(t-> t.type().equalsIgnoreCase("credit"))
                    .mapToDouble(Transaction::amount)
                    .sum();


            String category;
            if (totalAmount > 50000) {
                category = "rich";
            } else if (totalAmount >= 20000) {
                category = "middle";
            } else {
                category = "poor";
            }

            System.out.println("Customer: " + c.name() + ", Total Amount: " + totalAmount + ", Category: " + category);
        });
    }

    public static void main(String[] args) {
        List<Customer> customers = List.of(
                new Customer(1, "Rohit", List.of(
                        new Transaction(1, "debit", 20000.0),
                        new Transaction(2, "credit", 9000.0),
                        new Transaction(3, "credit", 19000.0)
                )),
                new Customer(2, "Don", List.of(
                        new Transaction(3, "debit", 60000.0),
                        new Transaction(4, "credit", 20000.0),
                        new Transaction(5, "credit", 40000.0)
                )),
                new Customer(3, "Samit", List.of(
                        new Transaction(5, "debit", 1000.0),
                        new Transaction(6, "credit", 7000.0),
                        new Transaction(7, "credit", 8000.0)
                ))
        );


        System.out.println("\nUsing SUM:");
        Find_from_SUM_amount(customers);
        System.out.println("\nUsing MAX:");
        Find_from_MAX_amount(customers);
        System.out.println("\nUsing SUM credit amount:");
        Find_from_SUM_credit_amount(customers);

    }
}

record Customer(Integer id, String name, List<Transaction> txns) {
}

record Transaction(Integer id, String type, Double amount) {
}
