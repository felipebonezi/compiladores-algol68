package br.upe.poli.compiladores.algol68.core.util.AST;

public class DA extends AST {

    private final DEXPR dexpr;

    public DA(DEXPR dexpr) {
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }
}
