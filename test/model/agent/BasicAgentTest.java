package model.agent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import delegate.ObscuredSweeper;
import model.agent.exceptions.MineFoundException;
import model.agent.exceptions.NothingToProbeException;
import model.board.Coord;
import model.board.RectWorld;
import org.junit.Test;

public class BasicAgentTest {

    String agentName = "P1";

    RectWorld world1 = RectWorld.TEST1;
    RectWorld world2 = RectWorld.TEST2;
    ObscuredSweeper game = new ObscuredSweeper(world1, agentName);

    @Test
    public void testSetup() {
        new BasicAgent(game, world1.createNewView());
    }

    @Test
    public void testGetNextCoordInOrder1() {
        BasicAgent agent = new BasicAgent(game, world1.createNewView());
        Coord c = agent.probeNext();
        assertEquals(new Coord(0, 0), c);

        c = agent.probeNext();
        assertEquals(new Coord(1, 1), c);

        assertThrows(MineFoundException.class, () -> agent.probeNext());

        assertThrows(MineFoundException.class, () -> agent.probeNext());

        assertThrows(MineFoundException.class, () -> agent.probeNext());

        assertThrows(NothingToProbeException.class, () -> agent.probeNext());
    }

    @Test
    public void testGetNextCoordInOrder2() {
        BasicAgent agent = new BasicAgent(game, world2.createNewView());

        Coord c = agent.probeNext();
        assertEquals(new Coord(0, 0), c);

        c = agent.probeNext();
        assertEquals(new Coord(0, 1), c);

        c = agent.probeNext();
        assertEquals(new Coord(0, 2), c);

        c = agent.probeNext();
        assertEquals(new Coord(1, 0), c);

        c = agent.probeNext();
        assertEquals(new Coord(1, 1), c);

        c = agent.probeNext();
        assertEquals(new Coord(1, 2), c);

        assertThrows(MineFoundException.class, () -> agent.probeNext());
        assertThrows(NothingToProbeException.class, () -> agent.probeNext());
    }

}
