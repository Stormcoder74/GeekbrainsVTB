package com.geekbrains.teryaevs.statsdemo;

public class Stats<T extends Number> {
    private T[] nums;

    public Stats(T... nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue(); // Ошибка
        }
        return sum / nums.length;
    }

    public boolean sameAvg(Stats<? extends Number> another){
        return Math.abs(avg() - another.avg()) < 0.0001;

    }
}
