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

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDW", level);

        builder.append(dw.toString(level + 1));

        return builder.toString();
    }
}
