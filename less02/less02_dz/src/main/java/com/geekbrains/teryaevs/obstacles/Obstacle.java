package com.geekbrains.teryaevs.obstacles;

public interface Obstacle {
    Task getTask();

    default String trouble(){
        return "";
    }
}
