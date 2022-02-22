package model.logic;

import java.util.ArrayList;
import java.util.HashSet;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;

public class DNFEncoder {

    private final FormulaFactory f;

    public DNFEncoder() {
        this(new FormulaFactory());
    }

    public DNFEncoder(FormulaFactory f) {
        this.f = f;
    }

    /**
     * @param cells The neighbors of the cell to be encoded.
     * @param clue The clue of the cell to be encoded
     * */
    public Formula encode(String[] cells, int clue) {
        ArrayList<BooleanArray> permutations = booleanPermutations(cells.length, clue);
        ArrayList<Formula> formulae = new ArrayList<>();

        for (BooleanArray permutation : permutations) {
            formulae.add(encode(cells, permutation));
        }

        return f.or(formulae);
    }

    public Formula encode(String[] cells, BooleanArray permutation) {

        ArrayList<Formula> literals = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            literals.add(f.literal(cells[i], permutation.get(i)));
        }

        return f.and(literals);
    }

    public ArrayList<BooleanArray> booleanPermutations(int numNbrs, int clue) {

        boolean[] current = new boolean[numNbrs];
        int cluesUsed = 0;
        for (int i = 0; i < numNbrs; i++) {
            current[i] = cluesUsed < clue;
            cluesUsed++;
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
