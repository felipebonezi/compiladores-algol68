package br.upe.poli.compiladores.algol68.core.util.AST;


import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;


public class DBCmdPrint extends DBCmd {

    private final TPrint tPrint;
    private final DEXPR dexpr;

    public DBCmdPrint(TPrint tPrint, DEXPR dexpr) {
        super();
        this.tPrint = tPrint;
        this.dexpr = dexpr;
    }

    public TPrint gettPrint() {
        return tPrint;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdPrint", level);

        builder.append(tPrint.toString(level + 1));
        builder.append(dexpr.toString(level + 1));

        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDBCmdPrint(this, list);
    }
}
