package models;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class AgentFactoryTest {

    String agentName = "P1";
    String unknownName = "ABC";
    World world = World.TEST1;

    @Test
    public void testBasicAgent() {
        Agent agent = AgentFactory.createAgent(agentName, world.createNewView());
        assert(agent instanceof BasicAgent);
    }

    @Test
    public void testUnknownName() {
        assertThrows(IllegalArgumentException.class, () -> {
            AgentFactory.createAgent(unknownName, world.createNewView());
        });
    }
}
