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

}
