package model.board;

import java.util.ArrayList;

/**
 * Worlds for triangular grids.
 * Two hints are given at indexes [0][0] and [length/2][length/2]
 * */
public enum TriWorld implements World {

	// sps ok
	TEST(new char[][] {
			{'0', '0', '1'},
			{'0', '1', 'm'},
			{'m', '1', 'b'}
	}),

	// sps not ok
	SMALL(new char[][] {
			{'0', '1', 'm', 'b', '0'},
			{'0', '1', '1', 'b', '0'},
			{'1', 'm', '3', 'm', '1'},
			{'0', '1', 'm', '2', '0'},
			{'m', '1', '1', 'm', '1'}
	}),

	// sps not ok
	MEDIUM(new char[][] {
			{'1', 'm', '1', '0', '1', '1', 'm'},
			{'0', '1', '0', '1', 'm', '1', '1'},
			{'1', 'm', '1', '0', '0', '0', '0'},
			{'1', 'm', '1', '0', '0', '0', '0'},
			{'0', '2', 'm', '1', '0', '0', '0'},
			{'1', 'm', '3', 'm', '2', 'm', '1'},
			{'0', '2', 'm', '2', '0', '1', '0'}
	}),
	;


	private final char[][] map;
	private final int size;
	private final GameBoardUtils gbu;

	TriWorld(char[][] map) {
		this.map = map;
		this.size = map.length;
		this.gbu = new TriGBU(this.map);
	}

	@Override
	public char[][] getMap() {
		return this.map;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean containsCoord(Coord coord) {
		return gbu.containsCoord(coord);
	}

	@Override
	public char getCell(Coord coord) {
		return gbu.getCell(coord);
	}

	@Override
	public ArrayList<Coord> getAdjacentCoords(Coord coord) {
		return gbu.getAdjacentCoords(coord);
	}

	/**
	 * Create a new View of the world map, uncovering all blocked cells and counting
	 * the total number of mines.
	 * */
	@Override
	public View createNewView() {
		return this.gbu.createNewView();
	}

	/**
	 * Count the number of mines in the map.
	 * */
	@Override
	public int countMines() {
		return gbu.countOccurrence(Token.MINE.getChar());
	}
}
