package br.upe.poli.compiladores.algol68;

import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;

public class Main {

    public static void main(String[] args) {
        // Implementação do código que será executado.
        Scanner scanner = new Scanner();

        Token token;
        do {
            try {
                token = scanner.getNextToken();
                System.out.println(token.getSpelling());
            } catch (LexicalException e) {
                e.printStackTrace();
                token = null;
                System.out.println("ERROR!!!!");
            }
        } while (token != null && token.getKind() != GrammarSymbols.EOT);
    }

}
