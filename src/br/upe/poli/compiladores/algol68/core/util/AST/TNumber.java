package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.scanner.Token;

public class TNumber extends T {

    public TNumber(Token id) {
        super(id);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TNumber", this, level);
        return builder.toString();
    }
}
