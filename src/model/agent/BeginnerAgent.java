package model.agent;

import java.util.ArrayList;
import delegate.Game;
import model.board.Coord;
import model.board.View;
import model.logic.SinglePointStrategy;

/**
 * BeginnerAgent using Single Point Strategy
 * */
public class BeginnerAgent extends Agent {

    private final SinglePointStrategy sps;

    public BeginnerAgent(Game game, View view) {
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
        }

        if (!changed && view.getUnknownCells().size() > 0) {
            this.canTerminate = false;
            throw new NothingToProbeException("No more cells to probe");
        }
    }
}
