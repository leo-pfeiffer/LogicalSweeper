package model.board;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TriWorldTest {
    @Test
    public void testValidity() {
        for (World w : TriWorld.values()) {
            assertTrue(w.validate());
        }
    }
}
