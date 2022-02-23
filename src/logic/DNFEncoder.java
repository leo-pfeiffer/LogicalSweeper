package logic;

import java.util.ArrayList;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;

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
}
