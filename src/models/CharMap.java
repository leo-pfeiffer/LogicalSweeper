package models;

import java.util.ArrayList;

/**
 * An interface for classes wrapping a 2d array of characters.
 * */
public interface CharMap {

    /**
     * Get the size of the map.
     * */
    int getSize();

    /**
     * Returns true if the given coord is in the map.
     * @param coord The coordinate to check.
     * */
    boolean containsCoord(Coord coord);

    /**
     * Returns the value of the cell of the given coord from the map.
     * @param coord The coordinate to get the value of.
     * */
    char getCell(Coord coord);

    /**
     * Return an array of cells adjacent to the given coordinate.
     * @param coord The coordinate to get the adjacent cells of.
     * */
    ArrayList<Coord> getAdjacentCoords(Coord coord);
}
