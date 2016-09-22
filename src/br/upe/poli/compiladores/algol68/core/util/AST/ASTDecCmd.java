package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class ASTDecCmd extends AST {

    private final List<ASTDecVarFunc> decs;

    public ASTDecCmd(List<ASTDecVarFunc> decs) {
        this.decs = decs;
    }

    public List<ASTDecVarFunc> getDecs() {
        return decs;
    }

}
