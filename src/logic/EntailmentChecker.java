package logic;

import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;

public class EntailmentChecker {

    private final KnowledgeBase kb;

    public EntailmentChecker(KnowledgeBase kb) {
        this.kb = kb;
    }

    public boolean checkEntailment(String literal, boolean phase) {
        FormulaFactory f = kb.getF();
        return checkEntailment(f.literal(literal, phase));
    }

    public boolean checkEntailment(Literal literal) {
        FormulaFactory f = kb.getF();

        Formula entailment = f.and(kb.getKBU(), literal);

        SATSolver miniSat = MiniSat.miniSat(f);
        miniSat.add(entailment);
        return miniSat.sat().ordinal() == 0;
    }
}
