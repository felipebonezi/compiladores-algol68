package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

public class P extends AST {

    private final CMD cmd;

    public P(CMD cmd) {
        this.cmd = cmd;
    }

    public CMD getCmd() {
        return cmd;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "P", level);

        if (cmd != null) {
            builder.append(cmd.toString(level + 1));
        }

        return builder.toString();
    }


    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitP(this, list);
    }

}
