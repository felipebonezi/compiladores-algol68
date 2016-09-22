package br.upe.poli.compiladores.algol68.core.parser;

import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.AST;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecCmd;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecFunc;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecVarFunc;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;

import java.util.ArrayList;
import java.util.List;

import static br.upe.poli.compiladores.algol68.helpers.GrammarSymbols.*;

/**
 * Parser class
 * @version 2010-august-29
 * @discipline Projeto de Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Parser {

	// The current token
	private Token currentToken = null;
	// The scanner
	private Scanner scanner = null;
	
	/**
	 * Parser constructor
	 */
	public Parser() {
		// Initializes the scanner object
		this.scanner = new Scanner();
	}
	
	/**
	 * Veririfes if the current token kind is the expected one
	 * @param kind
	 * @throws SyntacticException
	 * @throws LexicalException
	 */ //TODO
	private void accept(int kind) throws SyntacticException, LexicalException {
		// If the current token kind is equal to the expected
		if ((currentToken == null && kind == BEGIN)
				|| currentToken != null && currentToken.getKind() == kind) {
			// Gets next token
			this.currentToken = this.scanner.getNextToken();
		// If not
		} else {
			// Raises an exception
			throw new SyntacticException("Deu gueba!!!!", this.currentToken);
		}
	}
	
	/**
	 * Gets next token
	 */ //TODO
	private void acceptIt() throws LexicalException {
		this.currentToken = this.scanner.getNextToken();
	}

	/**
	 * Verifies if the source program is syntactically correct
	 * @throws SyntacticException
	 * @throws LexicalException
	 */ //TODO
	public AST parse() throws SyntacticException, LexicalException {
		// BEGIN
		accept(BEGIN);

		// dec_cmd
		AST ast = parseCmd();

		// END
		accept(END);

		// EOT
		accept(EOT);

		return ast;
	}

	private ASTDecCmd parseCmd() throws LexicalException, SyntacticException {
        List<ASTDecVarFunc> decs = new ArrayList<>();

        ASTDecVarFunc decVarFunc;
        while (this.currentToken.getKind() != EOT) {
            decVarFunc = parseDecVarFunc();
            decs.add(decVarFunc);
        }
		return new ASTDecCmd(decs);
	}

    private ASTDecVarFunc parseDecVarFunc() throws LexicalException, SyntacticException {
        ASTDecVarFunc decVarFunc = null;

        // dec_func
        int kind = this.currentToken.getKind();
        if (kind == PROC) {
            decVarFunc = parseDecFunc();
        } else if (kind == INT || kind == BOOL) {

        } else {
            throw new SyntacticException("Você não inicializou nenhuma variável ou função - i.e. (dec_cmd ::= (dec_var; | dec_func;)*)", this.currentToken);
        }

        return decVarFunc;
    }

    private ASTDecVarFunc parseDecFunc() {
        ASTDecFunc decFunc = null;
        return decFunc;
    }

}
