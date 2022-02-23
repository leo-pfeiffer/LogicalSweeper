package model.logic;

import java.util.ArrayList;
import java.util.HashSet;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;

public class DNFEncoder {

    private final FormulaFactory f;

    public DNFEncoder() {
        this(new FormulaFactory());
    }

    public DNFEncoder(FormulaFactory f) {
        this.f = f;
    }

    public FormulaFactory getFormulaFactory() {
        return f;
    }

    /**
     * @param variables The variables to be encoded.
     * @param numTrue The number of TRUE values in the literals.
     * */
    public Formula encode(String[] variables, int numTrue) {
        ArrayList<BooleanArray> permutations = booleanPermutations(variables.length, numTrue);
        ArrayList<Formula> formulae = new ArrayList<>();

        for (BooleanArray permutation : permutations) {
            formulae.add(encode(variables, permutation));
        }

        return f.or(formulae);
    }

    public Formula encode(String[] variables, BooleanArray permutation) {

        ArrayList<Formula> literals = new ArrayList<>();

        for (int i = 0; i < variables.length; i++) {
            literals.add(f.literal(variables[i], permutation.get(i)));
        }

        return f.and(literals);
    }

    public ArrayList<BooleanArray> booleanPermutations(int size, int numTrue) {

        boolean[] current = new boolean[size];
        int trueUsed = 0;
        for (int i = 0; i < size; i++) {
            current[i] = trueUsed < numTrue;
            trueUsed++;
        }

        HashSet<BooleanArray> permutationSet = permutations(new BooleanArray(current), current.length, new HashSet<>());
        return new ArrayList<>(permutationSet);
    }


    /**
     * Generates all permutations of the input array using Heap's algorithm.
     * */
    private HashSet<BooleanArray> permutations(BooleanArray arr, int size, HashSet<BooleanArray> permutations) {
        if (size == 1) {
            permutations.add(new BooleanArray(arr));
        }
        for (int i = 0; i < size; i++) {
            permutations(arr, size - 1, permutations);

            if (size % 2 == 0) {
                arr.swap(i, size - 1);
            } else {
                arr.swap(0, size - 1);
            }
        }
        return permutations;
    }
}
