package com.flamexander.multithreading.p3_synchronization;

public class SynchStaticMethodApp {
    static int counter = 0;

    public static void main(String[] args) {
        SynchStaticMethodApp e = new SynchStaticMethodApp();
        Thread thread1 = new Thread(() -> classMethod());
//        Thread thread2 = new Thread(() -> classMethod());
        Thread thread3 = new Thread(() -> e.objectMethod());

        try {
            thread1.start();
//            thread2.start();
            thread3.start();
            thread1.join();
//            thread2.join();
            thread3.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("counter: " + counter);
    }

    public synchronized static void classMethod() {
        System.out.println("Synch Static Method Start " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Synch Static Method End " + Thread.currentThread().getName());
    }

    public synchronized void objectMethod() {
        System.out.println("Synch Method Start " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                counter--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Synch Method End " + Thread.currentThread().getName());
    }
}
