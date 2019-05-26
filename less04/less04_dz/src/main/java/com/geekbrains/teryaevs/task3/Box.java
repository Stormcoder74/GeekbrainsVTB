package com.geekbrains.teryaevs.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> box;

    public Box() {
        box = new ArrayList<>();
    }

    public Box(T... array) {
        this();
        Collections.addAll(box, array);
    }

    public float getWeight(){
        if (box.size() == 0)
            return 0;

        return box.size() * box.get(0).getWeight();
    }

    public boolean add(T element){
        return box.add(element);
    }

    public boolean compare(Box<? extends Fruit> another){
        return getWeight() - another.getWeight() < 0.0001;
    }

    public void pourIn(Box<T> another){
        for (int i = box.size() - 1; i >= 0; i--)
            another.add(box.remove(i));
    }
}
