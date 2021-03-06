package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;
import br.upe.poli.compiladores.algol68.core.scanner.Token;

import java.util.ArrayList;

public class TVTInt extends TVT {

    public TVTInt(Token token) {
        super(token);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TVTInt", this, level);
        return builder.toString();
    }

    @Override
    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitTVT(this, list);
    }

}
