package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;

public class EncoderTest {

    @Test
    public void testSetupDNFEncoder() {
        new DNFEncoder();
    }

    @Test
    public void testTest() {
        ArrayList<BooleanArray> permutations = Encoder.booleanPermutations(3, 1);

        CNFEncoder cnf = new CNFEncoder();

        int[] variables = {1, 2, 3};
        int k = 1;
        int[][] atMost = cnf.atMost(variables, k);
        int[][] atLeast = cnf.atLeast(variables, variables.length - k);
        int[][] exactly = cnf.exactly(variables, k);
//        boolean result = cnf.solver(exactly);
        int x = 1;
    }

    @Test
    public void testBooleanPermutationsOneTrue() {
        int numTrueExpected = 1;
        ArrayList<BooleanArray> permutations = Encoder.booleanPermutations(3, numTrueExpected);
        for (BooleanArray permutation : permutations) {
            int numTrue = 0;
            for (boolean b : permutation.toArray()) {
                if (b) numTrue++;
            }
            assertEquals(numTrueExpected, numTrue);
        }
    }

    @Test
    public void testBooleanPermutationsSomeTrue() {
        int numTrueExpected = 2;
        ArrayList<BooleanArray> permutations = Encoder.booleanPermutations(3, numTrueExpected);
        for (BooleanArray permutation : permutations) {
            int numTrue = 0;
            for (boolean b : permutation.toArray()) {
                if (b) numTrue++;
            }
            assertEquals(numTrueExpected, numTrue);
        }
    }

    @Test
    public void testBooleanPermutationsAllTrue() {
        int numTrueExpected = 4;
        ArrayList<BooleanArray> permutations = Encoder.booleanPermutations(numTrueExpected, numTrueExpected);
        assertEquals(permutations.size(), 1);
        for (boolean b : permutations.get(0).toArray()) {
            assertTrue(b);
        }
    }

    @Test
    public void testBooleanPermutationsNoneTrue() {
        int numTrueExpected = 0;
        ArrayList<BooleanArray> permutations = Encoder.booleanPermutations(4, numTrueExpected);
        assertEquals(permutations.size(), 1);
        for (boolean b : permutations.get(0).toArray()) {
            assertFalse(b);
        }
    }

    @Test
    public void testEncodeBooleanArray() {
        DNFEncoder dnf = new DNFEncoder();
        BooleanArray array = new BooleanArray(new boolean[]{true, true, false});
        String[] variables = {"X", "Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.and(f.variable("X"), f.variable("Y"), f.not(f.variable("Z")));
        assertEquals(expected, dnf.encode(variables, array));
    }

    @Test
    public void testEncodeBooleanArrayAllTrue() {
        DNFEncoder dnf = new DNFEncoder();
        BooleanArray array = new BooleanArray(new boolean[]{true, true, true});
        String[] variables = {"X", "Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.and(f.variable("X"), f.variable("Y"), f.variable("Z"));
        assertEquals(expected, dnf.encode(variables, array));
    }

    @Test
    public void testEncodeBooleanArrayAllFalse() {
        DNFEncoder dnf = new DNFEncoder();
        BooleanArray array = new BooleanArray(new boolean[]{false, false, false});
        String[] variables = {"X", "Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.and(
                f.not(f.variable("X")),
                f.not(f.variable("Y")),
                f.not(f.variable("Z"))
        );
        assertEquals(expected, dnf.encode(variables, array));
    }

    @Test
    public void testEncodeOneTrue() {
        DNFEncoder dnf = new DNFEncoder();
        int numTrueExpected = 1;
        String[] variables = {"Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.or(
                f.and(f.variable("Y"), f.not(f.variable("Z"))),
                f.and(f.variable("Z"), f.not(f.variable("Y")))
        );
        assertEquals(expected, dnf.encode(variables, numTrueExpected));
    }

    @Test
    public void testEncodeSomeTrue() {
        DNFEncoder dnf = new DNFEncoder();
        int numTrueExpected = 2;
        String[] variables = {"X", "Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.or(
                f.and(f.variable("X"), f.variable("Y"), f.not(f.variable("Z"))),
                f.and(f.not(f.variable("X")), f.variable("Y"), f.variable("Z")),
                f.and(f.variable("X"), f.not(f.variable("Y")), f.variable("Z"))
        );
        assertEquals(expected, dnf.encode(variables, numTrueExpected));
    }


    @Test
    public void testEncodeAllTrue() {
        DNFEncoder dnf = new DNFEncoder();
        int numTrueExpected = 2;
        String[] variables = {"Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.and(f.variable("Y"), f.variable("Z"));

        assertEquals(expected, dnf.encode(variables, numTrueExpected));
    }

    @Test
    public void testEncodeNoneTrue() {
        DNFEncoder dnf = new DNFEncoder();
        int numTrueExpected = 0;
        String[] variables = {"Y", "Z"};
        FormulaFactory f = dnf.getFormulaFactory();
        Formula expected = f.and(f.not(f.variable("Y")), f.not(f.variable("Z")));

        assertEquals(expected, dnf.encode(variables, numTrueExpected));
    }
}