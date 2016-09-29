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

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DC", level);

        builder.append(dexpr.toString(level + 1));

        if (dbsIf != null) {
            for (DB dvf : dbsIf) {
                builder.append(dvf.toString(level + 1));
            }
        }

        if (dbsElse != null) {
            for (DB dvf : dbsElse) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }

}
