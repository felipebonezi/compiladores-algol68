package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

/**
 * Created by felipebonezi on 25/09/16.
 */
public class DIdAtriDA extends DIdAtri {

    private final List<DA> das;

    public DIdAtriDA(TID tid, List<DA> das) {
        super(tid);
        this.das = das;
    }

    public List<DA> getDas() {
        return das;
    }
}
