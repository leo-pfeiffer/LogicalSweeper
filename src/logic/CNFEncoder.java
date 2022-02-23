package logic;

import java.util.ArrayList;

public class CNFEncoder extends Encoder {

    /**
     * Call this method on every clue cell.
     * @param variables Integer identifiers of the neighbors of the cell
     * @param k The clue of the cell minus the number of adjacent cells already flagged
     * */
    public int[][] exactly(int[] variables, int k) {
        int[][] atMost = atMost(variables, k);
        int[][] atLeast = atLeast(variables, variables.length - k);
        int[][] exactly = new int[atLeast.length + atMost.length][];
        System.arraycopy(atLeast, 0, exactly, 0, atLeast.length);
        System.arraycopy(atMost, 0, exactly, atLeast.length, atMost.length);
        return exactly;
    }

    public int[][] atMost(int[] variables, int k) {
        return encode(variables, k, true);
    }

    public int[][] atLeast(int[] variables, int k) {
        return encode(variables, k, false);
    }

    public int[][] encode(int[] variables, int numTrue, boolean negate) {

        ArrayList<BooleanArray> permutations = booleanPermutations(variables.length, numTrue);

        int[][] clauses = new int[permutations.size()][];

        for (int i = 0; i < permutations.size(); i++) {
            BooleanArray permutation = permutations.get(i);
            int[] clause = encode(permutation, variables, negate);
            clauses[i] = clause;
        }

        return clauses;
    }

    public int[] encode(BooleanArray permutation, int[] variables, boolean negate) {
        ArrayList<Integer> clause = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            if (permutation.get(i)) {
                // ignore TRUE values
            } else {
                // add FALSE values
                if (negate) {
                    clause.add(-variables[i]);
                } else {
                    clause.add(variables[i]);
                }
            }
        }
        return clause.stream().mapToInt(i -> i).toArray();
    }
}
