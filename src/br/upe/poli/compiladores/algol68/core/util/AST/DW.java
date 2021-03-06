package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DW extends AST {

    private final DEXPR dexpr;
    private final List<DB> dbs;

    public DW(DEXPR dexpr, List<DB> dbs) {
        this.dexpr = dexpr;
        this.dbs = dbs;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    public List<DB> getDbs() {
        return dbs;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DW", level);

        builder.append(dexpr.toString(level + 1));

        if (dbs != null) {
            for (DB dvf : dbs) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDW(this, list);
    }
}
