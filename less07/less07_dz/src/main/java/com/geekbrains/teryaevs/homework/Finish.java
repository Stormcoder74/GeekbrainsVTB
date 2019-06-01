package com.geekbrains.teryaevs.homework;

import java.util.concurrent.CountDownLatch;

public class Finish {
    private Car winner;
    private CountDownLatch finishLine;

    public Finish(CountDownLatch finishLine) {
        this.finishLine = finishLine;
    }

    public void makeFinish(Car winner){
        if (this.winner == null) {
            finishLine.countDown();
            this.winner = winner;
        }
    }

    public Car getWinner() {
        return winner;
    }

    public CountDownLatch getFinishLine() {
        return finishLine;
    }
}
