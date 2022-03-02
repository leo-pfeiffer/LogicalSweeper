package model.board;

import java.util.ArrayList;

/**
 * Util class to provide functionalities for game boards.
 * */
public abstract class GameBoardUtils {

    private final char[][] map;

    public GameBoardUtils(char[][] map) {
        this.map = map;
    }

    public abstract ArrayList<Coord> getAdjacentCoords(Coord coord);

    public abstract GameBoardUtils newInstance(char[][] map);

    public boolean containsCoord(Coord coord) {
        int mapSize = this.map.length;
        int row = coord.getRow();
        int col = coord.getCol();
        return col >= 0 && col < mapSize && row >= 0 && row < mapSize;
    }

    public char getCell(Coord coord) {
        if (!this.containsCoord(coord)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return this.map[coord.getRow()][coord.getCol()];
    }

    public int countOccurrence(char token, ArrayList<Coord> cells) {
        int count = 0;
        for (Coord coord : cells) {
            if (this.getCell(coord) == token) {
                count++;
            }
        }
        return count;
    }

    public int countOccurrence(char token) {
        return this.countOccurrence(token, this.getAllCoords());
    }

    public boolean cellIsToken(char token, Coord coord) {
        if (!this.containsCoord(coord)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return this.map[coord.getRow()][coord.getCol()] == token;
    }

    public ArrayList<Coord> getAllCoords() {
        int size = this.map.length;
        ArrayList<Coord> coords = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                coords.add(new Coord(i, j));
            }
        }
        return coords;
    }

    /**
     * Create a new View of the world map, uncovering all blocked cells and counting
     * the total number of mines.
     * */
    public View createNewView() {

        int size = this.map.length;
        char[][] view = new char[size][size];

        // uncover blocked cells
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                Coord coord = new Coord(row, col);
                char cell = this.getCell(coord);
                if (cell == Token.BLOCK.getChar()) {
                    view[row][col] = Token.BLOCK.getChar();
                } else {
                    view[row][col] = Token.UNKNOWN.getChar();
                }
            }
        }

        GameBoardUtils viewGBU = this.newInstance(view);
        int mineCount = this.countOccurrence(Token.MINE.getChar());
        return new View(view, mineCount, viewGBU);
    }

    public boolean validate() {
        for (Coord cell : this.getAllCoords()) {
            if (Character.isDigit(this.getCell(cell))) {
                int numExpected = Character.getNumericValue(this.getCell(cell));
                ArrayList<Coord> nbrs = this.getAdjacentCoords(cell);
                int numActual = 0;
                for (Coord nbr : nbrs) {
                    if (this.cellIsToken(Token.MINE.getChar(), nbr)) {
                        numActual++;
                    }
                }
                if (numExpected != numActual) return false;
            }
        }
        return true;
    }
}
