package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DIdAtriDA extends DIdAtri {

    private final List<DA> das;

    public DIdAtriDA(TID tid, List<DA> das) {
        super(tid);
        this.das = das;
    }

    public List<DA> getDas() {
        return das;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DIdAtriDA", level);

        if (das != null) {
            for (DA dvf : das) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDIdAtriDA(this, list);
    }
}
