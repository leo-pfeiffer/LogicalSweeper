package model.agent;

import java.util.ArrayList;
import delegate.ObscuredSweeper;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.View;
import logic.SinglePointStrategy;

/**
 * BeginnerAgent uses
 * - Single Point Strategy
 * */
public class SpsAgent extends Agent {

    private final SinglePointStrategy sps;

    public SpsAgent(ObscuredSweeper game, View view) {
        super(game, view);
        sps = new SinglePointStrategy(this);
    }

    @Override
    public void playGame() throws NothingToProbeException, MineFoundException {

        // initial safe probes
        this.initialSafeProbes();

        // initialise boolean to mark if any changes occurred
        boolean changed = true;

        // keep searching until all cells are uncovered or early termination
        while (view.getUnknownCells().size() > 0 && changed) {

            changed = false;

            // changed = sps.checkUnknownCells();

            ArrayList<Coord> unknownCells = this.view.getUnknownCells();
            for (Coord cell : unknownCells) {
                changed = sps.check(cell) || changed;
            }

            this.printIteration();
            this.markIterationEnd();
        }

        if (!changed && view.getUnknownCells().size() > 0) {
            this.canTerminate = false;
            throw new NothingToProbeException("No more cells to probe");
        }
    }
}
