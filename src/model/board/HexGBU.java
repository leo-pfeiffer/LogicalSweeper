package model.board;

import java.util.ArrayList;

/**
 * GameBoardUtils implementation for hexagonal grids.
 */
public class HexGBU extends GameBoardUtils {

    public HexGBU(char[][] map) {
        super(map);
    }

    @Override
    public ArrayList<Coord> getAdjacentCoords(Coord coord) {

        int row = coord.getRow();
        int col = coord.getCol();

        Coord[] rawAdjacent;

        int UP = row - 1;
        int DOWN = row + 1;
        int RIGHT = col + 1;
        int LEFT = col - 1;

        // uneven column
        if (col % 2 == 1) {
            // assume coord = {1, 1}
            rawAdjacent = new Coord[]{
                new Coord(UP, col),
                new Coord(row, RIGHT),
                new Coord(DOWN, RIGHT),
                new Coord(DOWN, col),
                new Coord(DOWN, LEFT),
                new Coord(row, LEFT),
            };
        }

        // even column
        else {
            rawAdjacent = new Coord[]{
                    new Coord(UP, col),
                    new Coord(UP, RIGHT),
                    new Coord(row, RIGHT),
                    new Coord(DOWN, col),
                    new Coord(row, LEFT),
                    new Coord(UP, LEFT)
            };
        }

        ArrayList<Coord> adjacent = new ArrayList<>();

        for (Coord c : rawAdjacent) {
            if (this.containsCoord(c)) adjacent.add(c);
        }
        return adjacent;
    }

    @Override
    public GameBoardUtils newInstance(char[][] map) {
        return new HexGBU(map);
    }
}
