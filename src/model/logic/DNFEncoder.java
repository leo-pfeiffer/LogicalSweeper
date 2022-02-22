package model.logic;

import java.util.ArrayList;
import java.util.HashSet;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;

public class DNFEncoder {

    private static final FormulaFactory f = new FormulaFactory();

    public Formula encode(String[] cells, int clue) {
        ArrayList<BooleanArray> permutations = booleanPermutations(cells.length, clue);

        Formula formula = null;
        for (BooleanArray permutation : permutations) {
            Formula nextFormula = encode(cells, permutation);
            formula = formula == null ? nextFormula : f.or(formula, nextFormula);
        }

        return formula;
    }

    public Formula encode(String[] cells, BooleanArray permutation) {

        Formula formula = null;
        for (int i = 0; i < cells.length; i++) {
            Literal nextLiteral = f.literal(cells[i], permutation.get(i));
            formula = formula == null ? nextLiteral : f.and(formula, nextLiteral);
        }

        return formula;
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
            System.out.println(arr + " " + arr.hashCode());
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
