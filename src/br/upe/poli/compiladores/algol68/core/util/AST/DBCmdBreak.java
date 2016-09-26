package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.util.AST.DBCmd;
import br.upe.poli.compiladores.algol68.core.util.AST.TBreak;

public class DBCmdBreak extends DBCmd {

    private final TBreak tBreak;

    public DBCmdBreak(TBreak tBreak) {
        super();
        this.tBreak = tBreak;
    }

    public TBreak getTBreak() {
        return tBreak;
    }
}
