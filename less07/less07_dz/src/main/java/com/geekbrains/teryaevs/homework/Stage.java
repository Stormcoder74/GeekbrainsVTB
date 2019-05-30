package com.geekbrains.teryaevs.homework;

public abstract class Stage {
    protected int length;
    protected String description;
    protected Finish finish;

    public String getDescription() {
        return description;
    }

    public Finish getFinish() {
        return finish;
    }

    public abstract void go(Car c);
}
