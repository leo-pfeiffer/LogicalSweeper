package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;

public class CNFEncoderTest {

    @Test
    public void testSetup() {
        new CNFEncoder();
    }

    private boolean combinationsUnique(int[][] combs) {
        HashSet<int[]> unique = new HashSet<>(Arrays.asList(combs));
        return unique.size() == combs.length;
    }

    @Test
    public void testExactlyNoneTrue() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 0;
        int[] arr = new int[]{1, 2, 3};
        int[][] actual = cnf.exactly(arr, numTrueExpected);
        for (int[] subArr : actual) {
            assertEquals(1, subArr.length);
        }
        assertEquals(arr.length, actual.length);
        assertTrue(combinationsUnique(actual));
    }

    @Test
    public void testExactlyOneTrue() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 1;
        int[] arr = new int[]{1, 2, 3};
        int[][] actual = cnf.exactly(arr, numTrueExpected);
        assertEquals(4, actual.length);
        assertTrue(combinationsUnique(actual));
    }

    @Test
    public void testExactlySomeTrue() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 2;
        int[] arr = new int[]{1, 2, 3, 4};
        int[][] actual = cnf.exactly(arr, numTrueExpected);
        assertTrue(combinationsUnique(actual));
        assertEquals(8, actual.length);
    }

    @Test
    public void testExactlyAllTrue() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 3;
        int[] arr = new int[]{1, 2, 3};
        int[][] actual = cnf.exactly(arr, numTrueExpected);
        assertEquals(3, actual.length);
        assertTrue(combinationsUnique(actual));
    }

    @Test
    public void testAtMostNegates() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 0;
        int[] arr = new int[]{1, 2, 3};
        int[][] actual = cnf.atMost(arr, numTrueExpected);
        for (int[] subArr : actual) {
            for (int n : subArr) {
                assertTrue(n < 0);
            }
        }
    }

    @Test
    public void testAtLeastDoesNotNegate() {
        CNFEncoder cnf = new CNFEncoder();
        int numTrueExpected = 3;
        int[] arr = new int[]{1, 2, 3};
        int[][] actual = cnf.atLeast(arr, numTrueExpected);
        for (int[] subArr : actual) {
            for (int n : subArr) {
                assertTrue(n > 0);
            }
        }
    }
}
