package model;

/**
 * Tracker class to keep track of performance metrics.
 * */
public class Tracker {

    private boolean terminated = true;
    private boolean alive = true;
    private double percentageRemaining = 1;
    private int numIterations = 0;

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
        this.terminated = false;
    }

    public void setPercentageRemaining(double percentageRemaining) {
        this.percentageRemaining = percentageRemaining;
    }
}
