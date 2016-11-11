package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

public abstract class DIdAtri extends AST {

    private final TID id;

    protected DIdAtri(TID id) {
        this.id = id;
    }

    public TID getId() {
        return id;
    }

    @Override
    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDIdAtri(this, list);
    }

}
