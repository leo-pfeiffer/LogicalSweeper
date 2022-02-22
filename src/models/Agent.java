package models;

import delegate.Game;

public abstract class Agent {

    protected final View view;
    protected final Game game;
    protected boolean canTerminate = true;

    public Agent(Game game, View view) {
        this.view = view;
        this.game = game;
    }

    public View getView() {
        return this.view;
    }

    public abstract void playGame() throws NothingToProbeException, MineFoundException;

    public void uncover(Coord coord, char value) {
        this.view.uncover(coord, value);
    }

    public int getUncoveredCount() {
        return this.view.getUncoveredCount();
    }

    public int getMineCount() {
        return this.view.getMineCount();
    }

    public boolean isCanTerminate() {
        return canTerminate;
    }
}
