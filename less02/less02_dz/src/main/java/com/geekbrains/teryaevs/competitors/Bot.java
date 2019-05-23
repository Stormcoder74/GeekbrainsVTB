package com.geekbrains.teryaevs.competitors;

import com.geekbrains.teryaevs.obstacles.Obstacle;

public class Bot implements Competitor {
    private CompetitorModule module;

    public Bot(String name) {
        this(name, 10000, 1);
    }

    public Bot(String name, int runLimit, int jumpLimit) {
        module = new CompetitorModule(name, runLimit, jumpLimit);
    }

    @Override
    public void perform(Obstacle obstacle) {
        module.perform(obstacle);
    }
}
