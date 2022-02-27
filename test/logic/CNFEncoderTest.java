package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CNFEncoderTest {

    @Test
    public void testSetup() {
        new CNFEncoder();
    }

    @Test
    public void testExactlyOne() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 1;
        int[][] actual = cnf.exactly(new int[]{1, 2, 3}, numTrueExpected);
    }

}
