package model.agent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import delegate.Game;
import model.agent.BeginnerAgent;
import model.board.World;
import org.junit.Test;

public class BeginnerAgentTest {

    String agentName = "P2";

    @Test
    public void testSetup() {
        Game game = new Game(World.TEST1, agentName);
        new BeginnerAgent(game, World.TEST1.createNewView());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testWorld1() {
        Game game = new Game(World.TEST1, agentName);
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
        Game game = new Game(World.TEST2, agentName);
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
        Game game = new Game(World.TEST3, agentName);
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
        Game game = new Game(World.TEST4, agentName);
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
        Game game = new Game(World.TEST5, agentName);
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
        Game game = new Game(World.TEST6, agentName);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }
}
