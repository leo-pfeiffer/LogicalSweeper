package model.board;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GBUTest {

    public int numNbrs(HexGBU hexGBU, Coord cell) {
        ArrayList<Coord> nbrs = hexGBU.getAdjacentCoords(cell);
        return nbrs.size();
    }

    @Test
    public void testHex() {
        char[][] map = new char[][] {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        HexGBU hexGBU = new HexGBU(map);

        assertEquals(2, numNbrs(hexGBU, new Coord(0, 0)));
        assertEquals(5, numNbrs(hexGBU, new Coord(0, 1)));
        assertEquals(2, numNbrs(hexGBU, new Coord(0, 2)));
        assertEquals(4, numNbrs(hexGBU, new Coord(1, 0)));
        assertEquals(6, numNbrs(hexGBU, new Coord(1, 1)));
        assertEquals(4, numNbrs(hexGBU, new Coord(1, 2)));
        assertEquals(3, numNbrs(hexGBU, new Coord(2, 0)));
        assertEquals(3, numNbrs(hexGBU, new Coord(2, 1)));
        assertEquals(3, numNbrs(hexGBU, new Coord(2, 2)));
    }
}
