package model.board;

import java.util.ArrayList;

/**
 * Agent's view of a world map.
 * */
public class View implements CharMap {

    private final char[][] map;
    private final int mineCount;
    private final GameBoardUtils gbu;

    public View(char[][] map, int mineCount, GameBoardUtils gbu) {
        this.map = map;
        this.mineCount = mineCount;
        this.gbu = gbu;
    }

    public char[][] getMap() {
        return map;
    }

    public int getMineCount() {
        return mineCount;
    }

    @Override
    public int getSize() {
        return this.map.length;
    }

    @Override
    public boolean containsCoord(Coord coord) {
        return gbu.containsCoord(coord);
    }

    @Override
    public char getCell(Coord coord) {
        return gbu.getCell(coord);
    }

    @Override
    public ArrayList<Coord> getAdjacentCoords(Coord coord) {
        return gbu.getAdjacentCoords(coord);
    }

    /**
     * Uncover the field of the given coord.
     * @param coord The coord of the field to be uncovered.
     * @param value The value of the field to be uncovered.
     * */
    public void uncover(Coord coord, char value) {
        if (containsCoord(coord)) {
            this.map[coord.getRow()][coord.getCol()] = value;
        }
    }

    /**
     * Flag a cell as containing a danger.
     * @param coord The coord of the cell to be flagged.
     * */
    public void flagDanger(Coord coord) {
        if (containsCoord(coord)) {
            this.map[coord.getRow()][coord.getCol()] = Token.DANGER.getChar();
        }
    }

    /**
     * Count the number of flagged dangers in an arraylist of cells.
     * */
    public int countDangers(ArrayList<Coord> cells) {
        return gbu.countOccurrence(Token.DANGER.getChar(), cells);
    }

    /**
     * Count the number of unknowns (uncovered & unmarked) in an arraylist of cells.
     * */
    public int countUnknowns(ArrayList<Coord> cells) {
        return gbu.countOccurrence(Token.UNKNOWN.getChar(), cells);
    }

    /**
     * Returns true if the cell of the given coord is uncovered.
     * */
    public boolean isUncovered(Coord coord) {
        return !this.isUnknown(coord);
    }

    /**
     * Returns true if the cell of the given coord is unmarked and covered.
     * */
    public boolean isUnknown(Coord coord) {
        return gbu.cellIsToken(Token.UNKNOWN.getChar(), coord);
    }

    /**
     * Returns true if a mine has been uncovered and false otherwise.
     * */
    public boolean hasProbedMine() {
        for (Coord c : gbu.getAllCoords()) {
            if (this.getCell(c) == Token.UNCOVERED_MINE.getChar()) return true;
        }
        return false;
    }

    /**
     * Return the number of uncovered cells in the view.
     * A cell counts as uncovered if it is NOT unknown.
     * */
    public int getUncoveredCount() {
        int count = 0;
        for (Coord c : gbu.getAllCoords()) {
            if (this.isUncovered(c)) count++;
        }
        return count;
    }

    /**
     * Return an arraylist of all the coordinates of the cells that are unmarked and covered
     * */
    public ArrayList<Coord> getUnknownCells() {
        ArrayList<Coord> coords = new ArrayList<>();
        for (Coord c : gbu.getAllCoords()) {
            if (this.isUnknown(c)) coords.add(c);
        }
        return coords;
    }

    /**
     * Return an arraylist of all the coordinates of the cells that are uncovered
     * */
    public ArrayList<Coord> getUncoveredCells() {
        ArrayList<Coord> coords = new ArrayList<>();
        for (Coord c : gbu.getAllCoords()) {
            if (this.isUncovered(c)) coords.add(c);
        }
        return coords;
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
