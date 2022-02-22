package model;

import static org.junit.Assert.assertThrows;

import delegate.Game;
import model.agent.Agent;
import model.agent.AgentFactory;
import model.agent.BasicAgent;
import model.agent.BeginnerAgent;
import model.board.World;
import org.junit.Test;

public class AgentFactoryTest {

    String basic = "P1";
    String beginner = "P2";
    String unknownName = "ABC";
    World world = World.TEST1;

    @Test
    public void testBasicAgent() {
        Agent agent = AgentFactory.createAgent(basic, new Game(world, basic), world.createNewView());
        assert(agent instanceof BasicAgent);
    }

    @Test
    public void testBeginnerAgent() {
        Agent agent = AgentFactory.createAgent(beginner, new Game(world, beginner), world.createNewView());
        assert(agent instanceof BeginnerAgent);
    }

    @Test
    public void testUnknownName() {
        assertThrows(IllegalArgumentException.class, () -> {
            AgentFactory.createAgent(unknownName, new Game(world, basic), world.createNewView());
        });
    }
}
