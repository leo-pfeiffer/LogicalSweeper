package models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ViewTest {

    World world = World.TEST1;

    @Test
    public void testGetView() {
        View view = new View(world.createNewView(), world.getMineCount());
        assertArrayEquals(view.getView(), world.createNewView());
    }

    @Test
    public void testGetSize() {
        View view = new View(world.createNewView(), world.getMineCount());
        assertEquals(view.getSize(), world.getSize());
    }

    @Test
    public void testGetMineCount() {
        View view = new View(world.createNewView(), world.getMineCount());
        assertEquals(view.getMineCount(), world.getMineCount());
    }

    @Test
    public void testGetCell() {
        View view = new View(world.createNewView(), world.getMineCount());
        int m = view.getSize() / 2;
        Coord c = new Coord(0, 0);
        Coord d = new Coord(m, m);
        assertEquals(view.getCell(c), world.getCell(c));
        assertEquals(view.getCell(d), world.getCell(d));
    }
}
