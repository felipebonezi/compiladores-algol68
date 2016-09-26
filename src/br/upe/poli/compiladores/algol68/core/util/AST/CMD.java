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

}
