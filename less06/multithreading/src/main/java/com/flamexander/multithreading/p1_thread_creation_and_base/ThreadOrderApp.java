package com.flamexander.multithreading.p1_thread_creation_and_base;

public class ThreadOrderApp {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                }
            });
            thread1.start();

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(2);
                }
            });
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
