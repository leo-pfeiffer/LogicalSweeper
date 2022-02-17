package delegate;

import models.World;
import org.junit.Test;

public class GameTest {

    String basicAgent = "P1";
    World world = World.TEST1;

    @Test
    public void testSetup() {
        new Game(world, basicAgent);
    }
}
