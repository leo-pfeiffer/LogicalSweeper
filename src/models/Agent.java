package models;

public abstract class Agent {

    protected final View view;

    public Agent(View view) {
        this.view = view;
    }

    public Coord getNextProbeCoord() {
        throw new RuntimeException("Not implemented");
    }

    public void probe(Coord coord) {
        throw new RuntimeException("Not implemented");
    }

    public boolean hasDied() {
        return this.view.uncoveredMine();
    }

    public boolean hasWon() {
        return this.view.allUncovered();
    }
}
