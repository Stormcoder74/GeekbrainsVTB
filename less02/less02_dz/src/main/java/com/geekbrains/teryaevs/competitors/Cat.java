package com.geekbrains.teryaevs.competitors;

import com.geekbrains.teryaevs.obstacles.Obstacle;

public class Cat implements Competitor {
    private CompetitorModule module;

    public Cat(String name) {
        this(name, 100, 5);
    }

    public Cat(String name, int runLimit, int jumpLimit) {
        module = new CompetitorModule(name, runLimit, jumpLimit);
    }

    @Override
    public void perform(Obstacle obstacle) {
        module.perform(obstacle);
    }
}
