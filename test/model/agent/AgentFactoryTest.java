package model.agent;

import static org.junit.Assert.assertThrows;

import delegate.ObscuredSweeper;
import model.board.RectWorld;
import org.junit.Test;

public class AgentFactoryTest {

    String basic = "P1";
    String beginner = "P2";
    String unknownName = "ABC";
    RectWorld world = RectWorld.TEST1;

    @Test
    public void testBasicAgent() {
        Agent agent = AgentFactory.createAgent(basic, new ObscuredSweeper(world, basic), world.createNewView());
        assert(agent instanceof BasicAgent);
    }

    @Test
    public void testBeginnerAgent() {
        Agent agent = AgentFactory.createAgent(beginner, new ObscuredSweeper(world, beginner), world.createNewView());
        assert(agent instanceof BeginnerAgent);
    }

    @Test
    public void testUnknownName() {
        assertThrows(IllegalArgumentException.class, () -> AgentFactory.createAgent(unknownName,
                new ObscuredSweeper(world, basic), world.createNewView()));
    }
}
