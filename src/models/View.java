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

    public void uncover(Coord coord, char value) {
        this.view[coord.getRow()][coord.getCol()] = value;
    }

    public int getSize() {
        return this.view.length;
    }

    public char getCell(Coord coord) {
        assert coord.getCol() < this.getSize();
        assert coord.getRow() < this.getSize();
        return this.view[coord.getRow()][coord.getCol()];
    }

    public int getMineCount() {
        return mineCount;
    }

    public boolean allUncovered() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.getCell(new Coord(i, j)) == '?') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean uncoveredMine() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.getCell(new Coord(i, j)) == 'm') {
                    return true;
                }
            }
        }
        return false;
    }

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
