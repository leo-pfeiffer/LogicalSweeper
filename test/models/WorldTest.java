package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class WorldTest {

    @Test
    public void testIsInWorld() {
        World world = World.TEST1;
        assertTrue(world.containsCoord(new Coord(0, 0)));
        assertTrue(world.containsCoord(new Coord(0, 2)));
        assertTrue(world.containsCoord(new Coord(1, 1)));
        assertTrue(world.containsCoord(new Coord(2, 0)));
        assertTrue(world.containsCoord(new Coord(2, 2)));

        assertFalse(world.containsCoord(new Coord(0, 3)));
        assertFalse(world.containsCoord(new Coord(3, 0)));
        assertFalse(world.containsCoord(new Coord(3, 3)));

        assertThrows(IllegalArgumentException.class, () -> world.containsCoord(new Coord(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> world.containsCoord(new Coord(0, -1)));
        assertThrows(IllegalArgumentException.class, () -> world.containsCoord(new Coord(-1, -1)));
    }

    @Test
    public void testGetAdjacentCoords() {
        World world = World.TEST1;
        assertThrows(IllegalArgumentException.class, () -> world.containsCoord(new Coord(-1, -1)));

        ArrayList<Coord> coords1List = world.getAdjacentCoords(new Coord(0, 0));

        assertEquals(3, coords1List.size());
        assertTrue(coords1List.contains(new Coord(0, 1)));
        assertTrue(coords1List.contains(new Coord(1, 1)));
        assertTrue(coords1List.contains(new Coord(1, 0)));

        ArrayList<Coord> coords2List = world.getAdjacentCoords(new Coord(1, 1));

        assertEquals(8, coords2List.size());
        assertTrue(coords2List.contains(new Coord(0, 0)));
        assertTrue(coords2List.contains(new Coord(0, 1)));
        assertTrue(coords2List.contains(new Coord(0, 2)));

        assertTrue(coords2List.contains(new Coord(1, 0)));
        assertTrue(coords2List.contains(new Coord(1, 2)));

        assertTrue(coords2List.contains(new Coord(2, 0)));
        assertTrue(coords2List.contains(new Coord(2, 1)));
        assertTrue(coords2List.contains(new Coord(2, 2)));

        ArrayList<Coord> coords3List = world.getAdjacentCoords(new Coord(2, 2));

        assertEquals(3, coords3List.size());
        assertTrue(coords3List.contains(new Coord(2, 1)));
        assertTrue(coords3List.contains(new Coord(1, 1)));
        assertTrue(coords3List.contains(new Coord(1, 2)));
    }
}
