package com.geekbrains.teryaevs.competitors;

import com.geekbrains.teryaevs.obstacles.Obstacle;

public class Human implements Competitor {
    private CompetitorModule module;

    public Human(String name) {
        this(name, 3000, 3);
    }

    public Human(String name, int runLimit, int jumpLimit) {
        module = new CompetitorModule(name, runLimit, jumpLimit);
    }

    @Override
    public void perform(Obstacle obstacle) {
        module.perform(obstacle);
    }
}
