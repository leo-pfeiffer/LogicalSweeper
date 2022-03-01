package delegate;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.board.RectWorld;
import model.board.TriWorld;
import model.board.World;
import org.junit.Test;

/**
 * Test the ObscuredSweeper class.
 */
public class ObscuredSweeperTest {

    String basicAgent = "P1";

    public ObscuredSweeper runGame(World world, String agentName) {
        ObscuredSweeper game = new ObscuredSweeper(world, agentName, false);
        game.run();
        return game;
    }

    @Test
    public void testRunAllConfigurationsRect() {
        String[] agentNames = {"P1", "P2", "P3", "P4", "P5"};
        RectWorld[] worlds = RectWorld.values();
        for (String agent : agentNames) {
            for (RectWorld world : worlds) {
                ObscuredSweeper game = runGame(world, agent);
                assertFalse(game.isPlaying());
            }
        }
    }

    @Test
    public void testRunAllConfigurationsTri() {
        String[] agentNames = {"P1", "P2", "P3", "P4", "P5"};
        TriWorld[] worlds = TriWorld.values();
        for (String agent : agentNames) {
            for (TriWorld world : worlds) {
                ObscuredSweeper game = runGame(world, agent);
                assertFalse(game.isPlaying());
            }
        }
    }

    @Test
    public void test1() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST1, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }

    @Test
    public void test2() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST2, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test3() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST3, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test4() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST4, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test5() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST5, basicAgent);
        game.run();
        assertFalse(game.agentHasWon());
        assertTrue(game.agentHasDied());
    }

    @Test
    public void test6() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST6, basicAgent);
        game.run();
        assertTrue(game.agentHasWon());
        assertFalse(game.agentHasDied());
    }
}
