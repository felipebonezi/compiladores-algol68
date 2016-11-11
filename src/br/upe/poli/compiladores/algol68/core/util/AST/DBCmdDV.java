package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
import java.util.List;

public class DBCmdDV extends DBCmd {

    private final List<DV> dvs;

    public DBCmdDV(List<DV> dvs) {
        super();
        this.dvs = dvs;
    }

    public List<DV> getDvs() {
        return dvs;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "CMD", level);

        if (dvs != null) {
            for (DV dvf : dvs) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdDV(this, list);
    }
}
