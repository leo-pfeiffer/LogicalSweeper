package models;

public abstract class Agent {

    protected final View view;

    public Agent(View view) {
        this.view = view;
    }

    public abstract Coord probe() throws NothingToProbeException;

    public void uncover(Coord coord, char value) {
        this.view.uncover(coord, value);
    }

    public boolean hasDied() {
        return this.view.uncoveredMine();
    }

    public abstract boolean hasWon();
}
