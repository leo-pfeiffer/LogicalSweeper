package model.board;

public interface World extends CharMap {
    String name();
    char[][] getMap();
    View createNewView();
    int countMines();
    boolean validate();
}
