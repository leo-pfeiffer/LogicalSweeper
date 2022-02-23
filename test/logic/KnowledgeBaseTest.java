package logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.board.Coord;
import model.board.View;
import org.junit.Test;

public class KnowledgeBaseTest {

    @Test
    public void testSetup() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        DNFKnowledgeBase kb = new DNFKnowledgeBase(view);
    }

    @Test
    public void testEntailment() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        DNFKnowledgeBase kb = new DNFKnowledgeBase(view);

        assertFalse(kb.checkEntailment(new Coord(0, 2), true));
        assertTrue(kb.checkEntailment(new Coord(1, 2), true));
        assertFalse(kb.checkEntailment(new Coord(2, 2), true));
    }

}
