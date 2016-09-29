package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

public class DArith extends AST {

    private final DTerm t1;
    private List<DTerm> terms;
    private List<TOPBasic> tops;

    public DArith(DTerm t1) {
        this.t1 = t1;
    }

    public DTerm getT1() {
        return t1;
    }

    public List<DTerm> getTerms() {
        return terms;
    }

    public List<TOPBasic> getTops() {
        return tops;
    }

    public void setTerms(List<DTerm> terms) {
        this.terms = terms;
    }

    public void setTops(List<TOPBasic> tops) {
        this.tops = tops;
    }
}
