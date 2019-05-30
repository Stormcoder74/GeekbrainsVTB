package com.geekbrains.teryaevs.metodichka;

import java.util.concurrent.*;

public class ExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future future1 = executorService.submit(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Асинхронная задача 1");
            }
        });
        Future future2 = executorService.submit(new Callable<Test>() {
            public Test call() {
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Асинхронная задача 2");
                return new Test(5, "Иванов");
            }
        });
        executorService.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Асинхронная задача 3");
            }
        });
        executorService.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Асинхронная задача 4");
            }
        });
        executorService.shutdown();

        System.out.println(future1.get());
        Test test = (Test) future2.get();
        System.out.println(test);
    }
}
