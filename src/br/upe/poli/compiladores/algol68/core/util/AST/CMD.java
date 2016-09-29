package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class CMD extends AST {

    private final List<? extends DVF> dvfs;

    public CMD(List<? extends DVF> dvfs) {
        this.dvfs = dvfs;
    }

    public List<? extends DVF> getDvfs() {
        return dvfs;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "CMD", level);

        if (dvfs != null) {
            for (DVF dvf : dvfs) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }

}
