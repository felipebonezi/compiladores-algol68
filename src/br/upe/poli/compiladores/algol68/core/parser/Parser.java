package br.upe.poli.compiladores.algol68.core.parser;

import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.AST;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecCmd;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecFunc;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecVarFunc;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;
import com.sun.deploy.security.ValidationState;

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
	 *
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
	 *
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
			decVarFunc = parseDecVar();
		} else if (kind == INT || kind == BOOL) {
			decVarFunc = parseDecFunc();
		} else {
			error("Você não inicializou nenhuma variável ou função - i.e. (dec_cmd ::= (dec_var; | dec_func;)*)");
		}

		return decVarFunc;
	}

	private ASTDecVarFunc parseDecFunc() throws SyntacticException, LexicalException {

        ASTDecFunc decFunc = null;
//		dec_func ::= PROC identifier = (dec_param?)  (var_type | VOID) : BEGIN dec_bodies END
		int kind = this.currentToken.getKind();
		ArrayList<TType> argsType = new ArrayList<TType>();
		ArrayList<TID> argsId = new ArrayList<TID>();


		if (Kind == ID) {
			name = new TID(currentToken.getSpelling());
		} else {
			error("Nao ha um id");
		}

		accept(ASSIGN);
		accept(L_PAR);
		if (kind == R_PAR) {
			acceptIt();
		} else {
			do {
				if (kind == args) {
					argsType.add(new TT);
				} else {
					error("erro");
				}

				if (kind == ID) {
					argsId.add(new TID(currentToken.getSpelling()));
				} else {
					error("erro");
				}
			}while (kind == COMMA && acceptIt());
            accept(GrammarSymbols.R_PAR);
		}afs

		accept(TWO_DOTS);
        accept(BEGIN);
        ASTDecFunc = parseDecBodies();
        accept(END);



	}


	private ASTDecVarFunc parseDecVar() throws SyntacticException, LexicalException {

        ArrayList<TID> id = new ArrayList<TID>();
        int kind = this.currentToken.getKind();


        return null;
	}

    private Command parseDecBodies() throws SyntacticException, LexicalException {

        Command command = null;
        int kind = this.currentToken.getKind();

        do {
            if (kind == )
        }


        return null;
    }

	private void error(String name) throws SyntacticException {
		throw new SyntacticException(name, currentToken);
	}
}
