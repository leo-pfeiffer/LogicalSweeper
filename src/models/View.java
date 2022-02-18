package models;

public class View {

    private final char[][] view;
    private final int mineCount;

    public View(char[][] view, int mineCount) {
        this.view = view;
        this.mineCount = mineCount;
    }

    public char[][] getView() {
        return view;
    }

    /**
     * Returns true if the given coord is in the view.
     * */
    public boolean isInView(Coord coord) {
        int row = coord.getRow();
        int col = coord.getCol();
        return col >= 0 && col < this.getSize() && row >= 0 && row < this.getSize();
    }

    /**
     * Uncover the field of the given coord.
     * @param coord The coord of the field to be uncovered.
     * @param value The value of the field to be uncovered.
     * */
    public void uncover(Coord coord, char value) {
        if (isInView(coord)) {
            this.view[coord.getRow()][coord.getCol()] = value;
        }
    }

    /**
     * Get the size of the view.
     * */
    public int getSize() {
        return this.view.length;
    }

    /**
     * Returns the value of the cell of the given coord from the view.
     * */
    public char getCell(Coord coord) {
        if (!isInView(coord)) throw new IllegalArgumentException("Coord not in view");
        return this.view[coord.getRow()][coord.getCol()];
    }

    /**
     * Returns true if the cell of the given coord is uncovered.
     * */
    public boolean isUncovered(Coord coord) {
        if (!isInView(coord)) throw new IllegalArgumentException("Coord not in view");
        return this.view[coord.getRow()][coord.getCol()] != '?';
    }

    public int getMineCount() {
        return mineCount;
    }

    /**
     * Returns true if a mine has been uncovered and false otherwise.
     * */
    public boolean uncoveredMine() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.getCell(new Coord(i, j)) == '-') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return the number of uncovered cells in the view.
     * */
    public int getUncoveredCount() {
        int count = 0;
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.isUncovered(new Coord(i, j))) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof View)) {
            return false;
        }

        View other = (View) o;
        if (this.getSize() != other.getSize()) {
            return false;
        }
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.getCell(new Coord(i, j)) != other.getCell(new Coord(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                result = 31 * result + this.getCell(new Coord(i, j));
            }
        }
        return result;
    }
}
