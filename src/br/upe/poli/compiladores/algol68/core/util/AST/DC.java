package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class DC extends AST {

    private final DEXPR dexpr;
    private final List<DB> dbsIf;
    private List<DB> dbsElse;

    public DC(DEXPR dexpr, List<DB> dbsIf) {
        this.dexpr = dexpr;
        this.dbsIf = dbsIf;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }

    public List<DB> getDbsIf() {
        return dbsIf;
    }

    public List<DB> getDbsElse() {
        return dbsElse;
    }

    public void setDbsElse(List<DB> dbsElse) {
        this.dbsElse = dbsElse;
    }
}
