package br.upe.poli.compiladores.algol68.core.util.AST;

public class P extends AST {

    private final CMD cmd;

    public P(CMD cmd) {
        this.cmd = cmd;
    }

    public CMD getCmd() {
        return cmd;
    }

}
