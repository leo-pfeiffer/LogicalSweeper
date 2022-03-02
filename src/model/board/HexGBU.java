package model.board;

import java.util.ArrayList;

public class HexGBU extends GameBoardUtils {

    public HexGBU(char[][] map) {
        super(map);
    }

    @Override
    public ArrayList<Coord> getAdjacentCoords(Coord coord) {

        int row = coord.getRow();
        int col = coord.getCol();

        Coord[] rawAdjacent = new Coord[6];

        int UP = row - 1;
        int DOWN = row + 1;
        int RIGHT = col + 1;
        int LEFT = col - 1;

        // uneven column
        if (col % 2 == 1) {
            // assume coord = {1, 1}
            rawAdjacent[0] = new Coord(UP, col);    // {0, 1}
            rawAdjacent[1] = new Coord(row, RIGHT); // {1, 2}
            rawAdjacent[2] = new Coord(DOWN, RIGHT);// {2, 2}
            rawAdjacent[3] = new Coord(DOWN, col);  // {2, 1}
            rawAdjacent[4] = new Coord(DOWN, LEFT); // {2, 0}
            rawAdjacent[5] = new Coord(row, LEFT);  // {1, 0}
        }

        // even column
        else {
            // assume coord = {1, 2}
            rawAdjacent[0] = new Coord(UP, col);    // {0, 2}
            rawAdjacent[1] = new Coord(UP, RIGHT);  // {0, 3}
            rawAdjacent[2] = new Coord(row, RIGHT); // {1, 3}
            rawAdjacent[3] = new Coord(DOWN, col);  // {2, 2}
            rawAdjacent[4] = new Coord(row, LEFT);  // {1, 1}
            rawAdjacent[5] = new Coord(UP, LEFT);   // {0, 1}
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
