package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;
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

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DF", level);

        builder.append(tid.toString(level + 1));

        if (params != null) {
            for (DP dvf : params) {
                builder.append(dvf.toString(level + 1));
            }
        }

        builder.append(returnType.toString(level + 1));

        if (bodies != null) {
            for (DB dvf : bodies) {
                builder.append(dvf.toString(level + 1));
            }
        }

        return builder.toString();
    }


    public Object visit(Visitor v, ArrayList<AST> list) throws SemanticException {
        return v.visitDF(this, list);
    }


}
