package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DidAtriExpr extends DIdAtri {

    private final DEXPR dexpr;

    public DidAtriExpr(TID tid, DEXPR dexpr) {
        super(tid);
        this.dexpr = dexpr;
    }

    public DEXPR getDexpr() {
        return dexpr;
    }
}
