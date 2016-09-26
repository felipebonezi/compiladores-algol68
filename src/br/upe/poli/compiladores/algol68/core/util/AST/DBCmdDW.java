package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDW extends DBCmd {

    private final DW dw;

    public DBCmdDW(DW dw) {
        super();
        this.dw = dw;
    }

    public DW getDw() {
        return dw;
    }
}
