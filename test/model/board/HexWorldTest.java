package model.board;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HexWorldTest {

    @Test
    public void testValidity() {
        for (World w : HexWorld.values()) {
            assertTrue(w.validate());
        }
    }
}
