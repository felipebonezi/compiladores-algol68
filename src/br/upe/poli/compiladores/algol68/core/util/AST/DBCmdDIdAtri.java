package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDIdAtri extends DBCmd {

    private final DIdAtri dIdAtri;

    public DBCmdDIdAtri(DIdAtri dIdAtri) {
        super();
        this.dIdAtri = dIdAtri;
    }

    public DIdAtri getdIdAtri() {
        return dIdAtri;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDIdAtri", level);

        builder.append(dIdAtri.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdIdAtri(this, list);
    }
}
