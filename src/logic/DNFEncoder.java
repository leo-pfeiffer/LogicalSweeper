package logic;

import java.util.ArrayList;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;

/**
 * Knowledge Base extension using DNF encoding.
 */
public class DNFEncoder extends Encoder {

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
     * Encode a set of variables such that exactly numTrue of them are true in all cases.
     * @param variables The variables to be encoded.
     * @param numTrue The number of TRUE values in the literals.
     * */
    public Formula encode(String[] variables, int numTrue) {

        // get all permutations of the variables s.t. numTrue of them are true
        ArrayList<BooleanArray> permutations = booleanPermutations(variables.length, numTrue);

        // clauses of the final formula
        ArrayList<Formula> formulae = new ArrayList<>();

        // encode each permutation and add it to the list
        for (BooleanArray permutation : permutations) {
            formulae.add(encodePermutation(variables, permutation));
        }

        return f.or(formulae);
    }

    /**
     * Given a list of variables and a boolean permutation,
     * encode the permutation as a formula
     *
     * @param variables an array of strings, each of which is a variable name.
     * @param permutation a BooleanArray that represents the permutation.
     * @return The formula that encodes the permutation.
     */
    public Formula encodePermutation(String[] variables, BooleanArray permutation) {

        ArrayList<Formula> literals = new ArrayList<>();

        for (int i = 0; i < variables.length; i++) {
            literals.add(f.literal(variables[i], permutation.get(i)));
        }

        return f.and(literals);
    }
}
