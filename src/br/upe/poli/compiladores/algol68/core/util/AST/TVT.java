package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.scanner.Token;

public class TVT extends T {

    public TVT(Token varType) {
        super(varType);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TVT", this, level);
        return builder.toString();
    }

}
