package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDC extends DBCmd {

    private final DC dc;

    public DBCmdDC(DC dc) {
        super();
        this.dc = dc;
    }

    public DC getDc() {
        return dc;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDC", level);

        builder.append(dc.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdDC(this, list);
    }
}
