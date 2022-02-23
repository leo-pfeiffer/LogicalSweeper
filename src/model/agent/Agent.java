package model.agent;

import delegate.Game;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.View;

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

    public void printIteration() {
        this.game.printIteration();
    }

    public void probe(Coord coord) {
        this.game.probe(coord);
    }

    /**
     * Perform the initial safe probes of top left and center cells.
     * */
    protected void initialSafeProbes() {
        Coord topLeft = new Coord(0, 0);
        Coord center = new Coord(view.getSize() / 2, view.getSize() / 2);
        game.probe(topLeft);
        this.game.printIteration();
        game.probe(center);
        this.game.printIteration();
    }
}
