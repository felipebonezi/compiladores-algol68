package br.upe.poli.compiladores.algol68.core.util.AST;

public class DR extends AST {

    private final DEXPR dexpr;

    public DR(DEXPR dexpr) {
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DR", level);

        builder.append(dexpr.toString(level + 1));

        return builder.toString();
    }

}
