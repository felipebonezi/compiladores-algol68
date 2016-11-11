package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DArith extends AST {

    private final DTerm t1;
    private List<DTerm> terms;
    private List<TOPBasic> tops;

    public DArith(DTerm t1) {
        this.t1 = t1;
    }

    public DTerm getT1() {
        return t1;
    }

    public List<DTerm> getTerms() {
        return terms;
    }

    public List<TOPBasic> getTops() {
        return tops;
    }

    public void setTerms(List<DTerm> terms) {
        this.terms = terms;
    }

    public void setTops(List<TOPBasic> tops) {
        this.tops = tops;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DArith", level);

        builder.append(t1.toString(level + 1));

        if (terms != null && tops != null) {
            for (int i = 0;i < terms.size();i++) {
                TOPBasic topBasic = tops.get(i);
                builder.append(topBasic.toString(level + 1));

                DTerm dTerm = terms.get(i);
                builder.append(dTerm.toString(level + 1));
            }
        }

        return builder.toString();
    }

    @Override
    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDArith(this, list);
    }
}
