package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDR extends DBCmd {

    private final DR dr;

    public DBCmdDR(DR dr) {
        super();
        this.dr = dr;
    }

    public DR getDr() {
        return dr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDR", level);

        builder.append(dr.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdDR(this, list);
    }


}
