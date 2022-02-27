package logic;

import model.agent.Agent;
import model.board.Coord;
import model.board.View;

/**
 * Abstract class defining behaviour for different strategies.
 * */
public abstract class Strategy {

    protected final View view;
    protected final Agent agent;

    public Strategy(Agent agent) {
        this.agent = agent;
        this.view = agent.getView();
    }

    /**
     * Method to check a cell with using the concrete strategy.
     * @param cell The cell to check.
     * */
    public abstract boolean check(Coord cell);
}
