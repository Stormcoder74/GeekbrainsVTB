package com.geekbrains.teryaevs.competitors;

import com.geekbrains.teryaevs.obstacles.Obstacle;
import com.geekbrains.teryaevs.obstacles.Track;
import com.geekbrains.teryaevs.obstacles.Wall;

class CompetitorModule {
    private String name;
    private int runLimit;
    private int jumpLimit;

    private boolean nextStage = true;

    CompetitorModule(String name, int runLimit, int jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    void perform(Obstacle obstacle) {
        switch (obstacle.getTask().getType()){
            case RUN: run((Track) obstacle);
                break;
            case JUMP: jump((Wall) obstacle);
        }

    }

    private void jump(Wall wall) {
        if (nextStage) {
            int height = wall.getTask().getValue();
            String trouble = wall.trouble();
            if (height <= jumpLimit)
                System.out.println(name + " перепрыгнул стену" +
                        trouble + " высотой " + height + " м");
            else {
                System.out.println(name + " не смог перепрыгнуть стену" +
                        trouble + " высотой " + height + " м");
                nextStage = false;
            }
        }
    }

    private void run(Track track) {
        if (nextStage) {
            int length = track.getTask().getValue();
            if (length <= runLimit)
                System.out.println(name + " пробежал дистанцию" +
                        track.trouble() + " длинной " + length + " м");
            else {
                System.out.println(name + " не смог пробежать дистанцию" +
                        track.trouble() + " длинной " + length + " м");
                nextStage = false;
            }
        }
    }
}
