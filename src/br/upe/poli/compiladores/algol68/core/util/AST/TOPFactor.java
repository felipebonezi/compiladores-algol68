package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.scanner.Token;

/**
 * Created by felipebonezi on 26/09/16.
 */
public class TOPFactor extends TOP {
    public TOPFactor(Token id) {
        super(id);
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TOPFactor", this, level);
        return builder.toString();
    }
}
