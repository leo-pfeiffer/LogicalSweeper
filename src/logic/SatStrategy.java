package logic;

import model.agent.Agent;
import model.board.Coord;

/**
 * Class representing the SAT Strategy.
 * */
public class SatStrategy extends Strategy {

    private final KnowledgeBase kb;

    public SatStrategy(Agent agent, KnowledgeBase kb) {
        super(agent);
        this.kb = kb;
    }

    @Override
    public boolean check(Coord cell) {

        // check if KB |= Danger
        if (!kb.checkEntailment(cell, true)) {
            this.agent.probe(cell);
            return true;
        }

        // check if KB |= !Danger
        else if (!kb.checkEntailment(cell, false)) {
            this.view.flagDanger(cell);
            return true;
        }

        return false;
    }
}
