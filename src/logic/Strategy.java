package logic;

import model.agent.Agent;
import model.board.Coord;
import model.board.View;

public abstract class Strategy {

    protected final View view;
    protected final Agent agent;

    public Strategy(Agent agent) {
        this.agent = agent;
        this.view = agent.getView();
    }

    public abstract boolean check(Coord cell);
}
