package br.upe.poli.compiladores.algol68.core.parser;

import br.upe.poli.compiladores.algol68.core.compiler.Properties;
import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.*;

import java.util.ArrayList;
import java.util.Collections;
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
		CMD cmd = parseCmd();
		accept(END);
		accept(EOT);

		return new P(cmd);
	}

	// region Métodos de Parse.
	private CMD parseCmd() throws LexicalException, SyntacticException {
        // dec_cmd ::= (dec_var; | dec_func;)*

        List<DVF> dvfs = new ArrayList<>();
		while (this.currentToken.getKind() != END) {
			dvfs.addAll(parseDecVarFunc());
		}

		return new CMD(dvfs);
	}

	private List<? extends DVF> parseDecVarFunc() throws LexicalException, SyntacticException {
        List<? extends DVF> dvfs = null;

		int kind = this.currentToken.getKind();
		if (kind == PROC) {
			dvfs = Collections.singletonList(parseDecFunc());
		} else if (isVarType(kind)) {
            dvfs = parseDecVar();
            accept(SEMICOLON);
		} else {
			error("Você não inicializou nenhuma variável ou função - i.e. (dec_cmd ::= (dec_var; | dec_func;)*)");
		}

		return dvfs;
	}

	private DF parseDecFunc() throws SyntacticException, LexicalException {
        // dec_func ::= PROC identifier = (dec_param?) (var_type | VOID) : BEGIN dec_bodies END
        // dec_param ::= var_type identifier (,dec_param)?

        acceptIt();

        TID tid = new TID(this.currentToken);
        accept(ID);
		accept(OP_REL);
		accept(L_PAR);

        List<DP> dps = null;
        if (isVarType(this.currentToken.getKind())) {
            dps = new ArrayList<>();

            TVT tvt = new TVT(this.currentToken);
            acceptIt();

            TID tid1 = new TID(this.currentToken);
            accept(ID);
            dps.add(new DP(tvt, tid1));

            while (this.currentToken.getKind() == COMMA) {
                acceptIt();

                if (isVarType(this.currentToken.getKind())) {
                    tvt = new TVT(this.currentToken);
                    acceptIt();
                }

                TID tidN = new TID(this.currentToken);
                accept(ID);

                dps.add(new DP(tvt, tidN));
            }
        }

        accept(R_PAR);

        TVT tvt;
        if (isVarType(this.currentToken.getKind())) {
            tvt = new TVT(this.currentToken);
            acceptIt();
        } else {
            tvt = new TVTVoid(this.currentToken);
            accept(VOID);
        }

        accept(TWO_DOTS);
        accept(BEGIN);
        List<DB> bodies = parseDecBodies();
        accept(END);

        return new DF(tid, dps, tvt, bodies);
	}

    private List<DB> parseDecBodies() throws SyntacticException, LexicalException {
        // dec_bodies ::= (dec_cmd_body;)*

        List<DB> dbs = new ArrayList<>();
        while (isCmdBody(this.currentToken.getKind())) {
            dbs.add(parseDecCmdBody());
            accept(SEMICOLON);
        }

        return dbs;
    }

    private DBCmd parseDecCmdBody() throws LexicalException, SyntacticException {
        // dec_cmd_body ::= dec_var | dec_cond | dec_while | dec_return | dec_id_atri | PRINT dec_expr | BREAK | CONTINUE

        DBCmd dbCmd;
        int kind = this.currentToken.getKind();
        switch (kind) {
            case BREAK:
                dbCmd = new DBCmdBreak(new TBreak(this.currentToken));
                acceptIt();
                break;

            case CONTINUE:
                dbCmd = new DBCmdContinue(new TContinue(this.currentToken));
                acceptIt();
                break;

            case PRINT:
                TPrint tPrint = new TPrint(this.currentToken);
                acceptIt();
                DEXPR dexpr = parseDecExpr();

                dbCmd = new DBCmdPrint(tPrint, dexpr);
                break;

            case INT:
            case BOOL:
                List<DV> dvs = parseDecVar();
                dbCmd = new DBCmdDV(dvs);
                break;

            case IF:
                DC dc = parseDecCond();
                dbCmd = new DBCmdDC(dc);
                break;

            case WHILE:
                DW dw = parseDecWhile();
                dbCmd = new DBCmdDW(dw);
                break;

            case RETURN:
                DR dr = parseDecReturn();
                dbCmd = new DBCmdDR(dr);
                break;

            default:
                DIdAtri dIdAtri = parseDecIdAtri();
                dbCmd = new DBCmdDIdAtri(dIdAtri);
                break;
        }

        return dbCmd;
    }

    private DIdAtri parseDecIdAtri() throws SyntacticException, LexicalException {
        // dec_id_atri ::= identifier ( (dec_args?) | := (number | bool) )

        TID tid = new TID(this.currentToken);
        accept(ID);

        DIdAtri dIdAtri;
        if (this.currentToken.getKind() == L_PAR) {
            acceptIt();
            List<DA> das = parseDecArgs();
            accept(R_PAR);

            dIdAtri = new DIdAtriDA(tid, das);
        } else {
            accept(ASSIGN);
            DEXPR dexpr = parseDecExpr();

            dIdAtri = new DidAtriExpr(tid, dexpr);
        }

        return dIdAtri;
    }

    private DR parseDecReturn() throws LexicalException, SyntacticException {
        // dec_return ::= RETURN dec_expr

        acceptIt();
        DEXPR dexpr = parseDecExpr();

        return new DR(dexpr);
    }

    private DW parseDecWhile() throws LexicalException, SyntacticException {
        // dec_while ::= WHILE dec_expr DO dec_bodies OD

        acceptIt();
        DEXPR dexpr = parseDecExpr();
        accept(DO);
        List<DB> dbs = parseDecBodies();
        accept(OD);

        return new DW(dexpr, dbs);
    }

    private DC parseDecCond() throws LexicalException, SyntacticException {
        // dec_cond ::= IF dec_expr THEN dec_bodies (ELSE dec_bodies)? FI

        acceptIt();
        DEXPR dexpr = parseDecExpr();
        accept(THEN);
        List<DB> dbsIf = parseDecBodies();

        DC dc = new DC(dexpr, dbsIf);

        if (this.currentToken.getKind() == ELSE) {
            acceptIt();
            List<DB> dbsElse = parseDecBodies();
            dc.setDbsElse(dbsElse);
        }

        accept(FI);
        return dc;
    }

    private List<DV> parseDecVar() throws SyntacticException, LexicalException {
        // dec_var ::= var_type identifier (:= dec_expr)? (dec_n_vars)*
        // dec_n_vars ::= ,identifier (:= dec_expr)? --> Corrige na gramática isso!

        List<DV> dvs = new ArrayList<>();

        TVT tvt = new TVT(this.currentToken);
        acceptIt();

        TID tid = new TID(this.currentToken);
        accept(ID);

        DEXPR dexpr1 = null;
        if (this.currentToken.getKind() == ASSIGN) {
            acceptIt();
            dexpr1 = parseDecExpr();
        }
        tid.setAssignedExpr(dexpr1);
        dvs.add(new DV(tvt, tid));

        while (this.currentToken.getKind() == COMMA) {
            acceptIt();

            TID tidN = new TID(this.currentToken);
            accept(ID);

            DEXPR dexprN = null;
            if (this.currentToken.getKind() == ASSIGN) {
                acceptIt();
                dexprN = parseDecExpr();
            }
            tidN.setAssignedExpr(dexprN);
            dvs.add(new DV(tvt, tidN));
        }

        return dvs;
    }

    private DEXPR parseDecExpr() throws LexicalException, SyntacticException {
        // dec_expr ::= dec_arith (op_rel dec_arith)?

        parseDecArith();

        if (this.currentToken.getKind() == OP_REL) {
            acceptIt();
            parseDecArith();
        }

        return new DEXPR();
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
                T t = new TNumber(this.currentToken);
                acceptIt();
                break;

            case TRUE:
            case FALSE:
                acceptIt();
                break;

            case ID:
                parseDecId();
                break;

            default:
                accept(L_PAR);
                parseDecArith();
                accept(R_PAR);
                break;
        }
    }

    private void parseDecId() throws LexicalException, SyntacticException {
        acceptIt();
        if (this.currentToken.getKind() == L_PAR) {
            acceptIt();
            parseDecArgs();
            accept(R_PAR);
        }
    }

    private List<DA> parseDecArgs() throws LexicalException, SyntacticException {
        // dec_args ::=  dec_expr  (,dec_expr)*

        List<DA> das = new ArrayList<>();

        DEXPR dexpr = parseDecExpr();
        das.add(new DA(dexpr));

        while (this.currentToken.getKind() == COMMA) {
            acceptIt();
            DEXPR dexpr1 = parseDecExpr();
            das.add(new DA(dexpr1));
        }

        return das;
    }
    // endregion

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
