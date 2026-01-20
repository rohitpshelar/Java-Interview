package com.rs.Flexible_Constructor;

class Person {

    final int age;

    Person(int age) {
        this.age = age;
    }
}


class Employee extends Person {

    final String name;

    public Employee(String name, int age) {
        if (age < 18 || age > 67)
            throw new IllegalArgumentException("Age must be between 18 and 67");
        super(age); // super() is no longer required as the first statement in Java 25
        this.name = name;
    }
}
 // ================================== OR ================================
// Using Record (Java 16+)
interface Person1 {
     int age();
 }

record Employee1 (String name, int age) implements Person1 {
    public Employee1 {
        if (age < 18 || age > 67)
            throw new IllegalArgumentException("Age must be between 18 and 67");
    }
}

class Record_Implement_Interface {
    public static void main(String[] args) {
        Employee emp = new Employee("Rohit", 30);
        System.out.println("Name: " + emp.name + ", Age: " + emp.age);
    }
}
