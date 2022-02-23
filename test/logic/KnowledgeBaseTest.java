package logic;

import model.board.View;
import logic.KnowledgeBase;
import org.junit.Test;

public class KnowledgeBaseTest {

    @Test
    public void testSetup() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        KnowledgeBase kb = new KnowledgeBase(view);
    }

}
