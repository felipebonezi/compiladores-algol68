package br.upe.poli.compiladores.algol68.core.util.AST;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDC extends DBCmd {

    private final DC dc;

    public DBCmdDC(DC dc) {
        super();
        this.dc = dc;
    }

    public DC getDc() {
        return dc;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DBCmdDC", level);

        builder.append(dc.toString(level + 1));

        return builder.toString();
    }
}
