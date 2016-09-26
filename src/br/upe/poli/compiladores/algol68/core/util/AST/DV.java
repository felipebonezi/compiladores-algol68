package br.upe.poli.compiladores.algol68.core.util.AST;

public class DV extends DVF {

    private final TVT tvt;
    private final TID tid;

    public DV(TVT tvt, TID tid) {
        this.tvt = tvt;
        this.tid = tid;
    }

}
