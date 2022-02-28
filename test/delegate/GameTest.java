package delegate;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.board.World;
import org.junit.Test;

public class GameTest {

    String basicAgent = "P1";

    @Test
    public void test1() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST1, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }

    @Test
    public void test2() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST2, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test3() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST3, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test4() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST4, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test5() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST5, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test6() {
        ObscuredSweeper game = new ObscuredSweeper(World.TEST6, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }
}
