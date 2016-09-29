package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.scanner.Token;

public class TID extends T {

    private DEXPR assignedExpr;

    public TID(Token id) {
        super(id);
    }

    public DEXPR getAssignedExpr() {
        return assignedExpr;
    }

    public void setAssignedExpr(DEXPR assignedExpr) {
        this.assignedExpr = assignedExpr;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "TID", this, level);

        if (assignedExpr != null) {
            builder.append(assignedExpr.toString(level + 1));
        }

        return builder.toString();
    }
}
