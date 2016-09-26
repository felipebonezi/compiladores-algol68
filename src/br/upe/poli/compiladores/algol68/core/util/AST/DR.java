package br.upe.poli.compiladores.algol68.core.util.AST;

public class DR extends AST {

    private final DEXPR dexpr;

    public DR(DEXPR dexpr) {
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }
}
