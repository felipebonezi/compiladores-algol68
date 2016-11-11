package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DidAtriExpr extends DIdAtri {

    private final DEXPR dexpr;

    public DidAtriExpr(TID tid, DEXPR dexpr) {
        super(tid);
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DIdAtriExpr", level);

        builder.append(dexpr.toString(level + 1));

        return builder.toString();
    }


    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDidAtriExpr(this, list);
    }
}
