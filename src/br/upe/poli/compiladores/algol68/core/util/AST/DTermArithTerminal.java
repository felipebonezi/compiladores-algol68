package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 28/09/16.
 */
public class DTermArithTerminal extends DTermArith {

    private final T terminal;

    public DTermArithTerminal(T terminal) {
        super();
        this.terminal = terminal;
    }

    public T getTerminal() {
        return terminal;
    }

}
