package model.board;

/**
 * Interface for classes representing a World.
 * */
public interface World extends CharMap {

    /**
     * Return the name of the world
     */
    String name();

    /**
     * Get the raw map of the world
     * */
    char[][] getMap();

    /**
     * Create a new View of the world, covering all hints and mines
     * */
    View createNewView();

    /**
     * Return the number of mines in the world
     * */
    int countMines();

    /**
     * Return true if the map is a valid map else false
     * */
    boolean validate();
}
