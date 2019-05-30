package com.geekbrains.teryaevs;

import java.util.Arrays;

public class MainApp {
    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;

    static class MyThread implements Runnable {
        float[] arr;
        int offset;

        MyThread(float[] arr, int offset) {
            this.arr = arr;
            this.offset = offset;
        }

        @Override
        public void run() {
            for (int i = 0; i < HALF; i++) {
                int iOff = i + offset;
                arr[i] = (float) (arr[i] * Math.sin(0.2f + iOff / 5) *
                        Math.cos(0.2f + iOff / 5) * Math.cos(0.4f + iOff / 2));
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

        float[] half1 = new float[HALF];
        float[] half2 = new float[HALF];
        System.arraycopy(array, 0, half1, 0, HALF);
        System.arraycopy(array, HALF, half2, 0, HALF);

        Thread thread1 = new Thread(new MyThread(half1, 0));
        Thread thread2 = new Thread(new MyThread(half2, HALF));
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
        return array;
    }

    public static void main(String[] args) {
        float[] arr1 = method1();
        float[] arr2 = method2();
        for (int i = 0; i < SIZE; i++) {
            if (Math.abs(arr1[i] - arr2[i]) > 0.0001) {
                System.out.println("массивы не эквивалентны");
                return;
            }
        }
        System.out.println("массивы эквивалентны");
    }
}

//  method1(): 8972 мсек.
//  method2(): 5966 мсек.       8972/5966 ~ 1,5
//  массивы эквивалентны