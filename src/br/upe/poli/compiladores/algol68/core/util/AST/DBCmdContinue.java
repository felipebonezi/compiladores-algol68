package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

public class DBCmdContinue extends DBCmd {

    private final TContinue tContinue;

    public DBCmdContinue(TContinue tContinue) {
        super();
        this.tContinue = tContinue;
    }

    public TContinue getTContinue() {
        return tContinue;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdContinue", level);

        builder.append(tContinue.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdContinue(this, list);
    }

}
