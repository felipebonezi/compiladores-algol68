package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;


public class DV extends DVF {

    private final TVT tvt;
    private final TID tid;

    public DV(TVT tvt, TID tid) {
        this.tvt = tvt;
        this.tid = tid;
    }

    public TVT getTvt() {
        return tvt;
    }

    public TID getTid() {
        return tid;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DV", level);

        builder.append(tvt.toString(level + 1));
        builder.append(tid.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDV(this, list);
    }

}
