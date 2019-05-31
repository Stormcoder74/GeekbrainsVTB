package com.geekbrains.teryaevs.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 10;

    public static void main(String[] args) throws Exception {
        CyclicBarrier start = new CyclicBarrier(CARS_COUNT + 1);
        Finish finish = new Finish(new CountDownLatch(1));

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40, finish));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), start);
        }

        Thread[] threads = new Thread[CARS_COUNT];

        for (int i = 0; i < CARS_COUNT; i++) {
            Thread thread = new Thread(cars[i]);
            thread.start();
            threads[i] = thread;
        }


        start.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        finish.getFinishLine().await();
        System.out.println(finish.getWinner().getName() + " WIN!!!");

        for (int i = 0; i < CARS_COUNT; i++)
            threads[i].join();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

    }
}
