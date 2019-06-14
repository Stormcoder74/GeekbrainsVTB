package com.geekbrains.teryaevs.controllers;

public class Cat {
    long id;
    String name;
    String color;

    public Cat(long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Cat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
