package logic;

import java.util.ArrayList;
import model.board.Coord;
import model.board.Token;
import model.board.View;

public abstract class KnowledgeBase {
    protected final View view;

    public KnowledgeBase(View view) {
        this.view = view;
    }

    /**
     * Check if the current knowledge base entails a variable, i.e. check if
     * KB |= cell
     * @param cell The cell whose entailment to check
     * @param phase True if the positive of the cell should be used, False otherwise
     * */
    public abstract boolean checkEntailment(Coord cell, boolean phase);

    /**
     * Return an ArrayList of KBU contenders.
     * KBU contenders are cells that are clues (uncovered) and have at least one adjacent unknown cell.
     * */
    protected ArrayList<Coord> getKBUContenders() {
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

    /**
     * Return an array list of all those neighbors of a cell that are still unknown (uncovered + unmarked).
     * */
    protected ArrayList<Coord> getUnknownNeighbors(Coord coord) {
        ArrayList<Coord> neighbors = this.view.getAdjacentCoords(coord);
        ArrayList<Coord> unknownNeighbors = new ArrayList<>();

        for (Coord neighbor : neighbors) {
            if (this.view.isUnknown(neighbor)) {
                unknownNeighbors.add(neighbor);
            }
        }
        return unknownNeighbors;
    }

    /**
     * Get the number of mines in the unknown cells adjacent to coord.
     * */
    protected int getAdjacentRemainingDangerCount(Coord coord) {
        int clue = Character.getNumericValue(this.view.getCell(coord));
        int numDangers = this.view.countDangers(this.view.getAdjacentCoords(coord));
        return clue - numDangers;
    }
}
