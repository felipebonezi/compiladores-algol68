package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class DW extends AST {

    private final DEXPR dexpr;
    private final List<DB> dbs;

    public DW(DEXPR dexpr, List<DB> dbs) {
        this.dexpr = dexpr;
        this.dbs = dbs;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    public List<DB> getDbs() {
        return dbs;
    }
}
