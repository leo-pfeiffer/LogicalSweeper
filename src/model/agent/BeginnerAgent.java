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
        sps = new SinglePointStrategy(this.view);
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
                if (sps.afnCheck(cell)) {
                    game.probe(cell);
                    changed = true;
                }

                // perform the AMN check on the cell and flag as danger if successful
                else if (sps.amnCheck(cell)) {
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
}
