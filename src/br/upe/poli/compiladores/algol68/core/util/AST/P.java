package br.upe.poli.compiladores.algol68.core.util.AST;

public class P extends AST {

    private final CMD cmd;

    public P(CMD cmd) {
        this.cmd = cmd;
    }

    public CMD getCmd() {
        return cmd;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "P", level);

        if (cmd != null) {
            builder.append(cmd.toString(level + 1));
        }

        return builder.toString();
    }

}
