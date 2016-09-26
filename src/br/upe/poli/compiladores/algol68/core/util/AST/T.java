package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.scanner.Token;

public abstract class T extends AST {

    private final Token id;

    protected T(Token id) {
        this.id = id;
    }

    public Token getId() {
        return id;
    }
}
