package com.geekbrains.teryaevs.task3;

public class MainApp {
    public static void main(String[] args) {

        Box<Apple> aBox1 = new Box<>();
        Box<Apple> aBox2 = new Box<>(new Apple(),
                new Apple(), new Apple(), new Apple(), new Apple());
        Box<Orange> oBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            aBox1.add(new Apple());
            oBox.add(new Orange());
        }

        System.out.println("первая коробка яблок весит: " + aBox1.getWeight());
        System.out.println("вторая коробка яблок весит: " + aBox2.getWeight());
        System.out.println("коробка апельсинов весит: " + oBox.getWeight());
        System.out.println("сравним коробку апельсинов с первой коробкой яблок: " + oBox.compare(aBox1));

        aBox1.pourIn(aBox2);
        System.out.println("пересыпали первую коробку яблок во вторую");

        System.out.println("первая коробка яблок весит: " + aBox1.getWeight());
        System.out.println("вторая коробка яблок весит: " + aBox2.getWeight());
        System.out.println("сравним коробку апельсинов со второй коробкой яблок: " + oBox.compare(aBox2));
    }
}
