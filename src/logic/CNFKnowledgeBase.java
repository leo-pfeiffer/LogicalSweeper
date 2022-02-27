package logic;

import java.util.ArrayList;
import java.util.Arrays;
import model.board.Coord;
import model.board.View;
import org.sat4j.core.VecInt;
import org.sat4j.pb.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

public class CNFKnowledgeBase extends KnowledgeBase {

    private final CNFEncoder encoder = new CNFEncoder();

    public CNFKnowledgeBase(View view) {
        super(view);
    }

    /**
     * Get the current KBU using CNF encoding
     * */
    private int[][] getKBU() {

        // list of cells to consider for the KBU computation (cell with hint and unknown neighbor)
        ArrayList<Coord> contenders = this.getKBUContenders();

        // initialise list to add the CNF clauses to
        ArrayList<int[]> clauses = new ArrayList<>();

        for (Coord coord : contenders) {

            // get unknown neighbors and their integer identifiers
            ArrayList<Coord> unknownNeighbors = this.getUnknownNeighbors(coord);
            int[] identifiers = getCellIdentifiers(unknownNeighbors);

            // get the number of mines (k) in the unknown cells
            int clue = Character.getNumericValue(this.view.getCell(coord));
            int numDangers = this.view.countDangers(this.view.getAdjacentCoords(coord));
            int k = clue - numDangers;

            // encode that exactly k mines are located in adjacent unknown cells
            int[][] subClauses = encoder.exactly(identifiers, k);
            clauses.addAll(Arrays.asList(subClauses));
        }

        // convert to array of arrays
        int[][] kb = new int[clauses.size()][];
        for (int i = 0; i < clauses.size(); i++) {
            kb[i] = clauses.get(i);
        }
        return kb;
    }

    @Override
    public boolean checkEntailment(Coord cell, boolean phase) {
        int[][] kb = this.getKBU();
        int identifier = this.getCellIdentifier(cell);
        int[] identifierClause = {phase ? identifier : -identifier};

        int MAXVAR = view.getSize() * view.getSize();
        int NBCLAUSES = kb.length + 1;

        ISolver solver = SolverFactory.newDefault();

        solver.newVar(MAXVAR);
        solver.setExpectedNumberOfClauses(NBCLAUSES);

        // add clauses of the knowledge base
        for (int i = 0; i < NBCLAUSES - 1; i++) {
            int [] clause = kb[i];

            try {
                solver.addClause(new VecInt(clause));
            } catch (ContradictionException e) {
                throw new RuntimeException("Contradiction while adding clause " + Arrays.toString(clause));
            }
        }

        // add the clause to check entailment with the knowledge base
        try {
            solver.addClause(new VecInt(identifierClause));
            return solver.isSatisfiable();
        } catch (ContradictionException e) {
            // contradiction -> unsatisfiable
            return false;
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the integer identifier for a cell.
     * The integer identifier corresponds to the position of the cell on the board,
     * when counting the cells from top left to bottom right, row by row:
     * identifier = row * width + column + 1
     * */
    private int getCellIdentifier(Coord cell) {
        return cell.getRow() * view.getSize() + cell.getCol() + 1;
    }

    /**
     * Get the integer identifiers for an array list of cells.
     * */
    private int[] getCellIdentifiers(ArrayList<Coord> cells) {
        int[] identifiers = new int[cells.size()];
        for (int i = 0; i < cells.size(); i++) {
            Coord coord = cells.get(i);
            identifiers[i] = getCellIdentifier(coord);
        }
        return identifiers;
    }

}
