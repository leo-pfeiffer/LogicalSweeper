package model.board;

import java.util.ArrayList;

/**
 * Util class to provide functionalities for game boards.
 * */
public abstract class GameBoardUtils {

    public static boolean containsCoord(Coord coord, int mapSize) {
        int row = coord.getRow();
        int col = coord.getCol();
        return col >= 0 && col < mapSize && row >= 0 && row < mapSize;
    }

    public static char getCell(Coord coord, char[][] map) {
        if (!GameBoardUtils.containsCoord(coord, map.length)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return map[coord.getRow()][coord.getCol()];
    }

    public static ArrayList<Coord> getAdjacentCoords(Coord coord, char[][] map) {

        if (!GameBoardUtils.containsCoord(coord, map.length)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }

        ArrayList<Coord> adjacentCoords = new ArrayList<>();

        int row = coord.getRow();
        int col = coord.getCol();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && (i != row || j != col)) {
                    Coord adjCoord = new Coord(i, j);
                    if (containsCoord(adjCoord, map.length)) {
                        adjacentCoords.add(adjCoord);
                    }
                }
            }
        }
        return adjacentCoords;
    }

    public static int countOccurrence(char token, ArrayList<Coord> cells, char[][] map) {
        int count = 0;
        for (Coord coord : cells) {
            if (GameBoardUtils.getCell(coord, map) == token) {
                count++;
            }
        }
        return count;
    }

    public static int countOccurrence(char token, char[][] map) {
        return GameBoardUtils.countOccurrence(token, GameBoardUtils.getAllCoords(map.length), map);
    }

    public static boolean cellIsToken(char token, Coord coord, char[][] map) {
        if (!GameBoardUtils.containsCoord(coord, map.length)) {
            throw new IllegalArgumentException("Coordinates outside the map.");
        }
        return map[coord.getRow()][coord.getCol()] == token;
    }

    public static ArrayList<Coord> getAllCoords(int size) {
        ArrayList<Coord> coords = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                coords.add(new Coord(i, j));
            }
        }
        return coords;
    }
}
