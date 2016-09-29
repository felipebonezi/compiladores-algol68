package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DBCmdDV extends DBCmd {

    private final List<DV> dvs;

    public DBCmdDV(List<DV> dvs) {
        super();
        this.dvs = dvs;
    }

    public List<DV> getDvs() {
        return dvs;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "CMD", level);

        if (dvs != null) {
            for (DV dvf : dvs) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }
}
