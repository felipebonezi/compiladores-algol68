package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DEXPR extends AST {

    private final DArith d1;
    private DArith d2;
    private TOPRel topRel;

    public DEXPR(DArith d1) {
        this.d1 = d1;
    }

    public DArith getD1() {
        return d1;
    }

    public DArith getD2() {
        return d2;
    }

    public TOPRel getTopRel() {
        return topRel;
    }

    public void setD2(DArith d2) {
        this.d2 = d2;
    }

    public void setTopRel(TOPRel topRel) {
        this.topRel = topRel;
    }
}
