package com.flamexander.multithreading.p3_synchronization.double_counter;

public class SynchCounterApp {
    public static void main(String[] args) {
        SynchDoubleCounter synchDoubleCounter = new SynchDoubleCounter();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchDoubleCounter.inc1();
                System.out.println("sdc.value1: " + synchDoubleCounter.value1());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchDoubleCounter.dec1();
                System.out.println("sdc.value1: " + synchDoubleCounter.value1());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchDoubleCounter.inc2();
                System.out.println("sdc.value2: " + synchDoubleCounter.value2());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchDoubleCounter.dec2();
                System.out.println("sdc.value2: " + synchDoubleCounter.value2());
            }
        }).start();
    }
}
