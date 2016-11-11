package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DEXPR extends AST {

    private final DArith d1;
    private List<DArith> terms;
    private List<TOPRel> tops;

    public DEXPR(DArith d1) {
        this.d1 = d1;
    }

    public DArith getD1() {
        return d1;
    }

    public List<DArith> getTerms() {
        return terms;
    }

    public void setTerms(List<DArith> terms) {
        this.terms = terms;
    }

    public List<TOPRel> getTops() {
        return tops;
    }

    public void setTops(List<TOPRel> tops) {
        this.tops = tops;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DEXPR", level);

        builder.append(d1.toString(level + 1));

        if (terms != null && tops != null) {
            for (int i = 0; i < terms.size(); i++) {
                TOPRel topRel = tops.get(i);
                DArith dArith = terms.get(i);

                builder.append(topRel.toString(level + 1));
                builder.append(dArith.toString(level + 1));
            }
        }

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDEXPR(this, list);
    }

}
