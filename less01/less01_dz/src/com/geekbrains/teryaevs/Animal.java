package com.geekbrains.teryaevs;

public abstract class Animal {
    public static int animalCounter = 0;

    private String name;
    private int age;

    private int runLimit;
    private int swimLimit;

    public Animal(String name, int age, int runLimit, int swimLimit) {
        this.name = name;
        this.age = age;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;

        animalCounter++;
    }

    @Override
    public String toString() {
        return name + ", " + age + " years old";
    }

    public void run(int length) {
        if (runLimit == 0)
            System.out.println(this + " can't run");
        else if (length > runLimit)
            System.out.println(this + " can't run so much");
        else
            System.out.println(this + " ran " + length + " meters");
    }


    public void swim(int length) {
        if (swimLimit == 0)
            System.out.println(this + " can't swim");
        else if (length > swimLimit)
            System.out.println(this + " can't swim so much");
        else
            System.out.println(this + " swam " + length + " meters");
    }

    public static int getCounter(){
        return animalCounter;
    }
}
