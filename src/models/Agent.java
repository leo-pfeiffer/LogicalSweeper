package models;

public abstract class Agent {

    protected final View view;

    public Agent(View view) {
        this.view = view;
    }

    public abstract Coord probe();

    public void uncover(Coord coord, char value) {
        this.view.uncover(coord, value);
    }

    public boolean hasDied() {
        return this.view.uncoveredMine();
    }

    public boolean hasWon() {
        return this.view.allUncovered();
    }
}
