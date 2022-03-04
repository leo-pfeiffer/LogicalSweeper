package model.agent;

import java.util.ArrayList;
import delegate.ObscuredSweeper;
import logic.KnowledgeBase;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.View;
import logic.SatStrategy;
import logic.SinglePointStrategy;

/**
 * An intermediate agent uses
 * - Single Point Strategy
 * - SAT Strategy
 *
 * The type of encoding for the SAT Strategy depends on the KnowledgeBase provided
 * */
public class SpsSatAgent extends Agent {

    private final SinglePointStrategy sps;
    private final SatStrategy sat;

    public SpsSatAgent(ObscuredSweeper game, View view, KnowledgeBase kb) {
        super(game, view);
        sps = new SinglePointStrategy(this);
        sat = new SatStrategy(this, kb);
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
                if (sps.check(cell)) {
                    changed = true;
                } else if (sat.check(cell)) {
                    changed = true;
                }
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
