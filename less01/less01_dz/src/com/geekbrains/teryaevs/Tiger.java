package com.geekbrains.teryaevs;

public class Tiger extends Animal {
    public static int tigerCounter = 0;

    public Tiger(String name, int age) {
        super(name, age, Integer.MAX_VALUE, Integer.MAX_VALUE);

        tigerCounter++;
    }

    @Override
    public String toString() {
        return "Tiger " + super.toString();
    }

    public static int getCounter(){
        return tigerCounter;
    }
}
