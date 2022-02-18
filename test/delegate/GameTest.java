package delegate;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import models.World;
import org.junit.Test;

public class GameTest {

    String basicAgent = "P1";

    @Test
    public void test1() {
        Game game = new Game(World.TEST1, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }

    @Test
    public void test2() {
        Game game = new Game(World.TEST2, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test3() {
        Game game = new Game(World.TEST3, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test4() {
        Game game = new Game(World.TEST4, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test5() {
        Game game = new Game(World.TEST5, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test6() {
        Game game = new Game(World.TEST6, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }
}
