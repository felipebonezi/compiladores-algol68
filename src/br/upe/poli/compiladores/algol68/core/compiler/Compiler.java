package br.upe.poli.compiladores.algol68.core.compiler;

import br.upe.poli.compiladores.algol68.core.parser.Parser;
import br.upe.poli.compiladores.algol68.core.parser.SyntacticException;
import br.upe.poli.compiladores.algol68.core.scanner.LexicalException;
import br.upe.poli.compiladores.algol68.core.scanner.Scanner;
import br.upe.poli.compiladores.algol68.core.scanner.Token;
import br.upe.poli.compiladores.algol68.core.util.AST.AST;
import br.upe.poli.compiladores.algol68.core.util.symbolsTable.IdentificationTable;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;

/**
 * Compiler driver
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Compiler {
	
	// Compiler identification table
	public static IdentificationTable identificationTable = null;

	/**
	 * Compiler start point
	 * @param args - none
	 */
	public static void main(String[] args) {
//		 Initializes the identification table with the reserved words
		Compiler.initIdentificationTable();

		// Creates the parser object
		Parser p = new Parser();

		// Creates the AST object
		AST astRoot;
		try {
			// Parses the source code
			astRoot = p.parse();
			System.out.println("\n-- AST STRUCTURE --");
			if ( astRoot != null ) {
				System.out.println(astRoot.toString(0));
			}
		} catch (SyntacticException | LexicalException e) {
			// Shows the syntactic/lexical error stack trace
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the identification table with the reserved words
	 */
	private static void initIdentificationTable() {
		// Calls the initializer methods
		Compiler.identificationTable = new IdentificationTable();
	}
	
}
