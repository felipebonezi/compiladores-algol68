package br.upe.poli.compiladores.algol68.core.util.AST;

public class DBCmdBreak extends DBCmd {

    private final TBreak tBreak;

    public DBCmdBreak(TBreak tBreak) {
        super();
        this.tBreak = tBreak;
    }

    public TBreak getTBreak() {
        return tBreak;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdBreak", level);

        builder.append(tBreak.toString(level + 1));

        return builder.toString();
    }
}
