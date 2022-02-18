package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import delegate.Game;
import org.junit.Test;

public class BasicAgentTest {

    String agentName = "P1";

    World world1 = World.TEST1;
    World world2 = World.TEST2;
    Game game = new Game(world1, agentName);

    @Test
    public void testSetup() {
        new BasicAgent(world1.createNewView());
    }

    @Test
    public void testProbe1() {
        BasicAgent agent = new BasicAgent(world1.createNewView());
        Coord c = agent.probe();
        assertEquals(new Coord(2, 0), c);

        c = agent.probe();
        assertEquals(new Coord(2, 1), c);

        c = agent.probe();
        assertEquals(new Coord(2, 2), c);

        assertThrows(NothingToProbeException.class, () -> agent.probe());
    }

    @Test
    public void testProbe2() {
        BasicAgent agent = new BasicAgent(world2.createNewView());
        Coord c = agent.probe();
        assertEquals(new Coord(0, 1), c);

        c = agent.probe();
        assertEquals(new Coord(0, 2), c);

        c = agent.probe();
        assertEquals(new Coord(1, 0), c);

        c = agent.probe();
        assertEquals(new Coord(1, 2), c);

        c = agent.probe();
        assertEquals(new Coord(2, 2), c);

        assertThrows(NothingToProbeException.class, () -> agent.probe());
    }

}
