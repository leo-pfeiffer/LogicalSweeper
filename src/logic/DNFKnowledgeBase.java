package logic;

import java.util.ArrayList;
import model.board.Coord;
import model.board.View;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;

public class DNFKnowledgeBase extends KnowledgeBase {

    private final FormulaFactory f = new FormulaFactory();
    private final DNFEncoder encoder = new DNFEncoder(f);

    public DNFKnowledgeBase(View view) {
        super(view);
    }

    private Formula getKBU() {
        ArrayList<Coord> contenders = this.getKBUContenders();

        ArrayList<Formula> formulae = new ArrayList<>();

        for (Coord coord : contenders) {

            ArrayList<Coord> unknownNeighbors = this.getUnknownNeighbors(coord);
            String[] literals = this.stringifyLiterals(unknownNeighbors);
            int clue = Character.getNumericValue(this.view.getCell(coord));
            int numDangers = this.view.countDangers(this.view.getAdjacentCoords(coord));

            formulae.add(encoder.encode(literals, clue - numDangers));
        }

        return f.and(formulae);
    }

    private String[] stringifyLiterals(ArrayList<Coord> cells) {
        String[] literals = new String[cells.size()];
        for (int i = 0; i < cells.size(); i++) {
            Coord coord = cells.get(i);
            literals[i] = "D_" + coord.getRow() + "_" + coord.getCol();
        }
        return literals;
    }

    public boolean checkEntailment(Coord cell, boolean phase) {
        String literal = coordToLiteralString(cell);
        return checkEntailment(f.literal(literal, phase));
    }

    private boolean checkEntailment(Literal literal) {
        Formula entailment = f.and(getKBU(), literal);

        SATSolver miniSat = MiniSat.miniSat(f);
        miniSat.add(entailment);
        return miniSat.sat().ordinal() == 0;
    }

    private String coordToLiteralString(Coord coord) {
        return "D_" + coord.getRow() + "_" + coord.getCol();
    }
}
