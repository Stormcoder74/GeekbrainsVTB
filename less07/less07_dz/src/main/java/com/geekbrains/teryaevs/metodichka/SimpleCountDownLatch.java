package com.geekbrains.teryaevs.metodichka;

import java.util.concurrent.CountDownLatch;

public class SimpleCountDownLatch {
    public static void main(String[] args) {
        final int THREADS_COUNT = 6; // задаем количество потоков
        final CountDownLatch cdl = new CountDownLatch(6);

        System.out.println("Начинаем");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1500 + (int) (2500 * Math.random()));
                    cdl.countDown();
                    System.out.println("Поток #" + w + " - готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            System.out.println("основной поток ждет на await");
            cdl.await();
            System.out.println("основной поток прошел await");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работа завершена");
    }
}
