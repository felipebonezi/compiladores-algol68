package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

public class DR extends AST {

    private final DEXPR dexpr;

    public DR(DEXPR dexpr) {
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DR", level);

        builder.append(dexpr.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDR(this, list);
    }
}
