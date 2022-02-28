package model.board;

import java.util.ArrayList;

public class RectGBU extends GameBoardUtils {

    public RectGBU(char[][] map) {
        super(map);
    }

    @Override
    public ArrayList<Coord> getAdjacentCoords(Coord coord) {

        if (!this.containsCoord(coord)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }

        ArrayList<Coord> adjacentCoords = new ArrayList<>();

        int row = coord.getRow();
        int col = coord.getCol();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && (i != row || j != col)) {
                    Coord adjCoord = new Coord(i, j);
                    if (containsCoord(adjCoord)) {
                        adjacentCoords.add(adjCoord);
                    }
                }
            }
        }
        return adjacentCoords;
    }

    @Override
    public RectGBU newInstance(char[][] map) {
        return new RectGBU(map);
    }

}
