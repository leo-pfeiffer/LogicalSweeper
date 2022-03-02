package model.board;

import java.util.ArrayList;

/**
 * GameBoardUtils implementation for triangular grids
 * */
public class TriGBU extends GameBoardUtils {

    public TriGBU(char[][] map) {
        super(map);
    }

    /**
     * Get the direction of the coordinate.
     * This logic was taken from Practical 1.
     * Triangle points upwards: 0
     * Triangle points downwards: 1
     * */
    public int getDir(Coord coord) {
        if (coord.getRow() % 2 == 0) {
            return coord.getCol() % 2 == 0 ? 0 : 1;
        } else {
            return coord.getCol() % 2 == 0 ? 1 : 0;
        }
    }

    /**
     * Get adjacent coordinates from a triangular grid. This logic was taken from Practical 1.
     * */
    @Override
    public ArrayList<Coord> getAdjacentCoords(Coord coord) {

        int row = coord.getRow();
        int col = coord.getCol();

        Coord RIGHT = new Coord(row, col + 1);
        Coord DOWN = new Coord(row + 1, col);
        Coord LEFT = new Coord(row, col - 1);
        Coord UP = new Coord(row - 1, col);

        ArrayList<Coord> adjacent = new ArrayList<>();

        if (getDir(coord) == 0) {
            for (Coord c : new Coord[]{RIGHT, DOWN, LEFT}) {
                if (this.containsCoord(c)) adjacent.add(c);
            }
        } else {
            for (Coord c : new Coord[]{RIGHT, LEFT, UP}) {
                if (this.containsCoord(c)) adjacent.add(c);
            }
        }
        return adjacent;
    }

    @Override
    public TriGBU newInstance(char[][] map) {
        return new TriGBU(map);
    }
}
