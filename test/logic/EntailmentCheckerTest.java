package logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.board.View;
import model.logic.DNFEncoder;
import model.logic.EntailmentChecker;
import model.logic.KnowledgeBase;
import org.junit.Test;
import org.logicng.formulas.FormulaFactory;

public class EntailmentCheckerTest {

    @Test
    public void testWithString() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        KnowledgeBase kb = new KnowledgeBase(view);

        EntailmentChecker checker = new EntailmentChecker(kb);

        assertFalse(checker.checkEntailment("D_0_2", true));
        assertTrue(checker.checkEntailment("D_1_2", true));
        assertFalse(checker.checkEntailment("D_2_2", true));
    }

    @Test
    public void testWithVariable() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        KnowledgeBase kb = new KnowledgeBase(view);

        EntailmentChecker checker = new EntailmentChecker(kb);

        FormulaFactory f = kb.getF();

        assertFalse(checker.checkEntailment(f.variable("D_0_2")));
        assertTrue(checker.checkEntailment(f.variable("D_1_2")));
        assertFalse(checker.checkEntailment(f.variable("D_2_2")));
    }

    @Test
    public void testWithLiteral() {
        char[][] board = {{'0', '1', '?'}, {'0', '1', '?'}, {'0', '1', '?'}};
        View view = new View(board, 1);
        KnowledgeBase kb = new KnowledgeBase(view);

        EntailmentChecker checker = new EntailmentChecker(kb);

        FormulaFactory f = kb.getF();

        assertFalse(checker.checkEntailment(f.literal("D_0_2", true)));
        assertTrue(checker.checkEntailment(f.literal("D_1_2", true)));
        assertFalse(checker.checkEntailment(f.literal("D_2_2", true)));
    }

}
