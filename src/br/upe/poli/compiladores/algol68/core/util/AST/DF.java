package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class DF extends DVF {

    private final TID tid;
    private final List<DP> params;
    private final TVT returnType;
    private final List<DB> bodies;

    public DF(TID tid, List<DP> dps, TVT tvt, List<DB> bodies) {
        this.tid = tid;
        this.params = dps;
        this.returnType = tvt;
        this.bodies = bodies;
    }

    public TID getTid() {
        return tid;
    }

    public List<DP> getParams() {
        return params;
    }

    public TVT getReturnType() {
        return returnType;
    }

    public List<DB> getBodies() {
        return bodies;
    }
}
