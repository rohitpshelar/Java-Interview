package com.rs.Stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Stream_Object_Find_By_Salary {

    public static void main(String[] args) {

        var employeeList = getListOfEmployee();

//        # findEmpByThirdHighestSalary - Approach 1 - Using Comparable interface
        System.out.println("Using Comparable interface");
        employeeList.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .limit(1)
                .forEach(e -> System.out.println(e.name));

//        # findEmpByThirdHighestSalary - Approach 2 - Using Comparator
        System.out.println("Using Comparator");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(2)
                .findFirst()
                .ifPresent(e -> System.out.println(e.getName()));

    }

    private static List<Employee> getListOfEmployee() {

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Rohit", 90000));
        employeeList.add(new Employee(2, "Don", 1000));
        employeeList.add(new Employee(3, "Samit", 110000));
        employeeList.add(new Employee(4, "Janu", 50000));
        return employeeList;
    }
}

class Employee implements Comparable<Employee> {
    Integer id;
    String name;
    Integer salary;

    public Employee(Integer id, String name, Integer salary) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return this.salary.compareTo(o.salary);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}