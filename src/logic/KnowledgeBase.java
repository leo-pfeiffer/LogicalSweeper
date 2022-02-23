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


}
