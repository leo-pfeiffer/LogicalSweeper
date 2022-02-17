package models;

import delegate.Game;
import org.junit.Test;

public class BasicAgentTest {

    String agentName = "P1";

    World world = World.TEST1;
    Game game = new Game(world, agentName);

    @Test
    public void testSetup() {
        new BasicAgent(world.createNewView());
    }

}
