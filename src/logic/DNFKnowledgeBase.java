package logic;

import java.util.ArrayList;
import model.board.Coord;
import model.board.View;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;

/**
 * Knowledge Base extension using DNF encoding.
 */
public class DNFKnowledgeBase extends KnowledgeBase {

    private final FormulaFactory f = new FormulaFactory();
    private final DNFEncoder encoder = new DNFEncoder(f);

    public DNFKnowledgeBase(View view) {
        super(view);
    }

    @Override
    public boolean checkEntailment(Coord cell, boolean phase) {

        Literal literal = f.literal(coordToLiteralString(cell), phase);

        // check entailment of KB |= cell using a MiniSat solver
        Formula entailment = f.and(getKBU(), literal);
        SATSolver miniSat = MiniSat.miniSat(f);
        miniSat.add(entailment);

        // If MiniSat returns 0 -> entails, else does not entail
        return miniSat.sat().ordinal() == 0;
    }

    /**
     * Get the current KBU using DNF encoding
     */
    private Formula getKBU() {

        // list of cells to consider for the KBU computation (cell with hint and unknown neighbor)
        ArrayList<Coord> contenders = this.getKBUContenders();

        // initialise list to add the formulae to
        ArrayList<Formula> formulae = new ArrayList<>();

        for (Coord coord : contenders) {

            // get unknown neighbors and their string literals
            ArrayList<Coord> unknownNeighbors = this.getUnknownNeighbors(coord);
            String[] literals = this.stringifyLiterals(unknownNeighbors);

            // get the number of mines (k) in the unknown cells
            int k = getAdjacentRemainingDangerCount(coord);

            // encode the literals allowing exactly k mines in the unknown neighborhood of the cell
            Formula formula = encoder.encode(literals, k);
            formulae.add(formula);
        }

        // conjunction of sub-formulae
        return f.and(formulae);
    }

    /**
     * Convert an array list of cells to their string literal names based on row and column.
     */
    private String[] stringifyLiterals(ArrayList<Coord> cells) {
        String[] literals = new String[cells.size()];
        for (int i = 0; i < cells.size(); i++) {
            Coord coord = cells.get(i);
            literals[i] = coordToLiteralString(coord);
        }
        return literals;
    }

    /**
     * Convert a cell to its string literal name based on row and column.
     */
    private String coordToLiteralString(Coord coord) {
        return "D_" + coord.getRow() + "_" + coord.getCol();
    }
}
