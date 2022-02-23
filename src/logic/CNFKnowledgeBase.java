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

    private int[][] getKBU() {
        ArrayList<Coord> contenders = this.getKBUContenders();

        ArrayList<int[]> clauses = new ArrayList<>();

        for (Coord coord : contenders) {
            ArrayList<Coord> unknownNeighbors = this.getUnknownNeighbors(coord);
            int[] identifiers = getCellIdentifiers(unknownNeighbors);
            int clue = Character.getNumericValue(this.view.getCell(coord));
            int numDangers = this.view.countDangers(this.view.getAdjacentCoords(coord));

            int[][] subClauses = encoder.exactly(identifiers, clue - numDangers);
            clauses.addAll(Arrays.asList(subClauses));
        }

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

        for (int i = 0; i < NBCLAUSES; i++) {
            int [] clause = kb[i];

            try {
                solver.addClause(new VecInt(clause));
            } catch (ContradictionException e) {
                throw new RuntimeException("Contradiction while adding clause " + Arrays.toString(clause));
            }
        }

        try {
            solver.addClause(new VecInt(identifierClause));
        } catch (ContradictionException e) {
            throw new RuntimeException("Contradiction while adding clause " + Arrays.toString(identifierClause));
        }

        try {
            return solver.isSatisfiable();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private int getCellIdentifier(Coord coord) {
        return coord.getRow() * view.getSize() + coord.getCol() + 1;
    }

    private int[] getCellIdentifiers(ArrayList<Coord> cells) {
        int[] identifiers = new int[cells.size()];
        for (int i = 0; i < cells.size(); i++) {
            Coord coord = cells.get(i);
            identifiers[i] = getCellIdentifier(coord);
        }
        return identifiers;
    }

}
