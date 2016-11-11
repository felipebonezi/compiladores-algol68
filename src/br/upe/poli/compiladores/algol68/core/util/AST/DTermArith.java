package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 28/09/16.
 */
public abstract class DTermArith extends AST {

    @Override
    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDTermArith(this, list);
    }

}
