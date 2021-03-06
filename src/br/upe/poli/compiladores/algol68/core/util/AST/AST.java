package br.upe.poli.compiladores.algol68.core.util.AST;

import br.upe.poli.compiladores.algol68.core.checker.SemanticException;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.checker.Visitor;

import java.util.ArrayList;

/**
 * AST class
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public abstract class AST {

    private String type = null;
    private AST declaration = null;

	public String getSpaces(int level) {
		StringBuffer str = new StringBuffer();
		while( level>0 ) {
			str.append(" ");
			level--;
		}
		return str.toString();
	}

    public abstract String toString(int level);

    protected void toStringHelper(StringBuilder builder, String tag, int level) {
        builder.append("|");
        for (int i = 1; i <= level; i++)
            builder.append("-");

        builder.append(" ");
        builder.append(tag);
        builder.append("\n");
    }

    protected void toStringHelper(StringBuilder builder, String tag, T terminal, int level) {
        builder.append("|");
        for (int i = 1; i <= level; i++)
            builder.append("-");

        builder.append(" ");
        builder.append(tag);
        builder.append(" ");

        Token id = terminal.getId();
        builder.append("(");
        builder.append(id);
        builder.append(")");

        builder.append("\n");
    }

    public abstract Object visit(Visitor v, ArrayList<AST> list) throws SemanticException;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AST getDeclaration() {
        return declaration;
    }

    public void setDeclaration(AST declaration) {
        this.declaration = declaration;
    }

}
