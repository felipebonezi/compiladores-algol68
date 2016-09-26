package br.upe.poli.compiladores.algol68.core.util.AST;

public abstract class DIdAtri extends AST {

    private final TID id;

    protected DIdAtri(TID id) {
        this.id = id;
    }

    public TID getId() {
        return id;
    }

}
