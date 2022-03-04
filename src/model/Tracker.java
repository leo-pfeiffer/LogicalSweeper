package model;

/**
 * Tracker class to keep track of performance metrics.
 * */
public class Tracker {

    // flag to indicate whether the agent has terminated.
    private boolean terminated = true;

    // flag to indicate whether the agent is alive.
    private boolean alive = true;

    // percentage of uncovered fields remaining.
    private double percentageRemaining = 1;

    // counter to keep track of the number of iterations.
    private int numIterations = 0;

    // run time
    private long runTime = 0;

    private long startTime;

    public boolean isTerminated() {
        return terminated;
    }

    public boolean isAlive() {
        return alive;
    }

    public double getPercentageRemaining() {
        return percentageRemaining;
    }

    public int getNumIterations() {
        return numIterations;
    }

    public void incrementNumIterations() {
        this.numIterations++;
    }

    public void setNotTerminated() {
        this.terminated = false;
    }

    public void setNotAlive() {
        this.alive = false;
    }

    public void setPercentageRemaining(double percentageRemaining) {
        this.percentageRemaining = percentageRemaining;
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.runTime = System.currentTimeMillis() - this.startTime;
    }

    public long getRunTime() {
        return this.runTime;
    }
}
