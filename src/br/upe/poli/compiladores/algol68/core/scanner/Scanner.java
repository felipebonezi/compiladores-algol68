package br.upe.poli.compiladores.algol68.core.scanner;

import br.upe.poli.compiladores.algol68.core.compiler.Properties;
import br.upe.poli.compiladores.algol68.core.util.Arquivo;
import br.upe.poli.compiladores.algol68.helpers.GrammarSymbols;

/**
 * Scanner class
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Scanner {

	// The file object that will be used to read the source code
	private Arquivo file;
	// The last char read from the source code
	private char currentChar;
	// The kind of the current token
	private int currentKind;
	// Buffer to append characters read from file
	private StringBuffer currentSpelling;
	// Current line and column in the source file
	private int line, column;

	/**
	 * Default constructor
	 */
	public Scanner() {
		this.file = new Arquivo(Properties.sourceCodeLocation);
		this.line = 0;
		this.column = 0;
		this.currentChar = this.file.readChar();
	}

	/**
	 * Returns the next token
	 * @return
	 */ //TODO
	public Token getNextToken() throws LexicalException {

		while(this.currentChar == '#' || this.currentChar == ' ' ||
				this.currentChar == '\n' || this.currentChar == '\t' ) {
			this.scanSeparator();
		}
		this.currentSpelling = new StringBuffer("");
		this.currentKind = this.scanToken();

		return new Token(currentKind, currentSpelling.toString(), this.line, this.column);

		// Ignores separators
		// Clears the string buffer
		// Scans the next token
		// Creates and returns a token for the lexema identified
	}

	/**
	 * Returns if a character is a separator
	 * @param c
	 * @return
	 */
	private boolean isSeparator(char c) {
		if ( c == '#' || c == ' ' || c == '\n' || c == '\t' ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads (and ignores) a separator
	 * @throws LexicalException
	 */ //TODO
	private void scanSeparator() throws LexicalException {
		if(this.currentChar == '#'){
			this.getNextChar();
			while(this.isGraphic(this.currentChar)){
				this.getNextChar();
				while (currentChar != '\n') {
					getNextChar();
				}
			}
		}else{
			this.getNextChar();
		}
		// If it is a comment line
		// Gets next char
		// Reads characters while they are graphics or '\t'
		// A command line should finish with a \n
	}

	/**
	 * Gets the next char
	 */
	private void getNextChar() throws LexicalException {
		// Appends the current char to the string buffer

		if (currentSpelling != null) {
			this.currentSpelling.append(this.currentChar);
			// Reads the next one
			this.currentChar = this.file.readChar();
			// Increments the line and column
			this.incrementLineColumn();
		}
	}

	/**
	 * Increments line and column
	 */
	private void incrementLineColumn() {
		// If the char read is a '\n', increments the line variable and assigns 0 to the column
		if ( this.currentChar == '\n' ) {
			this.line++;
			this.column = 0;
			// If the char read is not a '\n' 
		} else {
			// If it is a '\t', increments the column by 4
			if ( this.currentChar == '\t' ) {
				this.column = this.column + 4;
				// If it is not a '\t', increments the column by 1
			} else {
				this.column++;
			}
		}
	}

	/**
	 * Returns if a char is a digit (between 0 and 9)
	 * @param c
	 * @return
	 */
	private boolean isDigit(char c) {
		if ( c >= '0' && c <= '9' ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns if a char is a letter (between a and z or between A and Z)
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c) {
		if ( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns if a char is a graphic (any ASCII visible character)
	 * @param c
	 * @return
	 */
	private boolean isGraphic(char c) {
		if ( c >= ' ' && c <= '~' ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Scans the next token
	 * Simulates the DFA that recognizes the language described by the lexical grammar
	 * @return
	 * @throws LexicalException
	 */ //TODO
	private int scanToken() throws LexicalException {
		int state = 0;
		while(true){
			switch(state){
			case 0:
				if(currentChar == ';'){
					state = 1;
					getNextToken();
				}else if(currentChar == '+' || currentChar == '-'){
					state = 2;
					getNextToken();
				}else if(currentChar == '*' || currentChar == '/'){
					state = 3;
					getNextToken();
				}else if(currentChar == '>' || currentChar == '<'){
					state = 4;
					getNextToken();
				}else if(currentChar == ','){
					state = 5;
					getNextToken();
				}else if(currentChar == '='){
					state = 6;
					getNextToken();
				}else if(currentChar == ':'){				
					state = 7;
					getNextToken();
				}else if(currentChar == ')'){
					state = 8;
					getNextToken();
				}else if(currentChar == '('){
					state = 9;
					getNextToken();
				}else if(currentChar == '\000'){
					state = 10;
					getNextToken();
				}else{
					if (isLetter(currentChar)) {
						state = 11;
					} else if (isDigit(currentChar)) {
						state = 12;
					} else {
						throw new LexicalException("", currentChar, line, column);
					}		
				}

			case 1:
				return GrammarSymbols.SEMICOLON;
			case 2: 
				return GrammarSymbols.OP_BASIC;
			case 3: 
				return GrammarSymbols.OP_FACTOR;
			case 4:
				return GrammarSymbols.COMMA;
			case 5:
				switch (currentChar) {
				case '!':
				case '>':
				case '<':
					state = 9;
					break;
				default:
					return GrammarSymbols.EQUALS;
				}
				break;
			case 6:
				return GrammarSymbols.TWO_DOTS;
			case 8:
				return GrammarSymbols.R_PAR;
			case 9:
				return GrammarSymbols.L_PAR;
			case 10:
				return GrammarSymbols.EOT;
			case 11:
				while(isLetter(currentChar) || isDigit(currentChar)){
					getNextChar();
				}

				String name = currentSpelling.toString();
				name = name.toLowerCase();
				return GrammarSymbols.ID;
			}
		}
	}
}
	
	
