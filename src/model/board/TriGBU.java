package model.board;

import java.util.ArrayList;
import java.util.List;

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

        if (getDir(coord) == 0) {
            return new ArrayList<>(List.of(new Coord[]{RIGHT, DOWN, LEFT}));
        } else {
            return new ArrayList<>(List.of(new Coord[]{RIGHT, LEFT, UP}));
        }
    }
}
