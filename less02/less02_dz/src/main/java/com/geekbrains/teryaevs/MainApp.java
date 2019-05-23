package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.competitors.Bot;
import com.geekbrains.teryaevs.competitors.Cat;
import com.geekbrains.teryaevs.competitors.Competitor;
import com.geekbrains.teryaevs.competitors.Human;
import com.geekbrains.teryaevs.obstacles.*;

import javax.management.BadAttributeValueExpException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    static final int stageAmount = 3;

    public static void main(String[] args) throws BadAttributeValueExpException {

        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new MudTrack(Task.SPRINT100));
        obstacles.add(new MudTrack(Task.CROSS5000));
        obstacles.add(new SimpleCross3000());
        obstacles.add(new FireWall(Task.WALL2));
        obstacles.add(new FireWall(Task.WALL3));
        obstacles.add(new FireWall(Task.WALL5));

        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Human("Иван"));
        competitors.add(new Cat("Мурзик"));
        competitors.add(new Bot("Bot-10000"));
        competitors.add(new Bot("Superbot-10000-J10", 10000, 10));

        for (Competitor c : competitors) {
            for (int i = 0; i < stageAmount; i++){
                c.perform(obstacles.get((int) (Math.random() * obstacles.size())));
            }
        }
    }
}
