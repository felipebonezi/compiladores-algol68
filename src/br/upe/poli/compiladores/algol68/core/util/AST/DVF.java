package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

public abstract class DVF extends AST {

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDVF(this, list);
    }

}
