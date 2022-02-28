package model.agent;

import delegate.ObscuredSweeper;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.View;

/*
* BasicAgent naively probing cells in order.
**/
public class BasicAgent extends Agent {

    public BasicAgent(ObscuredSweeper game, View view) {
        super(game, view);
    }

    private Coord lastProbedCell;

    public Coord probeNext() {
        Coord cell = this.getNextCoordInOrder();
        this.lastProbedCell = cell;

        // ask game to probe the cell
        this.game.probe(cell);
        return cell;
    }

    @Override
    public void playGame() throws NothingToProbeException {
        // ask the game if the game is still going on (not game over)
        while (this.game.isPlaying()) {
            this.probeNext();
            this.game.printIteration();
            this.markIterationEnd();
        }
    }

    /**
     * Return the next coordinate to probe.
     * */
    public Coord getNextCoordInOrder() {
        int row = 0;
        int col = 0;

        // if we have probed a cell before, move the row and col to the next cell
        if (lastProbedCell != null) {

            // move one cell to the right
            col = lastProbedCell.getCol() + 1;
            row = lastProbedCell.getRow();

            // if we're beyond the board with, go to first cell of next row
            if (col > this.view.getSize() - 1) {
                row++;
                col = 0;
            }
        }

        // look for the next cell that is not yet probed and return it
        while (row < this.view.getSize()) {
            while (col < this.view.getSize()) {
                Coord newCoord = new Coord(row, col);
                if (this.view.isUncovered(newCoord)) col++;
                else return newCoord;
            }
            col = 0;
            row++;
        }

        // there are no more cells to probe
        throw new NothingToProbeException("No more cells to probe.");
    }
}
