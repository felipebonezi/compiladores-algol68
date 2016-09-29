package br.upe.poli.compiladores.algol68.core.util.AST;

import java.util.List;

/**
 * Created by felipebonezi on 28/09/16.
 */
public class DTerm extends AST {

    private final DTermArith t1;
    private List<DTermArith> terms;
    private List<TOPFactor> tops;

    public DTerm(DTermArith t1) {
        this.t1 = t1;
    }

    public DTermArith getT1() {
        return t1;
    }

    public List<DTermArith> getTerms() {
        return terms;
    }

    public List<TOPFactor> getTops() {
        return tops;
    }

    public void setTerms(List<DTermArith> terms) {
        this.terms = terms;
    }

    public void setTops(List<TOPFactor> tops) {
        this.tops = tops;
    }

    @Override
    public String toString(int level) {
        StringBuilder builder = new StringBuilder();
        toStringHelper(builder, "DTerm", level);

        builder.append(t1.toString(level + 1));

        if (terms != null && tops != null) {
            for (int i = 0;i < terms.size();i++) {
                TOPFactor topFactor = tops.get(i);
                builder.append(topFactor.toString(level + 1));

                DTermArith dTermArith = terms.get(i);
                builder.append(dTermArith.toString(level + 1));
            }
        }

        return builder.toString();
    }

}
