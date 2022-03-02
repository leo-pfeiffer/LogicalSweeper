package model.agent;

import delegate.ObscuredSweeper;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.View;

/**
 * The Agent class is the base class for all agents. It provides the basic functionality
 * for the agent to interact with the game
 */
public abstract class Agent {

    protected final View view;
    protected final ObscuredSweeper game;
    protected boolean canTerminate = true;

    public Agent(ObscuredSweeper game, View view) {
        this.view = view;
        this.game = game;
    }

    /**
     * Returns the agent's view of the world
     *
     * @return The agent's view
     */
    public View getView() {
        return this.view;
    }

    /**
     * Play the game.
     */
    public abstract void playGame() throws NothingToProbeException, MineFoundException;

    /**
     * This function is responsible for uncovering the given coordinate on the view
     *
     * @param coord The coordinate of the cell to uncover.
     * @param value the value of the cell at the given coordinate.
     */
    public void uncover(Coord coord, char value) {
        this.view.uncover(coord, value);
    }

    /**
     * Returns the number of uncovered cells in the view
     *
     * @return The number of uncovered cells.
     */
    public int getUncoveredCount() {
        return this.view.getUncoveredCount();
    }

    /**
     * Returns the number of mines in the game
     *
     * @return The number of mines in the game.
     */
    public int getMineCount() {
        return this.view.getMineCount();
    }

    /**
     * Returns true if the agent can terminate the game.
     * This is true until the agent has nothing left to probe but has not won yet.
     *
     * @return True if (at this point) the agent can terminate, false otherwise.
     */
    public boolean isCanTerminate() {
        return canTerminate;
    }

    /**
     * Prints the current iteration of the game
     */
    public void printIteration() {
        this.game.printIteration();
    }

    /**
     * It probes the cell at the given coordinate.
     *
     * @param coord The coordinate of the cell to probe.
     */
    public void probe(Coord coord) {
        this.game.probe(coord);
    }

    /**
     * This function marks the end of an iteration.
     */
    public void markIterationEnd() {
        this.game.getTracker().incrementNumIterations();
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
