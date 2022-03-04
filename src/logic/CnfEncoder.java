package logic;

import java.util.ArrayList;
import java.util.List;

public class CnfEncoder extends Encoder {

    /**
     * Create CNF clause for an exactly-k constraint by conjunction of
     * at-most-k and at-least-k.
     * @param variables Integer identifiers of the neighbors of the cell
     * @param k The clue of the cell minus the number of adjacent cells already flagged
     * */
    public int[][] exactly(int[] variables, int k) {

        // encode at-most-k and at-least-k
        int[][] atMost = atMost(variables, k);
        int[][] atLeast = atLeast(variables, k);

        // take the union of both and return
        int[][] exactly = new int[atLeast.length + atMost.length][];
        System.arraycopy(atLeast, 0, exactly, 0, atLeast.length);
        System.arraycopy(atMost, 0, exactly, atLeast.length, atMost.length);

        return exactly;
    }

    /**
     * Create CNF clause for an at-most-k constraint.
     * We achieve this by encoding "n Choose k+1" combinations.
     * @param variables Integer identifiers of the neighbors of the cell
     * @param k The clue of the cell minus the number of adjacent cells already flagged
     * */
    public int[][] atMost(int[] variables, int k) {
        return encode(variables, k + 1, true);
    }

    /**
     * Create CNF clause for an at-least-k constraint.
     * We achieve this by encoding "n Choose n-k+1" combinations.
     * @param variables Integer identifiers of the neighbors of the cell
     * @param k The clue of the cell minus the number of adjacent cells already flagged
     * */
    public int[][] atLeast(int[] variables, int k) {
        return encode(variables,variables.length - k + 1, false);
    }

    /**
     * Create a constraint in CNF form restricting the variables to only have k of them
     * be true at the same time.
     * @param variables Integer identifiers of the neighbors of the cell
     * @param k The clue of the cell minus the number of adjacent cells already flagged
     * @param negate Should the negation of the variables be used?
     * */
    public int[][] encode(int[] variables, int k, boolean negate) {
        int n = variables.length;

        // generate binomial combinations
        List<int[]> combinations = generateBinomialCombinations(n, k);

        // encode the variables by mapping them onto the binomial combinations
        int[][] encoded = new int[combinations.size()][];
        
        for (int i = 0; i < combinations.size(); i++) {

            int[] combination = combinations.get(i);
            int[] encodedArr = new int[combination.length];
        
            // map the 1st variable to the integer 0 in the combination
            // map the 2nd variable to the integer 1 ... 
            for (int j = 0; j < combination.length; j++) {
                int v = variables[combination[j]];
                encodedArr[j] = negate ? -v : v;
            }

            encoded[i] = encodedArr;
        }

        return encoded;
    }

    /**
     * Generate the binomial combinations ("n Choose k") recursively.
     *
     * Adapted from: https://www.baeldung.com/java-combinations-algorithm
     *
     * @param n number of variables
     * @param k number of True variables
     * */
    public List<int[]> generateBinomialCombinations(int n, int k) {
        List<int[]> combinations = new ArrayList<>();
        binomialCombinationsHelper(combinations, new int[k], 0, n-1, 0);
        return combinations;
    }

    /**
     * Helper method for the recursive call of the binomial combination generator method.
     * The algorithm generates all (n choose k) combinations for n integers by
     * recursively traversing the array, at every recursion first including then excluding
     * the current integer.
     * The base case is given by when the current index reaches the end of the temporary array,
     * which has length k.
     * @param combinations The list of binomial combinations
     * @param temp An initially empty array of length k to build the combination
     * @param startIdx First index to consider in the recursive call
     * @param endIdx Last index to consider in the recursive call
     * @param currIdx Index currently being considered
     * */
    private void binomialCombinationsHelper(List<int[]> combinations, int[] temp, int startIdx, int endIdx, int currIdx) {
        // base case: add temp to array of combinations
        if (currIdx == temp.length) {
            int[] combination = temp.clone();
            combinations.add(combination);
        }
        // recursive steps with two recursive calls:
        // 1. include current index and choose r - 1 more elements
        // 2. exclude current index and choose r more elements
        else if (startIdx <= endIdx) {
            temp[currIdx] = startIdx;
            binomialCombinationsHelper(combinations, temp, startIdx + 1, endIdx, currIdx + 1);
            binomialCombinationsHelper(combinations, temp, startIdx + 1, endIdx, currIdx);
        }
    }
}
