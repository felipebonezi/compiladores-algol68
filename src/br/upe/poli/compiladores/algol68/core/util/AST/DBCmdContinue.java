package br.upe.poli.compiladores.algol68.core.util.AST;

public class DBCmdContinue extends DBCmd {

    private final TContinue tContinue;

    public DBCmdContinue(TContinue tContinue) {
        super();
        this.tContinue = tContinue;
    }

    public TContinue getTContinue() {
        return tContinue;
    }

}
