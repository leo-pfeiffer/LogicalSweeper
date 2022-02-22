package model.agent;

import java.util.ArrayList;
import delegate.Game;
import model.board.Coord;
import model.board.View;

/**
 * BeginnerAgent using Single Point Strategy
 * */
public class BeginnerAgent extends Agent {

    public BeginnerAgent(Game game, View view) {
        super(game, view);
    }

    @Override
    public void playGame() throws NothingToProbeException, MineFoundException {

        // initial safe probes
        Coord topLeft = new Coord(0, 0);
        Coord center = new Coord(view.getSize() / 2, view.getSize() / 2);
        game.probe(topLeft);
        this.game.printIteration();
        game.probe(center);
        this.game.printIteration();

        // initialise boolean to mark if any changes occurred
        boolean changed = true;

        // keep searching until all cells are uncovered or early termination
        while (view.getUnknownCells().size() > 0 && changed) {

            changed = false;

            ArrayList<Coord> unknownCells = view.getUnknownCells();
            for (Coord cell : unknownCells) {
                // perform the AFN check on the cell and probe if successful
                if (afnCheck(cell)) {
                    game.probe(cell);
                    changed = true;
                }

                // perform the AMN check on the cell and flag as danger if successful
                else if (amnCheck(cell)) {
                    view.flagDanger(cell);
                    changed = true;
                }

                this.game.printIteration();
            }
        }
        if (!changed && view.getUnknownCells().size() > 0) {
            this.canTerminate = false;
            throw new NothingToProbeException("No more cells to probe");
        }
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
