package model.agent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import delegate.ObscuredSweeper;
import model.board.RectWorld;
import org.junit.Test;

public class SpsAgentTest {

    String agentName = "P2";

    @Test
    public void testSetup() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST1, agentName);
        new SpsAgent(game, RectWorld.TEST1.createNewView());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testWorld1() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST1, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testWorld2() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST2, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testWorld3() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST3, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testWorld4() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST4, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testWorld5() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST5, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testWorld6() {
        ObscuredSweeper game = new ObscuredSweeper(RectWorld.TEST6, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }
}
