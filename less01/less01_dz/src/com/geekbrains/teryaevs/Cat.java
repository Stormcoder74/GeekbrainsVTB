package com.geekbrains.teryaevs;

public class Cat extends Animal {
    public static int catCounter = 0;

    public Cat(String name, int age) {
        super(name, age, 200, 0);

        catCounter++;
    }

    @Override
    public String toString() {
        return "Cat " + super.toString();
    }

    public static int getCounter(){
        return catCounter;
    }
}
