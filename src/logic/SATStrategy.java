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

        // KB |= Danger
        if (!kb.checkEntailment(cell, true)) {
            this.agent.probe(cell);
            return true;
        }

        // KB |= !Danger
        else if (!kb.checkEntailment(cell, false)) {
            this.view.flagDanger(cell);
            return true;
        }

        return false;
    }
}
