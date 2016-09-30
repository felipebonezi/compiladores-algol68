package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 28/09/16.
 */
public class DTermArithDExpr extends DTermArith {

    private final DEXPR dexpr;

    public DTermArithDExpr(DEXPR dexpr) {
        super();
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DTermArithDExpr", level);

        builder.append(dexpr.toString(level + 1));

        return builder.toString();
    }
}
