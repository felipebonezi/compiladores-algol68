package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;
import br.upe.poli.compiladores.algol68.core.scanner.Token;

import java.util.ArrayList;

/**
 * Created by felipebonezi on 26/09/16.
 */
public class TOPBasic extends TOP {

    public TOPBasic(Token id) {
        super(id);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TOPBasic", this, level);
        return builder.toString();
    }

    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitTOPBasic(this, list);
    }

}
