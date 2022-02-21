package models;

import static org.junit.Assert.assertThrows;

import delegate.Game;
import org.junit.Test;

public class AgentFactoryTest {

    String agentName = "P1";
    String unknownName = "ABC";
    World world = World.TEST1;
    Game game = new Game(world, agentName);

    @Test
    public void testBasicAgent() {
        Agent agent = AgentFactory.createAgent(agentName, game, world.createNewView());
        assert(agent instanceof BasicAgent);
    }

    @Test
    public void testUnknownName() {
        assertThrows(IllegalArgumentException.class, () -> {
            AgentFactory.createAgent(unknownName, game, world.createNewView());
        });
    }
}
