package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;
import br.upe.poli.compiladores.algol68.core.scanner.Token;

import java.util.ArrayList;

public class TPrint extends T {

    public TPrint(Token id) {
        super(id);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TPrint", this, level);
        return builder.toString();
    }

    @Override
    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitTPrint(this, list);
    }

}
