package com.geekbrains.teryaevs;

public class Dog extends Animal {
    public static int dogCounter = 0;

    public Dog(String name, int age) {
        super(name, age, 500, 10);

        dogCounter++;
    }

    @Override
    public String toString() {
        return "Dog " + super.toString();
    }

    public static int getCounter(){
        return dogCounter;
    }
}
