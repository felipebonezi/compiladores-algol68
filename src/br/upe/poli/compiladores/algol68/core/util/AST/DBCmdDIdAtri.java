package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.util.AST.DBCmd;
import br.upe.poli.compiladores.algol68.core.util.AST.DIdAtri;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDIdAtri extends DBCmd {

    private final DIdAtri dIdAtri;

    public DBCmdDIdAtri(DIdAtri dIdAtri) {
        super();
        this.dIdAtri = dIdAtri;
    }

    public DIdAtri getdIdAtri() {
        return dIdAtri;
    }
}
