package br.upe.poli.compiladores.algol68.core.util.AST;

public class DP extends AST {

    private final TVT varType;
    private final TID tid;

    public DP(TVT tvt, TID tid) {
        this.varType = tvt;
        this.tid = tid;
    }

    public TID getTid() {
        return tid;
    }

    public TVT getVarType() {
        return varType;
    }
}
