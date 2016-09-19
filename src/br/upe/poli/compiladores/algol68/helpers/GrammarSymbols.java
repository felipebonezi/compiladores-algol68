package br.upe.poli.compiladores.algol68.helpers;

import java.util.HashSet;
import java.util.Hashtable;

public class GrammarSymbols {

    // Language terminals (starts from 0)
    public static final int ID = 0;
    public static final int EOT = 1000;

    public static final int SEMICOLON = 1;
    public static final int OP_BASIC = 2;
    public static final int OP_FACTOR = 3;
    public static final int COMMA = 4;
    public static final int EQUALS = 5;
    public static final int TWO_DOTS = 6;
    public static final int R_PAR = 7;
    public static final int L_PAR = 8;

    public static final int BEGIN = 9;
    public static final int END = 10;
    public static final int INT = 11;
    public static final int BOOL = 12;
    public static final int IF = 13;
    public static final int THEN = 14;
    public static final int ELSE = 15;
    public static final int FI = 16;
    public static final int WHILE = 17;
    public static final int DO = 18;
    public static final int OD = 19;
    public static final int BREAK = 20;
    public static final int CONTINUE = 21;
    public static final int PRINT = 22;
    public static final int PROC = 23;
    public static final int VOID = 24;
    public static final int RETURN = 25;

    public static final HashSet<String> PRIVATE_WORDS;
    public static final Hashtable<String, Integer> PRIVATE_WORDS_TOKENS;

    static {
        PRIVATE_WORDS = new HashSet<>();
        PRIVATE_WORDS.add("int");
        PRIVATE_WORDS.add("bool");
        PRIVATE_WORDS.add("if");
        PRIVATE_WORDS.add("then");
        PRIVATE_WORDS.add("else");
        PRIVATE_WORDS.add("fi");
        PRIVATE_WORDS.add("while");
        PRIVATE_WORDS.add("do");
        PRIVATE_WORDS.add("od");
        PRIVATE_WORDS.add("break");
        PRIVATE_WORDS.add("continue");
        PRIVATE_WORDS.add("print");
        PRIVATE_WORDS.add("begin");
        PRIVATE_WORDS.add("end");
        PRIVATE_WORDS.add("proc");
        PRIVATE_WORDS.add("void");
        PRIVATE_WORDS.add("return");

        PRIVATE_WORDS_TOKENS = new Hashtable<>();
        PRIVATE_WORDS_TOKENS.put("int", INT);
        PRIVATE_WORDS_TOKENS.put("bool", BOOL);
        PRIVATE_WORDS_TOKENS.put("if", IF);
        PRIVATE_WORDS_TOKENS.put("then", THEN);
        PRIVATE_WORDS_TOKENS.put("else", ELSE);
        PRIVATE_WORDS_TOKENS.put("fi", FI);
        PRIVATE_WORDS_TOKENS.put("while", WHILE);
        PRIVATE_WORDS_TOKENS.put("do", DO);
        PRIVATE_WORDS_TOKENS.put("od", OD);
        PRIVATE_WORDS_TOKENS.put("break", BREAK);
        PRIVATE_WORDS_TOKENS.put("continue", CONTINUE);
        PRIVATE_WORDS_TOKENS.put("print", PRINT);
        PRIVATE_WORDS_TOKENS.put("begin", BEGIN);
        PRIVATE_WORDS_TOKENS.put("end", END);
        PRIVATE_WORDS_TOKENS.put("proc", PROC);
        PRIVATE_WORDS_TOKENS.put("void", VOID);
        PRIVATE_WORDS_TOKENS.put("return", RETURN);
    }

    public static boolean isPrivateWord(StringBuffer buffer) {
        return PRIVATE_WORDS.contains(buffer.toString().toLowerCase());
    }

    public static int getTokenForPrivateWord(StringBuffer buffer) {
        return PRIVATE_WORDS_TOKENS.get(buffer.toString().toLowerCase());
    }

}
