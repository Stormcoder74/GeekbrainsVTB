package com.geekbrains.teryaevs.metodichka;

public class Test {
    private int count;
    private String name;

    public Test(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }
}
