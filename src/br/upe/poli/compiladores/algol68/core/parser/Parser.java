package br.upe.poli.compiladores.algol68.core.parser;

import br.upe.poli.compiladores.algol68.core.compiler.Properties;
import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.AST;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecCmd;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecFunc;
import br.upe.poli.compiladores.algol68.core.util.AST.ASTDecVarFunc;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;
import com.sun.deploy.security.ValidationState;
import com.sun.org.apache.regexp.internal.RE;

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
		this.scanner = new Scanner(Properties.PROGRAM_3);
	}

	/**
	 * Veririfes if the current token kind is the expected one
	 *
	 * @param kind
	 * @throws SyntacticException
	 * @throws LexicalException
	 */
	private void accept(int kind) throws SyntacticException, LexicalException {
		// If the current token kind is equal to the expected
        if (currentToken != null && currentToken.getKind() == kind) {
			// Gets next token
			acceptIt();
		} else {
			// If not
			// Raises an exception
			throw new SyntacticException("Deu gueba!!!!", this.currentToken);
		}
	}

	/**
	 * Gets next token
	 */
	private void acceptIt() throws LexicalException {
        System.out.print(this.currentToken.getSpelling());
        System.out.print(" ");
		this.currentToken = this.scanner.getNextToken();
	}

	/**
	 * Verifies if the source program is syntactically correct
	 *
	 * @throws SyntacticException
	 * @throws LexicalException
	 */
	public AST parse() throws SyntacticException, LexicalException {
		// program ::= BEGIN dec_cmd END EOT

        this.currentToken = this.scanner.getNextToken();

		accept(BEGIN);
		parseCmd();
		accept(END);
		accept(EOT);

		return null;
	}

	private void parseCmd() throws LexicalException, SyntacticException {
        // dec_cmd ::= (dec_var; | dec_func;)*

		while (this.currentToken.getKind() != END) {
			parseDecVarFunc();
		}
	}

	private void parseDecVarFunc() throws LexicalException, SyntacticException {
		int kind = this.currentToken.getKind();
		if (kind == PROC) {
			parseDecFunc();
		} else if (isVarType(kind)) {
			parseDecVar();
            accept(SEMICOLON);
		} else {
			error("Você não inicializou nenhuma variável ou função - i.e. (dec_cmd ::= (dec_var; | dec_func;)*)");
		}
	}

	private void parseDecFunc() throws SyntacticException, LexicalException {
        // dec_func ::= PROC identifier = (dec_param?) (var_type | VOID) : BEGIN dec_bodies END
        // dec_param ::= var_type identifier (,dec_param)?

        acceptIt();
        accept(ID);
		accept(OP_REL);
		accept(L_PAR);

        if (isVarType(this.currentToken.getKind())) {
            acceptIt();
            accept(ID);

            while (this.currentToken.getKind() == COMMA) {
                acceptIt();

                if (isVarType(this.currentToken.getKind())) {
                    acceptIt();
                }

                accept(ID);
            }
        }

        accept(R_PAR);

        if (isVarType(this.currentToken.getKind())) {
            acceptIt();
        } else {
            accept(VOID);
        }

        accept(TWO_DOTS);
        accept(BEGIN);
        parseDecBodies();
        accept(END);
	}

    private void parseDecBodies() throws SyntacticException, LexicalException {
        // dec_bodies ::= (dec_cmd_body;)*

        while (isCmdBody(this.currentToken.getKind())) {
            parseDecCmdBody();
            accept(SEMICOLON);
        }
    }

    private void parseDecCmdBody() throws LexicalException, SyntacticException {
        // dec_cmd_body ::= dec_var | dec_cond | dec_while | dec_return | dec_id_atri | PRINT dec_expr | BREAK | CONTINUE

        int kind = this.currentToken.getKind();
        switch (kind) {
            case BREAK:
            case CONTINUE:
                acceptIt();
                break;

            case PRINT:
                acceptIt();
                parseDecExpr();
                break;

            case INT:
            case BOOL:
                parseDecVar();
                break;

            case IF:
                parseDecCond();
                break;

            case WHILE:
                parseDecWhile();
                break;

            case RETURN:
                parseDecReturn();
                break;

            default:
                parseDecIdAtri();
                break;
        }
    }

    private void parseDecIdAtri() throws SyntacticException, LexicalException {
        // dec_id_atri ::= identifier ( (dec_args?) | := (number | bool) )

        accept(ID);
        if (this.currentToken.getKind() == L_PAR) {
            acceptIt();
            parseDecArgs();
            accept(R_PAR);
        } else {
            accept(ASSIGN);
            parseDecExpr();
        }
    }

    private void parseDecReturn() throws LexicalException, SyntacticException {
        // dec_return ::= RETURN dec_expr

        acceptIt();
        parseDecExpr();
    }

    private void parseDecWhile() throws LexicalException, SyntacticException {
        // dec_while ::= WHILE dec_expr DO dec_bodies OD

        acceptIt();
        parseDecExpr();
        accept(DO);
        parseDecBodies();
        accept(OD);
    }

    private void parseDecCond() throws LexicalException, SyntacticException {
        // dec_cond ::= IF dec_expr THEN dec_bodies (ELSE dec_bodies)? FI

        acceptIt();
        parseDecExpr();
        accept(THEN);
        parseDecBodies();

        if (this.currentToken.getKind() == ELSE) {
            acceptIt();
            parseDecBodies();
        }

        accept(FI);
    }

    private void parseDecVar() throws SyntacticException, LexicalException {
        // dec_var ::= var_type identifier (:= dec_expr)? (dec_n_vars)*
        // dec_n_vars ::= ,identifier (:= dec_expr)? --> Corrige na gramática isso!

        acceptIt();
        accept(ID);
        if (this.currentToken.getKind() == ASSIGN) {
            acceptIt();
            parseDecExpr();
        }

        while (this.currentToken.getKind() == COMMA) {
            acceptIt();
            accept(ID);

            if (this.currentToken.getKind() == ASSIGN) {
                acceptIt();
                parseDecExpr();
            }
        }
    }

    private void parseDecExpr() throws LexicalException, SyntacticException {
        // dec_expr ::= dec_arith (op_rel dec_arith)

        parseDecArith();
        if (this.currentToken.getKind() == OP_REL) {
            acceptIt();
            parseDecArith();
        }
    }

    private void parseDecArith() throws LexicalException, SyntacticException {
        // dec_arith ::= dec_term (op_basic dec_term)*

        parseDecTerm();
        while (this.currentToken.getKind() == OP_BASIC) {
            acceptIt();
            parseDecTerm();
        }
    }

    private void parseDecTerm() throws LexicalException, SyntacticException {
        // dec_term ::= dec_term_arith (op_factor dec_term_arith)*

        parseDecTermArith();
        while (this.currentToken.getKind() == OP_FACTOR) {
            acceptIt();
            parseDecTermArith();
        }
    }

    private void parseDecTermArith() throws LexicalException, SyntacticException {
        // dec_term_arith ::= dec_id | number | bool | (dec_arith)
        // dec_id ::= identifier ((dec_args?))?

        int kind = this.currentToken.getKind();
        switch (kind) {
            case NUMBER:
            case TRUE:
            case FALSE:
                acceptIt();
                break;

            case ID:
                acceptIt();
                if (this.currentToken.getKind() == L_PAR) {
                    acceptIt();
                    parseDecArgs();
                    accept(R_PAR);
                }
                break;

            default:
                accept(L_PAR);
                parseDecArith();
                accept(R_PAR);
                break;
        }
    }

    private void parseDecArgs() throws LexicalException, SyntacticException {
        // dec_args ::=  dec_expr  (,dec_expr)*

        parseDecExpr();
        while (this.currentToken.getKind() == COMMA) {
            acceptIt();
            parseDecExpr();
        }
    }

    // region Métodos auxiliares
    private boolean isCmdBody(int kind) {
        return kind == BREAK || kind == CONTINUE || kind == PRINT || kind == INT || kind == BOOL || kind == IF || kind == WHILE || kind == RETURN || kind == ID;
    }

    private boolean isVarType(int kind) {
        return kind == INT || kind == BOOL;
    }

    private void error(String name) throws SyntacticException {
		throw new SyntacticException(name, currentToken);
	}
	// endregion

}
