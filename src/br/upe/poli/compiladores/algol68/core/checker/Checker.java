package br.upe.poli.compiladores.algol68.core.checker;

import br.upe.poli.compiladores.algol68.core.parser.Parser;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.*;
import br.upe.poli.compiladores.algol68.core.util.symbolsTable.IdentificationTable;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;

import java.util.ArrayList;
import java.util.List;

public class Checker implements Visitor {

    Parser parser = null;
    IdentificationTable idTable = null;


    public Checker() {
        this.parser = new Parser();
        this.idTable = new IdentificationTable();
    }

    public AST check(P p) throws SemanticException {
        return (AST) p.visit(this, null);
    }

    @Override
    public Object visitP(P p, ArrayList<AST> list) throws SemanticException {
        if (list == null)
            list = new ArrayList<>();

        CMD cmd = p.getCmd();
        cmd.visit(this, list);

        AST begin = idTable.retrieve("begin");
        AST end = idTable.retrieve("end");
        if (begin == null || !(begin instanceof CMD)
                || end == null || !(end instanceof CMD)
                || begin != end) {
            throw new SemanticException("O programa deve iniciar com 'BEGIN' e finaliza com 'END'.");
        }

        return p;
    }

    @Override
    public Object visitCMD(CMD cmd, ArrayList<AST> list) throws SemanticException {
        List<? extends DVF> dvfs = cmd.getDvfs();

        idTable.enter("begin", cmd);
        if (dvfs != null && !dvfs.isEmpty()) {
            for (DVF dvf : dvfs) {
                dvf.visit(this, list);
            }
        }
        idTable.enter("end", cmd);

        return cmd;
    }

    @Override
    public Object visitDVF(DVF dvf, ArrayList<AST> list) throws SemanticException {
        if (dvf instanceof DF) {
            DF df = (DF) dvf;
            df.visit(this, list);
        } else {
            DV dv = (DV) dvf;
            dv.visit(this, list);
        }
        return dvf;
    }

    @Override
    public Object visitDF(DF df, ArrayList<AST> list) throws SemanticException {
        TID tid = df.getTid();
        tid.visit(this, list);

        Token token = tid.getId();
        String spelling = token.getSpelling();
        AST retrieve = idTable.retrieve(spelling);
        if (retrieve != null && retrieve instanceof DF) {
            DF dfRetrived = (DF) retrieve;

            if (dfRetrived.equals(df)) {
                throw new SemanticException(String.format("A função '%s' já está declarada no programa com a mesma assinatura.", spelling));
            }
        } else {
            idTable.enter(spelling, df);
        }

        if (tid.getAssignedExpr() != null) {
            throw new SemanticException("Você não pode declarar uma função como variável.");
        }

        List<DP> params = df.getParams();
        if (params != null && !params.isEmpty()) {
            for (DP dp : params) {
                dp.visit(this, list);
            }
        }

        TVT returnType = df.getReturnType();
        returnType.visit(this, list);

        list.add(returnType);

        // region Scope
        idTable.openScope();

        boolean hasReturn = false;
        List<DB> bodies = df.getBodies();
        for (DB db : bodies) {
            db.visit(this, list);

            if (db instanceof DBCmdDR) {
                hasReturn = true;
            }
        }
        idTable.closeScope();
        // endregion

        if (hasReturn && returnType instanceof TVTVoid) {
            throw new SemanticException("Você não pode retornar valores em funções com retorno do tipo VOID.");
        }

        return df;
    }

    @Override
    public Object visitDB(DB db, ArrayList<AST> list) throws SemanticException {
        DBCmd dbCmd = (DBCmd) db;
        dbCmd.visit(this, list);

        return db;
    }

    @Override
    public Object visitDBCmd(DBCmd dbCmd, ArrayList<AST> list) throws SemanticException {
        if (dbCmd instanceof DBCmdBreak) {
            DBCmdBreak dbCmdBreak = (DBCmdBreak) dbCmd;
            dbCmdBreak.visit(this, list);
        } else if (dbCmd instanceof DBCmdContinue) {
            DBCmdContinue dbCmdContinue = (DBCmdContinue) dbCmd;
            dbCmdContinue.visit(this, list);
        } else if (dbCmd instanceof DBCmdDC) {
            DBCmdDC dbCmdDC = (DBCmdDC) dbCmd;
            dbCmdDC.visit(this, list);
        } else if (dbCmd instanceof DBCmdPrint) {
            DBCmdPrint dbCmdPrint = (DBCmdPrint) dbCmd;
            dbCmdPrint.visit(this, list);
        } else if (dbCmd instanceof DBCmdDW) {
            DBCmdDW dbCmdDW = (DBCmdDW) dbCmd;
            dbCmdDW.visit(this, list);
        } else if (dbCmd instanceof DBCmdDIdAtri) {
            DBCmdDIdAtri dbCmdDIdAtri = (DBCmdDIdAtri) dbCmd;
            dbCmdDIdAtri.visit(this, list);
        } else if (dbCmd instanceof DBCmdDR) {
            DBCmdDR dbCmdDR = (DBCmdDR) dbCmd;
            dbCmdDR.visit(this, list);
        } else if (dbCmd instanceof DBCmdDV) {
            DBCmdDV dbCmdDV = (DBCmdDV) dbCmd;
            dbCmdDV.visit(this, list);
        }

        return dbCmd;
    }

    @Override
    public Object visitDBCmdBreak(DBCmdBreak dbCmdBreak, ArrayList<AST> list) throws SemanticException {
        TBreak tBreak = dbCmdBreak.getTBreak();
        tBreak.visit(this, list);

        return dbCmdBreak;
    }

    @Override
    public Object visitDBCmdContinue(DBCmdContinue dbCmdContinue, ArrayList<AST> list) throws SemanticException {
        TContinue tContinue = dbCmdContinue.getTContinue();
        tContinue.visit(this, list);

        return dbCmdContinue;
    }

    @Override
    public Object visitDBCmdDC(DBCmdDC dbCmdDC, ArrayList<AST> list) throws SemanticException {
        DC dc = dbCmdDC.getDc();
        dc.visit(this, list);

        return dbCmdDC;
    }

    @Override
    public Object visitDBCmdPrint(DBCmdPrint dbCmdPrint, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = dbCmdPrint.getDexpr();
        dexpr.visit(this, list);

        TPrint tPrint = dbCmdPrint.gettPrint();
        tPrint.visit(this, list);

        return dbCmdPrint;
    }

    @Override
    public Object visitDBCmdDW(DBCmdDW dbCmdDW, ArrayList<AST> list) throws SemanticException {
        DW dw = dbCmdDW.getDw();
        dw.visit(this, list);

        return dbCmdDW;
    }

    @Override
    public Object visitDBCmdIdAtri(DBCmdDIdAtri dbCmdDIdAtri, ArrayList<AST> list) throws SemanticException {
        DIdAtri dIdAtri = dbCmdDIdAtri.getdIdAtri();
        dIdAtri.visit(this, list);

        return dbCmdDIdAtri;
    }

    @Override
    public Object visitDBCmdDR(DBCmdDR dbCmdDR, ArrayList<AST> list) throws SemanticException {
        DR dr = dbCmdDR.getDr();
        dr.visit(this, list);

        return dbCmdDR;
    }

    @Override
    public Object visitDBCmdDV(DBCmdDV dbCmdDV, ArrayList<AST> list) throws SemanticException {
        List<DV> dvs = dbCmdDV.getDvs();
        if (dvs != null && !dvs.isEmpty()) {
            for (DV dv : dvs) {
                dv.visit(this, list);
            }
        }

        return dbCmdDV;
    }

    @Override
    public Object visitDP(DP dp, ArrayList<AST> list) throws SemanticException {
        TVT varType = dp.getVarType();
        varType.visit(this, list);

        if (varType instanceof TVTVoid) {
            throw new SemanticException("Você não pode definir um parâmetro do tipo VOID.");
        }

        TID tid = dp.getTid();
        tid.visit(this, list);

        if (tid.getAssignedExpr() != null) {
            throw new SemanticException("Você não pode inicializar um parâmetro na assinatura da função.");
        }

        return dp;
    }

    @Override
    public Object visitTVT(TVT tvt, ArrayList<AST> list) throws SemanticException {
        if (tvt instanceof TVTVoid) {
            TVTVoid tvtVoid = (TVTVoid) tvt;
            tvtVoid.visit(this, list);
        }

        return tvt;
    }

    @Override
    public Object visitTID(TID tid, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = tid.getAssignedExpr();
        if (dexpr != null) {
            dexpr.visit(this, list);
        }

        return tid;
    }

    @Override
    public Object visitTVTVoid(TVTVoid tvtVoid, ArrayList<AST> list) throws SemanticException {
        return tvtVoid;
    }

    @Override
    public Object visitTBreak(TBreak tBreak, ArrayList<AST> list) {
        return tBreak;
    }

    @Override
    public Object visitTContinue(TContinue tContinue, ArrayList<AST> list) {
        return tContinue;
    }

    @Override
    public Object visitTPrint(TPrint tPrint, ArrayList<AST> list) {
        return tPrint;
    }

    @Override
    public Object visitTBool(TBool tBool, ArrayList<AST> list) {
        return tBool;
    }

    @Override
    public Object visitTNumber(TNumber tNumber, ArrayList<AST> list) {
        return tNumber;
    }

    @Override
    public Object visitT(T t, ArrayList<AST> list) {
        return t;
    }

    @Override
    public Object visitDArith(DArith dArith, ArrayList<AST> list) throws SemanticException {
        DTerm dTerm = dArith.getT1();
        T terminal = (T) dTerm.visit(this, list);

        List<DTerm> terms = dArith.getTerms();
        List<TOPBasic> topBasics = dArith.getTops();
        if (terms != null && !terms.isEmpty() && !topBasics.isEmpty()) {
            for (DTerm term : terms) {
                T terminalTerm = (T) term.visit(this, list);

                if (terminal.getClass() != terminalTerm.getClass()) {
                    throw new SemanticException(String.format("As variáveis são de tipos diferentes ('%s' != '%s').", terminal.getId().getSpelling(), terminalTerm.getId().getSpelling()));
                }
            }

            for (TOPBasic topBasic : topBasics) {
                topBasic.visit(this, list);
            }
        }

        return terminal;
    }

    @Override
    public Object visitDTerm(DTerm dTerm, ArrayList<AST> list) throws SemanticException {
        DTermArith dTermArith = dTerm.getT1();
        T terminal = (T) dTermArith.visit(this, list);

        List<DTermArith> terms = dTerm.getTerms();
        List<TOPFactor> topFactors = dTerm.getTops();
        if (terms != null && !terms.isEmpty() && !topFactors.isEmpty()) {
            for (DTermArith term : terms) {
                T terminalTerm = (T) term.visit(this, list);

                if (terminal.getClass() != terminalTerm.getClass()) {
                    throw new SemanticException(String.format("As variáveis são de tipos diferentes ('%s' != '%s').", terminal.getId().getSpelling(), terminalTerm.getId().getSpelling()));
                }
            }

            for (TOPFactor topFactor : topFactors) {
                topFactor.visit(this, list);
            }
        }

        return terminal;
    }

    @Override
    public Object visitDTermArith(DTermArith dTermArith, ArrayList<AST> list) throws SemanticException {
        if (dTermArith instanceof DTermArithDExpr) {
            DTermArithDExpr dTermArithDExpr = (DTermArithDExpr) dTermArith;
            dTermArithDExpr.visit(this, list);
        } else if (dTermArith instanceof DTermArithFunc) {
            DTermArithFunc dTermArithFunc = (DTermArithFunc) dTermArith;
            dTermArithFunc.visit(this, list);

            AST retrieve = idTable.retrieve(dTermArithFunc.getTid().getId().getSpelling());
            if (retrieve != null && retrieve instanceof DF) {
                DF df = (DF) retrieve;
                return df.getReturnType();
            }
        } else if (dTermArith instanceof DTermArithTerminal) {
            DTermArithTerminal dTermArithTerminal = (DTermArithTerminal) dTermArith;
            dTermArithTerminal.visit(this, list);
            return dTermArithTerminal.getTerminal();
        }

        return dTermArith;
    }

    @Override
    public Object visitDTermArithDExpr(DTermArithDExpr dTermArithDExpr, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = dTermArithDExpr.getDexpr();
        dexpr.visit(this, list);

        return dTermArithDExpr;
    }

    @Override
    public Object visitDTermArithFunc(DTermArithFunc dTermArithFunc, ArrayList<AST> list) throws SemanticException {
        TID tid = dTermArithFunc.getTid();
        tid.visit(this, list);

        List<DA> args = dTermArithFunc.getArgs();
        if (args != null && !args.isEmpty()) {
            for (DA da : args) {
                da.visit(this, list);
            }
        }

        String spelling = dTermArithFunc.getTid().getId().getSpelling();
        AST retrieve = idTable.retrieve(spelling);
        if (retrieve != null && retrieve instanceof DF) {
            DF df = (DF) retrieve;
            return df.getReturnType();
        } else {
            throw new SemanticException(String.format("Você está chamando a função '%s' que não foi declarada.", spelling));
        }
    }

    @Override
    public Object visitDTermArithTerminal(DTermArithTerminal dTermArithTerminal, ArrayList<AST> list) throws SemanticException {
        T terminal = dTermArithTerminal.getTerminal();
        terminal.visit(this, list);

        String spelling = terminal.getId().getSpelling();
        if (terminal instanceof TID && idTable.retrieve(spelling) == null) {
            throw new SemanticException(String.format("Você não declarou a variável '%s' no escopo.", spelling));
        }

        return terminal;
    }

    @Override
    public Object visitTOPRel(TOPRel topRel, ArrayList<AST> list) {
        return topRel;
    }

    @Override
    public Object visitTOPBasic(TOPBasic topBasic, ArrayList<AST> list) {
        return topBasic;
    }

    @Override
    public Object visitTOPFactor(TOPFactor topFactor, ArrayList<AST> list) {
        return topFactor;
    }

    @Override
    public Object visitDV(DV dv, ArrayList<AST> list) throws SemanticException {
        TVT tvt = dv.getTvt();
        tvt.visit(this, list);

        TID tid = dv.getTid();
        tid.visit(this, list);

        String spelling = tid.getId().getSpelling();
        AST dvEarlier = idTable.retrieve(spelling);
        if (dvEarlier != null) {
            throw new SemanticException(String.format("Você já declarou a variável '%s' em outro lugar.", spelling));
        } else {
            idTable.enter(spelling, tid);
        }

        DEXPR dexpr = tid.getAssignedExpr();
        if (dexpr != null) {
            T terminal = (T) dexpr.visit(this, list);

            if ((tvt instanceof TVTBool && !(terminal instanceof TBool))
                    || (tvt instanceof TVTInt && !(terminal instanceof TNumber))) {
                throw new SemanticException(String.format("As variáveis são de tipos diferentes ('%s' != '%s').", tvt.getId().getSpelling(), terminal.getId().getSpelling()));
            }
        }

        return dv;
    }

    @Override
    public Object visitDC(DC dc, ArrayList<AST> list) throws SemanticException {
        idTable.openScope();
        DEXPR dexpr = dc.getDexpr();
        dexpr.visit(this, list);

        boolean hasReturn = false;
        List<DB> dbsIf = dc.getDbsIf();
        if (dbsIf != null && !dbsIf.isEmpty()) {
            for (DB db : dbsIf) {
                db.visit(this, list);

                if (db instanceof DBCmdDR) {
                    hasReturn = true;
                }
            }
        }

        if (!list.isEmpty()) {
            TVT returnType = (TVT) list.get(list.size() - 1);
            if (returnType != null) {
                list.remove(returnType);

                if (hasReturn && returnType instanceof TVTVoid) {
                    throw new SemanticException("Você não pode retornar valores em funções com retorno do tipo VOID.");
                }
            }
        }

        idTable.closeScope();

        List<DB> dbsElse = dc.getDbsElse();
        if (dbsElse != null && !dbsElse.isEmpty()) {
            idTable.openScope();
            for (DB db : dbsElse) {
                db.visit(this, list);
            }
            idTable.closeScope();
        }

        return dc;
    }

    @Override
    public Object visitDW(DW dw, ArrayList<AST> list) throws SemanticException {
        idTable.openScope();
        DEXPR dexpr = dw.getDexpr();
        dexpr.visit(this, list);

        boolean hasReturn = false;
        List<DB> dbs = dw.getDbs();
        if (dbs != null && !dbs.isEmpty()) {
            for (DB db : dbs) {
                db.visit(this, list);

                if (db instanceof DBCmdDR) {
                    hasReturn = true;
                }
            }
        }

        TVT returnType = (TVT) list.get(list.size() - 1);
        if (returnType != null) {
            list.remove(returnType);

            if (hasReturn && returnType instanceof TVTVoid) {
                throw new SemanticException("Você não pode retornar valores em funções com retorno do tipo VOID.");
            }
        }

        idTable.closeScope();

        return dw;
    }

    @Override
    public Object visitDEXPR(DEXPR dexpr, ArrayList<AST> list) throws SemanticException {
        DArith dArith = dexpr.getD1();
        T terminal = (T) dArith.visit(this, list);

        List<DArith> terms = dexpr.getTerms();
        List<TOPRel> tops = dexpr.getTops();
        if (terms != null && !terms.isEmpty() && !tops.isEmpty()) {
            for (DArith term : terms) {
                T terminalTerm = (T) term.visit(this, list);

                if (terminal.getClass() != terminalTerm.getClass()) {
                    throw new SemanticException(String.format("As variáveis são de tipos diferentes ('%s' != '%s').", terminal.getId().getSpelling(), terminalTerm.getId().getSpelling()));
                }
            }

            for (TOPRel topRel : tops) {
                topRel.visit(this, list);
            }
        }

        return terminal;
    }

    @Override
    public Object visitDIdAtri(DIdAtri dIdAtri, ArrayList<AST> list) throws SemanticException {
        TID id = dIdAtri.getId();
        id.visit(this, list);

        if (dIdAtri instanceof DIdAtriDA) {
            DIdAtriDA dIdAtriDA = (DIdAtriDA) dIdAtri;
            dIdAtriDA.visit(this, list);


        } else {
            DidAtriExpr didAtriExpr = (DidAtriExpr) dIdAtri;
            didAtriExpr.visit(this, list);
        }

        return dIdAtri;
    }

    @Override
    public Object visitDR(DR dr, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = dr.getDexpr();
        dexpr.visit(this, list);

        return dr;
    }

    @Override
    public Object visitDA(DA da, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = da.getDexpr();
        dexpr.visit(this, list);

        return da;
    }

    @Override
    public Object visitDidAtriExpr(DidAtriExpr didAtriExpr, ArrayList<AST> list) throws SemanticException {
        DEXPR dexpr = didAtriExpr.getDexpr();
        T terminal = (T) dexpr.visit(this, list);

        return terminal;
    }

    @Override
    public Object visitDIdAtriDA(DIdAtriDA dIdAtriDA, ArrayList<AST> list) throws SemanticException {
        List<DA> das = dIdAtriDA.getDas();
        if (das != null && !das.isEmpty()) {
            for (DA da : das) {
                da.visit(this, list);
            }
        }

        return dIdAtriDA;
    }

}
