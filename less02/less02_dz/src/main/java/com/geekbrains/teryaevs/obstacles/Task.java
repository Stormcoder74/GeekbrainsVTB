package com.geekbrains.teryaevs.obstacles;

public enum Task {
    CROSS3000(Type.RUN, 3000),
    CROSS5000(Type.RUN, 5000),
    SPRINT100(Type.RUN, 100),
    WALL2(Type.JUMP, 2),
    WALL3(Type.JUMP, 3),
    WALL5(Type.JUMP, 5);

    private Type type;
    private int value;

    Task(Type type, int value) {
        this.type = type;
        if (value > 0) {
            this.value = value;
        } else
            try {
                throw new NumberFormatException("Not valid value. It must be great than zero.");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                this.value = Integer.MAX_VALUE;
            }
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}

