package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDR extends DBCmd {

    private final DR dr;

    public DBCmdDR(DR dr) {
        super();
        this.dr = dr;
    }

    public DR getDr() {
        return dr;
    }
}
