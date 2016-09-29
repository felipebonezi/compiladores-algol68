package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 28/09/16.
 */
public class DTermArithDArith extends DTermArith {

    private final DArith dArith;

    public DTermArithDArith(DArith dArith) {
        super();
        this.dArith = dArith;
    }

    public DArith getdArith() {
        return dArith;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DTermArithDArith", level);

        builder.append(dArith.toString(level + 1));

        return builder.toString();
    }
}
