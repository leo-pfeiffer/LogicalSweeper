package logic;

import model.agent.Agent;
import model.board.Coord;

public class SATStrategy extends Strategy {

    private final KnowledgeBase kb;

    public SATStrategy(Agent agent, KnowledgeBase kb) {
        super(agent);
        this.kb = kb;
    }

    @Override
    public boolean check(Coord cell) {

        // perform the AFN check on the cell and probe if successful
        if (!kb.checkEntailment(cell, true)) {
            this.agent.probe(cell);
            return true;
        }

        // perform the AMN check on the cell and flag as danger if successful
        else if (!kb.checkEntailment(cell, false)) {
            this.view.flagDanger(cell);
            return true;
        }

        return false;
    }
}
