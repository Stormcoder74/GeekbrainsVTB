package com.geekbrains.teryaevs.obstacles;

import javax.management.BadAttributeValueExpException;

public class MudTrack implements Track {
    private static final String trouble = " по ужасной грязи";
    private Task task;

    public MudTrack(Task task) throws BadAttributeValueExpException {
        if (task.getType() != Type.RUN)
            throw new BadAttributeValueExpException("Track task type must be RUN");
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
