package models;

public class View {

    private final char[][] view;
    private final int mineCount;

    public View(char[][] view, int mineCount) {
        this.view = view;
        this.mineCount = mineCount;
    }

    public char[][] getView() {
        return view;
    }

    public int getSize() {
        return this.view.length;
    }

    public char getCell(Coord coord) {
        assert coord.getCol() < this.getSize();
        assert coord.getRow() < this.getSize();
        return this.view[coord.getRow()][coord.getCol()];
    }

    public int getMineCount() {
        return mineCount;
    }
}
