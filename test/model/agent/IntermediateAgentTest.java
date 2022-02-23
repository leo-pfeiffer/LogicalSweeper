package model.agent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import delegate.Game;
import logic.CNFKnowledgeBase;
import logic.DNFKnowledgeBase;
import model.board.View;
import model.board.World;
import org.junit.Test;

public class IntermediateAgentTest {

    String agentNameP3 = "P3";
    String agentNameP4 = "P4";

    @Test
    public void testSetupDNF() {
        Game game = new Game(World.TEST1, agentNameP3);
        View v = World.TEST1.createNewView();
        new IntermediateAgent(game, v, new DNFKnowledgeBase(v));
    }

    @Test
    public void testSetupCNF() {
        Game game = new Game(World.TEST1, agentNameP3);
        View v = World.TEST1.createNewView();
        new IntermediateAgent(game, v, new CNFKnowledgeBase(v));
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP3World1() {
        Game game = new Game(World.TEST1, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP3World2() {
        Game game = new Game(World.TEST2, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP3World3() {
        Game game = new Game(World.TEST3, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP3World4() {
        Game game = new Game(World.TEST4, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP3World5() {
        Game game = new Game(World.TEST5, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testP3World6() {
        Game game = new Game(World.TEST6, agentNameP3);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP4World1() {
        Game game = new Game(World.TEST1, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP4World2() {
        Game game = new Game(World.TEST2, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP4World3() {
        Game game = new Game(World.TEST3, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP4World4() {
        Game game = new Game(World.TEST4, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent wins.
     * */
    @Test
    public void testP4World5() {
        Game game = new Game(World.TEST5, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertTrue(game.agentHasWon());
        assertFalse(game.isPlaying());
    }

    /**
     * Agent doesn't terminate.
     * */
    @Test
    public void testP4World6() {
        Game game = new Game(World.TEST6, agentNameP4);
        game.run();
        assertFalse(game.agentHasDied());
        assertFalse(game.agentHasWon());
        assertFalse(game.isPlaying());
    }
}