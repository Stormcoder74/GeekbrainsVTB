package com.geekbrains.teryaevs.task3;

public abstract class Fruit {
    protected String type;
    protected float weight;

    public Fruit(String type, float weight) {
        this.type = type;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public float getWeight() {
        return weight;
    }
}
