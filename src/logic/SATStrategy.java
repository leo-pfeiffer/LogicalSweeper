package logic;

import model.agent.Agent;
import model.board.Coord;

public class SATStrategy extends Strategy {

    private final EntailmentChecker checker;

    public SATStrategy(Agent agent) {
        super(agent);
        KnowledgeBase kb = new KnowledgeBase(agent.getView());
        checker = new EntailmentChecker(kb);
    }

    @Override
    public boolean check(Coord cell) {

        String literalString = coordToLiteralString(cell);

        // perform the AFN check on the cell and probe if successful
        if (!checker.checkEntailment(literalString, true)) {
            this.agent.probe(cell);
            return true;
        }

        // perform the AMN check on the cell and flag as danger if successful
        else if (!checker.checkEntailment(literalString, false)) {
            this.view.flagDanger(cell);
            return true;
        }

        return false;
    }

    public String coordToLiteralString(Coord coord) {
        return "D_" + coord.getRow() + "_" + coord.getCol();
    }
}
