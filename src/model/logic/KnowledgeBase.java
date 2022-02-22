package model.logic;

import java.util.ArrayList;
import model.board.Coord;
import model.board.Token;
import model.board.View;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;

public class KnowledgeBase {

    private final FormulaFactory f = new FormulaFactory();
    private final DNFEncoder encoder = new DNFEncoder(f);

    private View view;

    public KnowledgeBase(View view) {
        this.view = view;
    }

    public FormulaFactory getF() {
        return f;
    }

    public void updateView(View view) {
        this.view = view;
    }

    public Formula getKBU() {
        ArrayList<Coord> contenders = this.getKBUContenders();

        ArrayList<Formula> formulae = new ArrayList<>();

        for (Coord coord : contenders) {

            ArrayList<Coord> unknownNeighbors = this.getUnknownNeighbors(coord);
            String[] literals = this.stringifyLiterals(unknownNeighbors);
            int clue = Character.getNumericValue(this.view.getCell(coord));

            formulae.add(encoder.encode(literals, clue));
        }

        return f.and(formulae);
    }

    /**
     * Return an ArrayList of KBU contenders.
     * KBU contenders are cells that are clues (uncovered) and have at least one adjacent unknown cell.
     * */
    private ArrayList<Coord> getKBUContenders() {
        ArrayList<Coord> uncovered = this.view.getUncoveredCells();
        ArrayList<Coord> contenders = new ArrayList<>();

        for (Coord coord : uncovered) {
            char cell = this.view.getCell(coord);
            if (Token.isClue(cell)) {
                boolean hasAdjacentCovered = this.view.countUnknowns(this.view.getAdjacentCoords(coord)) > 0;
                if (hasAdjacentCovered) {
                    contenders.add(coord);
                }
            }
        }

        return contenders;
    }

    private ArrayList<Coord> getUnknownNeighbors(Coord coord) {
        ArrayList<Coord> neighbors = this.view.getAdjacentCoords(coord);
        ArrayList<Coord> unknownNeighbors = new ArrayList<>();

        for (Coord neighbor : neighbors) {
            if (this.view.isUnknown(neighbor)) {
                unknownNeighbors.add(neighbor);
            }
        }

        return unknownNeighbors;
    }

    private String[] stringifyLiterals(ArrayList<Coord> cells) {
        String[] literals = new String[cells.size()];
        for (int i = 0; i < cells.size(); i++) {
            Coord coord = cells.get(i);
            literals[i] = "D_" + coord.getRow() + "_" + coord.getCol();
        }
        return literals;
    }
}
