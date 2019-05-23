package com.geekbrains.teryaevs;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {
                new Cat("Barsik", 3),
                new Cat("Murzik", 4),
                new Dog("Sharik", 7),
                new Dog("Barbos", 5),
                new Dog("Pirat", 4),
                new Tiger("Sherkhan", 12)
        };

        for (Animal animal : animals) {
            animal.run(300);
            animal.run(150);
            animal.swim(15);
            animal.swim(7);
        }

        System.out.println();
        System.out.println("took part in the competition:");
        System.out.println("animal " + Animal.getCounter());
        System.out.println("cat " + Cat.getCounter());
        System.out.println("dog " + Dog.getCounter());
        System.out.println("tiger " + Tiger.getCounter());
    }


}
