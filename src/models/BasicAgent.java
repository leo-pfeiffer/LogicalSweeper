package models;

import delegate.Game;

public class BasicAgent extends Agent {

    public BasicAgent(Game game, View view) {
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
        }
    }

    public Coord getNextCoordInOrder() {
        int row = 0;
        int col = 0;

        if (lastProbedCell != null) {
            col = lastProbedCell.getCol() + 1;
            row = lastProbedCell.getRow();

            if (col > this.view.getSize() - 1) {
                row++;
                col = 0;
            }
        }

        while (row < this.view.getSize()) {
            while (col < this.view.getSize()) {
                Coord newCoord = new Coord(row, col);
                if (this.view.isUncovered(newCoord)) col++;
                else return newCoord;
            }
            col = 0;
            row++;
        }

        throw new NothingToProbeException("No more cells to probe.");
    }
}
