package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDW extends DBCmd {

    private final DW dw;

    public DBCmdDW(DW dw) {
        super();
        this.dw = dw;
    }

    public DW getDw() {
        return dw;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDW", level);

        builder.append(dw.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdDW(this, list);
    }
}
