package com.rs;

public class Facrory_Design_pattern {

    public static void main(String[] args) {
        AnimalFactory dogFactory = new DogFactory();
        Animal dog = dogFactory.createAnimal();
        dog.speak(); // Output: Woof!

        AnimalFactory catFactory = new CatFactory();
        Animal cat = catFactory.createAnimal();
        cat.speak(); // Output: Meow!
    }
}

// 1. Product Interface
interface Animal {
    void speak();
}

// 2. Concrete Products
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}

// 3. Creator Interface/Abstract Class (Factory)
abstract class AnimalFactory {
    public abstract Animal createAnimal();
}

// 4. Concrete Creators
class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}

class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

