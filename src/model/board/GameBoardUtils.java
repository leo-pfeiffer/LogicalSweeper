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

    /**
     * This function returns a list of all the coordinates that are adjacent to the given coordinate
     *
     * @param coord The coordinate to get the adjacent coordinates of.
     * @return An ArrayList of Coord objects.
     */
    public abstract ArrayList<Coord> getAdjacentCoords(Coord coord);

    /**
     * This function creates a new instance of the class that implements the GameBoardUtils interface
     *
     * @param map The map of the game board.
     * @return The new instance of the GameBoardUtils class.
     */
    public abstract GameBoardUtils newInstance(char[][] map);

    /**
     * Given a coordinate, return true if the coordinate is in the map, and false otherwise
     *
     * @param coord The coordinate to check.
     * @return A boolean value.
     */
    public boolean containsCoord(Coord coord) {
        int mapSize = this.map.length;
        int row = coord.getRow();
        int col = coord.getCol();
        return col >= 0 && col < mapSize && row >= 0 && row < mapSize;
    }

    /**
     * Return the cell value at the given coordinates
     *
     * @param coord The coordinates of the cell to get.
     * @return The character at the specified coordinates.
     */
    public char getCell(Coord coord) {
        if (!this.containsCoord(coord)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return this.map[coord.getRow()][coord.getCol()];
    }

    /**
     * Given a token and a list of cells, return the number of times the token appears in the list of cells
     *
     * @param token the character you want to count
     * @param cells an array of coordinates
     * @return The number of occurrences of the token in the cells.
     */
    public int countOccurrence(char token, ArrayList<Coord> cells) {
        int count = 0;
        for (Coord coord : cells) {
            if (this.getCell(coord) == token) {
                count++;
            }
        }
        return count;
    }

    /**
     * Given a token, return the number of times it occurs in the grid
     *
     * @param token the character you want to count
     * @return The number of occurrences of the token in the board.
     */
    public int countOccurrence(char token) {
        return this.countOccurrence(token, this.getAllCoords());
    }

    /**
     * Return true if the cell at the given coordinates contains the given token
     *
     * @param token the character that represents the token.
     * @param coord The coordinates of the cell to check.
     * @return A boolean value.
     */
    public boolean cellIsToken(char token, Coord coord) {
        if (!this.containsCoord(coord)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return this.map[coord.getRow()][coord.getCol()] == token;
    }

    /**
     * Return a list of all the coordinates in the map
     *
     * @return An ArrayList of Coord objects.
     */
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

    /**
     * For each cell in the board, if the cell is a number, then the number of mines adjacent to the cell must match the
     * number in the cell
     *
     * @return The return type is boolean.
     */
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
