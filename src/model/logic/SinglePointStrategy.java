package model.logic;

import java.util.ArrayList;
import model.agent.Agent;
import model.board.Coord;
import model.board.View;

/**
 * Provide methods for the single point strategy on a view.
 * */
public class SinglePointStrategy {

    private final View view;
    private final Agent agent;

    public SinglePointStrategy(Agent agent) {
        this.agent = agent;
        this.view = agent.getView();
    }

    /**
     * Check the currently unknown cells of the agent's view using the single point strategy.
     * If any changes were made, return true, else false.
     * */
    public boolean checkCurrentUnknownCells() {
        boolean changed = false;

        ArrayList<Coord> unknownCells = this.view.getUnknownCells();
        for (Coord cell : unknownCells) {
            // perform the AFN check on the cell and probe if successful
            if (afnCheck(cell)) {
                this.agent.probe(cell);
                changed = true;
            }

            // perform the AMN check on the cell and flag as danger if successful
            else if (amnCheck(cell)) {
                this.view.flagDanger(cell);
                changed = true;
            }

            this.agent.printIteration();
        }

        return changed;
    }

    /**
     * Run an AFN check on a cell by determining the AFN condition of all its neighbors.
     * */
    public boolean afnCheck(Coord cell) {
        ArrayList<Coord> neighbors = view.getAdjacentCoords(cell);
        for (Coord neighbor : neighbors) {
            if (afn(neighbor)) return true;
        }
        return false;
    }

    /**
     * Return if the cell satisfies the AFN condition.
     * The AFN condition is satisfied if the hint of the cell is equal to the number of dangers adjacent to the cell.
     * */
    public boolean afn(Coord cell) {
        if (view.isUnknown(cell)) return false;
        int numericValue = Character.getNumericValue(view.getCell(cell));
        ArrayList<Coord> neighbors = view.getAdjacentCoords(cell);
        return numericValue == view.countDangers(neighbors);
    }

    /**
     * Run an AMN check on a cell by determining the AMN condition of all its neighbors.
     * */
    public boolean amnCheck(Coord cell) {
        ArrayList<Coord> neighbors = view.getAdjacentCoords(cell);
        for (Coord neighbor : neighbors) {
            if (amn(neighbor)) return true;
        }
        return false;
    }

    /**
     * Return if the cell satisfies the AMN condition.
     * The AMN condition is satisfied if the number of unknowns adjacent to the cell is equal to the hint
     * of the cell minus the dangers adjacent to the cell.
     * */
    public boolean amn(Coord cell) {
        if (view.isUnknown(cell)) return false;
        ArrayList<Coord> neighbors = view.getAdjacentCoords(cell);
        int unknownCount = view.countUnknowns(neighbors);
        int dangerCount = view.countDangers(neighbors);
        int numericValue = Character.getNumericValue(view.getCell(cell));
        return unknownCount == numericValue - dangerCount;
    }
}
