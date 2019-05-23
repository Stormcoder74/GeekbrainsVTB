package com.geekbrains.teryaevs.obstacles;

import javax.management.BadAttributeValueExpException;

public class FireWall implements Wall {
    private static final String trouble = " в адском огне";
    private Task task;

    public FireWall(Task task) throws BadAttributeValueExpException {
        if (task.getType() != Type.JUMP)
            throw new BadAttributeValueExpException("Wall task type must be JUMP");
        this.task = task;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public String trouble() {
        return trouble;
    }
}
