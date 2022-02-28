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

        rawAdjacent[0] = new Coord(UP, col);
        rawAdjacent[1] = new Coord(row, RIGHT);
        rawAdjacent[2] = new Coord(DOWN, RIGHT);
        rawAdjacent[3] = new Coord(DOWN, col);
        rawAdjacent[4] = new Coord(DOWN, LEFT);
        rawAdjacent[5] = new Coord(row, LEFT);

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
