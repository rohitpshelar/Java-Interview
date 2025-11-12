package com.rs.Flexible_Constructor;

public class JavaMemory {

    int nonStaticVariable;
    static int staticVariable;

    String nonStaticString;
    static String staticString;


    static void staticMethod() {
        System.out.println(staticVariable);
        System.out.println(staticString);
    }

    void nonStaticMethod() {
        System.out.println(staticVariable);
        System.out.println(nonStaticVariable);
        System.out.println(staticString);
        System.out.println(nonStaticString);
    }
}

class MainClass {
    public static void main(String[] args) {
        JavaMemory.staticVariable = 10;
        JavaMemory.staticString = "Hello Static";
        JavaMemory.staticMethod();

        JavaMemory a1 = new JavaMemory();
        JavaMemory a2 = new JavaMemory();

        System.out.println(a1.nonStaticVariable);
        System.out.println(a1.staticVariable);
        System.out.println(a1.staticString);
        a1.nonStaticMethod();
        a1.staticMethod();

        System.out.println(a2.staticVariable);
        a1.staticVariable = 20;
        System.out.println(a2.staticVariable);

        System.out.println(a2.staticString);
        a1.staticString = "Rohit";
        System.out.println(a2.staticString);


    }
}

