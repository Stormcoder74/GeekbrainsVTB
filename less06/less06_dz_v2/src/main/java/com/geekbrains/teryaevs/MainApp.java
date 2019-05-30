package com.geekbrains.teryaevs;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {
    private static final int NUM_THREADS = 113;
    private static final int SIZE = 14_654_737;

    private static final int PART = SIZE / NUM_THREADS * NUM_THREADS == SIZE
                ? SIZE / NUM_THREADS : SIZE / NUM_THREADS + 1;

    static class MyThread implements Runnable {
        float[] arr;
        int offset;

        MyThread(float[] arr, int offset) {
            this.arr = arr;
            this.offset = offset;
        }

        @Override
        public void run() {
            int end = Math.min(offset + PART, SIZE);
            for (int i = offset; i < end; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    private static float[] method1() {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++)
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        System.out.println("method1(): " + (System.currentTimeMillis() - startTime) + " мсек.");
        return array;
    }

    private static float[] method2() {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1.0f);

        long startTime = System.currentTimeMillis();

        ArrayList<Thread> threads = new ArrayList<>(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new MyThread(array, PART * i));
            thread.start();
            threads.add(thread);
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2(): " + (System.currentTimeMillis() - startTime) + " мсек.");
        return array;
    }

    public static void main(String[] args) {
        float[] arr1 = method1();
        float[] arr2 = method2();
        for (int i = 0; i < SIZE; i++) {
            if (Math.abs(arr1[i] - arr2[i]) > 0.0001) {
                System.out.println("массивы не эквивалентны " + i);
                return;
            }
        }
        System.out.println("массивы эквивалентны");
    }
}

//      method1(): 11816 мсек.
//      method2(): 2099 мсек.    11816/2099 ~ 5,6
//      массивы эквивалентны