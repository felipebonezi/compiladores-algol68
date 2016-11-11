package br.upe.poli.compiladores.algol68.core.checker;

import br.upe.poli.compiladores.algol68.core.util.AST.*;

import java.util.ArrayList;

public interface Visitor {

    Object visitP(P p, ArrayList<AST> list) throws SemanticException;
    Object visitCMD(CMD cmd, ArrayList<AST> list) throws SemanticException;

    Object visitDVF(DVF dvf, ArrayList<AST> list) throws SemanticException;
    Object visitDF(DF df, ArrayList<AST> list) throws SemanticException;
    Object visitDB(DB db, ArrayList<AST> list) throws SemanticException;
    Object visitDP(DP dp, ArrayList<AST> list) throws SemanticException;
    Object visitDV(DV dv, ArrayList<AST> list) throws SemanticException;
    Object visitDC(DC dc, ArrayList<AST> list) throws SemanticException;
    Object visitDW(DW dw, ArrayList<AST> list) throws SemanticException;
    Object visitDEXPR(DEXPR dexpr, ArrayList<AST> list) throws SemanticException;
    Object visitDIdAtri(DIdAtri dIdAtri, ArrayList<AST> list) throws SemanticException;
    Object visitDR(DR dr, ArrayList<AST> list) throws SemanticException;
    Object visitDA(DA da, ArrayList<AST> list) throws SemanticException;
    Object visitDidAtriExpr(DidAtriExpr didAtriExpr, ArrayList<AST> list) throws SemanticException;
    Object visitDIdAtriDA(DIdAtriDA dIdAtriDA, ArrayList<AST> list) throws SemanticException;

    Object visitDBCmd(DBCmd dbCmd, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdBreak(DBCmdBreak dbCmdBreak, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdContinue(DBCmdContinue dbCmdContinue, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdDC(DBCmdDC dbCmdDC, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdPrint(DBCmdPrint dbCmdPrint, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdDW(DBCmdDW dbCmdDW, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdIdAtri(DBCmdDIdAtri dbCmdDIdAtri, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdDR(DBCmdDR dbCmdDR, ArrayList<AST> list) throws SemanticException;
    Object visitDBCmdDV(DBCmdDV dbCmdDV, ArrayList<AST> list) throws SemanticException;

    Object visitTVT(TVT tvt, ArrayList<AST> list) throws SemanticException;
    Object visitTID(TID tid, ArrayList<AST> list) throws SemanticException;
    Object visitTVTVoid(TVTVoid tvtVoid, ArrayList<AST> list) throws SemanticException;
    Object visitTBreak(TBreak tBreak, ArrayList<AST> list);
    Object visitTContinue(TContinue tContinue, ArrayList<AST> list);
    Object visitTPrint(TPrint tPrint, ArrayList<AST> list);
    Object visitTBool(TBool tBool, ArrayList<AST> list);
    Object visitTNumber(TNumber tNumber, ArrayList<AST> list);
    Object visitT(T t, ArrayList<AST> list);

    Object visitDArith(DArith dArith, ArrayList<AST> list) throws SemanticException;
    Object visitDTerm(DTerm dTerm, ArrayList<AST> list) throws SemanticException;
    Object visitDTermArith(DTermArith dTermArith, ArrayList<AST> list) throws SemanticException;
    Object visitDTermArithDExpr(DTermArithDExpr dTermArithDExpr, ArrayList<AST> list) throws SemanticException;
    Object visitDTermArithFunc(DTermArithFunc dTermArithFunc, ArrayList<AST> list) throws SemanticException;
    Object visitDTermArithTerminal(DTermArithTerminal dTermArithTerminal, ArrayList<AST> list) throws SemanticException;

    Object visitTOPRel(TOPRel topRel, ArrayList<AST> list);
    Object visitTOPBasic(TOPBasic topBasic, ArrayList<AST> list);
    Object visitTOPFactor(TOPFactor topFactor, ArrayList<AST> list);

}
