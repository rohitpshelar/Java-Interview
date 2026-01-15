package com.rs.Stream;

import java.util.Comparator;
import java.util.List;

record Employee (Integer id, String name, Integer salary) implements Comparable<Employee>{
    @Override
    public int compareTo(Employee o) {
        int salaryCompare =  this.salary.compareTo(o.salary);
        int nameCompare = this.name.compareTo(o.name);
        return (salaryCompare == 0) ? nameCompare : salaryCompare;
    }
}

public class Stream_Object_Find_By_Salary {

    private static List<Employee> getListOfEmployee() {
        return List.of(
            new Employee(1, "Rohit", 90000),
            new Employee(2, "Don", 1000),
            new Employee(3, "Samit", 110000),
            new Employee(4, "Janu", 50000)
        );
    }

    public static void main(String[] args) {

        var employeeList = getListOfEmployee();

//        # findEmpByThirdHighestSalary - Approach 1 - Using Comparable interface
        System.out.println("Using Comparable interface");
        employeeList.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .limit(1)
                .forEach(e -> System.out.println(e.name()));

//        # findEmpByThirdHighestSalary - Approach 2 - Using Comparator
        System.out.println("Using Comparator");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed().thenComparing(Employee::name))
                .skip(2)
                .findFirst()
                .ifPresent(e -> System.out.println(e.name()));

    }
}