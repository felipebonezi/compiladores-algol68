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

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DP", level);

        builder.append(varType.toString(level + 1));
        builder.append(tid.toString(level + 1));

        return builder.toString();
    }
}
