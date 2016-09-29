package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

/**
 * Created by felipebonezi on 28/09/16.
 */
public class DTermArithFunc extends DTermArith {

    private final TID tid;
    private final List<DA> args;

    public TID getTid() {
        return tid;
    }

    public List<DA> getArgs() {
        return args;
    }

    public DTermArithFunc(TID tid, List<DA> args) {
        super();
        this.tid = tid;
        this.args = args;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DTermArithFunc", level);

        builder.append(tid.toString(level + 1));

        if (args != null) {
            for (DA dvf : args) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }
}
