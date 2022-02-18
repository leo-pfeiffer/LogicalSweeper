package models;

public class BasicAgent extends Agent {

    public BasicAgent(View view) {
        super(view);
    }

    private Coord lastProbedCell;
    private boolean hasWon = false;

    @Override
    public Coord probe() {
        try {
            Coord nextCoord = this.getNextCoordInOrder();
            this.lastProbedCell = nextCoord;
            return nextCoord;
        } catch (NothingToProbeException e) {
            this.hasWon = true;
            throw e;
        }
    }

    @Override
    public boolean hasWon() {
        return this.hasWon;
    }

    private Coord getNextCoordInOrder() {
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
