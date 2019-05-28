package com.geekbrains.teryaevs;

import java.util.Arrays;

public class MainApp {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    static class MyThread extends Thread {
        float[] arr;

        MyThread(float[] arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            for (int i = 0; i < HALF; i++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static void method1() {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++)
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        System.out.println("method1(): " + (System.currentTimeMillis() - startTime) + " мсек.");

    }

    static void method2() {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();

        float[] half1 = new float[HALF];
        float[] half2 = new float[HALF];
        System.arraycopy(array, 0, half1, 0, HALF);
        System.arraycopy(array, HALF, half2, 0, HALF);

        Thread thread1 = new MyThread(half1);
        Thread thread2 = new MyThread(half2);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(half1, 0, array, 0, HALF);
        System.arraycopy(half2, 0, array, HALF, HALF);

        System.out.println("method2(): " + (System.currentTimeMillis() - startTime) + " мсек.");
    }

    public static void main(String[] args) {
        new Thread(() -> method1()).start();
        new Thread(() -> method2()).start();
    }
}

//    method2(): 3268 мсек.
//    method1(): 8995 мсек.
