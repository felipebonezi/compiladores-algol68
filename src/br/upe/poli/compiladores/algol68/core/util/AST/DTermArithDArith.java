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
}
