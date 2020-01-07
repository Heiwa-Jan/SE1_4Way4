package src.model;

import java.util.Scanner;

import src.view.PrintCanvas;

public class Move {
	//Default-Werte dienen der Erkennung eines Eingabefehlers
	private int line = -1;
	private int column = -1;
	private int direction = -1;
	private GameBoard board;
	private String moveString = null;

	/**
	 * constructor for TESTING and DEBUGGING only (without input String)
	 * @param line
	 * @param column
	 * @param direction
	 */
	public Move(int line, int column, int direction, GameBoard board) {
		this.setColumn(column);
		this.setLine(line);
		this.setDirection(direction);
		this.setMoveString(this.toString());

		this.board = board;
	}


	public Move(GameBoard board, String move) {
		this.board = board;
		this.moveString = move;

		//direkt getValidMove() im Konstruktor aufrufen?
		//yes
	}

	public Move(GameBoard board) {
		this.board = board;

		//direkt getValidMove() im Konstruktor aufrufen?
		//yes
	}

	/**
	 * the current move of the player will get translated from String to Move-object
	 * @param in
	 * @return move
	 */
	public Move getValidMove() {
		Scanner input = new Scanner(System.in);
		moveString = input.next();
		while(!(this.isValidMove())) {	//wenn der übergebene String kein valider Zug ist, wird ein neuer angefordert

			// wieder auf default-Werte zurücksetzen, zur Vermeidung zufällig entstehender Züge
			this.setColumn(-1);
			this.setDirection(-1);
			this.setLine(-1);

			PrintCanvas.print("Ungültiger Zug");
			moveString = input.next();
		}
		return this;

		//Eingabe bestenfalls komplett in eigene Klasse in View auslagern!
		//-> Fehlerabfrage + einlesen des neuen Inputs auslagern in Menü?
	}
	/**
	 * a new String [input] gets converted to Move object
	 * @param input
	 * @return
	 */
	public boolean isValidString(String input) {
		if(input.length() > 2 && input.length() <= 4) {
			// der Zug findet in einer Ecke des Spielfelds statt (am Ende des Zuges wird eine zusätzliche Richtungsangabe gemacht)
			if(input.length() > 2 && Constants.directions.contains(input.substring(input.length()-1))) {	//die angegebene Richtung einlesen
				this.setDirection(Constants.directions.indexOf(input.charAt(input.length()-1)));		//die Richtung in int umwandeln und speichern

				if(this.getDirection() == 0) {	// kann 10 sein	// == left
					if(!(input.contains("a"))) {	
						return false;	//wenn der Zug nicht ganz links stattfindet: FEHLER
					}

					this.setColumn(0);	//der Zug muss zwingend in der Spalte ganz links stattfinden
					if(input.contains("10")) {	//ist eine 10 enthalten, ist es die obere Ecke (10al)
						this.setLine(9);
					}
					else if(input.contains("1")) {	//ist nur eine 1 statt einer 10 enthalten, ist es die untere Ecke (1al)
						this.setLine(0);
					}
					//wenn die höchste Koordinate des Feldes enthalten ist, ist es die obere Ecke (9al)
					else if(input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) {  
						this.setLine(board.getRows()-1);
					}	
					else {	//ist die Eingabe keine Ecke: FEHLER
						return false;
					}
				}
				else if(this.getDirection() == 1) {	// == rechts
					if(!(input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns())))) { 
						return false;	//wenn der Zug nicht ganz rechts stattfindet: FEHLER
					}

					this.setColumn(board.getColumns()-1);	//der Zug muss zwingend in der Spalte ganz rechts stattfinden
					if(input.contains("10")) {	//ist eine 10 enthalten, ist es die obere Ecke (10jr)
						this.setLine(9);
					}
					else if(input.contains("1")) {	//ist eine 1 statt einer 10 enthalten, ist es die untere Ecke (1jr)
						this.setLine(0);
					}
					//wenn die höchste Koordinate des Feldes enthalten ist, ist es die obere Ecke (9jr)
					else if(input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) { 
						this.setLine(board.getRows()-1);
					}
					else {	//ist die Eingabe keine Ecke: FEHLER
						return false;
					}
				}
				else if(this.getDirection() == 2) {	// == oben
					if(!(input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) ) { 
						return false;	//wenn der Zug nicht ganz oben stattfindet: FEHLER
					}

					this.setLine(0);	//der Zug muss zwingend in der obersten Reihe stattfinden
					if(input.contains("a")) {	//ist ein a enthalten, ist es die linke Ecke (10au)
						this.setColumn(0);
					}
					//wenn die höchste Koordinate des Feldes enthalten ist, ist es die rechte Ecke (10ju)
					else if(input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) { 
						this.setColumn(board.getColumns()-1);
					}
					else {	//ist die Eingabe keine Ecke: FEHLER
						return false;
					}
				}
				else if(this.getDirection() == 3) {	// == unten
					if(!(input.contains("1"))) {	//wenn der Zug nicht ganz unten stattfindet: FEHLER
						return false;
					}

					this.setLine(board.getRows()-1);	//derZug muss zwingend in der untersten Reihe stattfinden
					if(input.contains("a")) {	//ist ein a enthalten, ist es die linke Ecke (1ad)
						this.setColumn(0);
					}
					//wenn die höchste Koordinate des Feldes enthalten ist, ist es die rechte Ecke (1jd)
					else if(input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) { 
						this.setColumn(board.getColumns()-1);
					}
					else {	//ist die Eingabe keine Ecke: FEHLER
						return false;
					}
				}
			}
			else if(input.length() == 3) {
				if(input.contains("10")) {
					//wenn die Eingabe eine Ecke OHNE Richtungsangabe ist: FEHLER
					if(input.contains("a") || input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) {  
						return false;
					}

					this.setDirection(2);	// Richtung des Einwurfs == unten
					this.setLine(0);	//der Einwurf erfolgt ganz unten
					for(int i = 0; i < input.length(); i++) {	//jede Position der Eingabe auf die Spaltenangabe prüfen
						if(Constants.coordinatesColumns.contains(input.substring(i, i+1))) {  
							this.setColumn(Constants.coordinatesColumns.indexOf(input.charAt(i))); //bei gefundener Spaltenangabe: diese zuweisen
						}
					}

					return true;
				}
				else {
					return false;
				}
			}
		}
		// der Zug findet NICHT in einer Ecke des Spielfelds statt (es wird keine zusätzliche Richtungsangabe gemacht)
		else if(input.length() == 2){
			if(input.length() >= 2 && input.length() <= 3) {	//ohne Richtungsangabe darf die Eingabe nicht 4 Zeichen lang sein
				//wenn ein a enthalten ist, erfolgt der Einwurf von links
				if(input.length() == 2 && input.contains("a")) {	//bei Angabe der Spalte (ohne Richtungsangabe) ist keine 10 möglich

					// wenn die Eingabe eine Ecke OHNE Richtungsangabe ist: FEHLER
					if(input.contains("1") || input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) {  
						return false;
					}
					this.setDirection(0);	// Richtung des Einwurfs == links
					this.setColumn(0);	//der Einwurf erfolgt ganz links
					for(int i = 0; i < input.length(); i++) {	//jede Position der Eingabe auf die Zeilenangabe prüfen
						if(Constants.coordinatesRows.contains(input.substring(i, i+1))) {
							this.setLine(Constants.coordinatesRows.indexOf(input.charAt(i)));  //bei gefundener Zeilenangabe: diese zuweisen
						}
					}
				}
				// wenn die höchste Spaltenzahl enthalten ist, erfolgt der Einwurf von rechts
				else if(input.length() == 2 && input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) {	//wenn die Spalte und keine Richtung angegeben ist, keine 10 möglich

					//wenn die Eingabe eine Ecke OHNE Richtungsangabe ist: FEHLER
					if(input.contains("1") || input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) {
						return false;
					}
					this.setDirection(1);	// Richtung des Einwurfs == rechts
					this.setColumn(board.getColumns()-1);	//der Einwurf erfolgt ganz rechts
					for(int i = 0; i < input.length(); i++) {	//jede Position der Eingabe auf die Zeilenangabe prüfen
						if(Constants.coordinatesRows.contains(input.substring(i, i+1))) {  
							if(Constants.coordinatesRows.indexOf(input.substring(i, i+1)) < board.getRows()){
								this.setLine(Constants.coordinatesRows.indexOf(input.charAt(i)));  //bei gefundener Zeilenangabe: diese zuweisen
							}
						}
					}
				}

				//wenn die höchste Zeilenzahl enthalten ist, erfolgt der Einwurf von unten
				else if(input.contains(Constants.coordinatesRows.substring(board.getRows()-1, board.getRows()))) {	

					//wenn die Eingabe eine Ecke OHNE Richtungsangabe ist: FEHLER
					if(input.contains("a") || input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) {  
						return false;
					}

					this.setDirection(2);	// Richtung des Einwurfs == unten
					this.setLine(0);	//der Einwurf erfolgt ganz unten
					for(int i = 0; i < input.length(); i++) {	//jede Position der Eingabe auf die Spaltenangabe prüfen
						if(Constants.coordinatesColumns.contains(input.substring(i, i+1))) {  
							this.setColumn(Constants.coordinatesColumns.indexOf(input.charAt(i))); //bei gefundener Spaltenangabe: diese zuweisen
						}
					}
				}
				//wenn eine 1 enthalten ist, erfolgt der Einwurf von oben
				else if(input.length() == 2 && input.contains("1")) {	// keine 10 möglich

					//wenn die Eingabe eine Ecke OHNE Richtungsangabe ist: FEHLER
					if(input.contains("a") || input.contains(Constants.coordinatesColumns.substring(board.getColumns()-1, board.getColumns()))) {  
						return false;
					}

					this.setDirection(3);	// Richtung des Einwurfs == oben
					this.setLine(board.getRows()-1);	//der Einwurf erfolgt ganz oben
					for(int i = 0; i < input.length(); i++) {
						if(Constants.coordinatesColumns.contains(input.substring(i, i+1))) {  //jede Position der Eingabe auf die Spaltenangabe prüfen
							this.setColumn(Constants.coordinatesColumns.indexOf(input.charAt(i)));  //bei gefundener Spaltenangabe: diese zuweisen
						}
					}
				}
			}
		}

		if(this.getDirection() == -1 || this.getColumn() == -1 || this.getLine() == -1){	//wenn es sich nicht um eine gültige Eingabe handelt: FEHLER
			return false;
		}
		return true;	//wenn Eingabe erfolgreich: ERFOLG
	}
	/**
	 * tests if there's space in the chosen row/column for placing a token
	 * @return boolean true if token can be placed
	 */
	public boolean isValidMove() {
		if(!isValidString(this.moveString)) {
			return false;
		} else if(direction == 0) { // == left

			for(int i = 0; i < board.getColumns(); i++) {	//wenn in der betreffenden Reihe noch min. ein Platz frei ist: TRUE
				try {
					if(board.getField(this.getLine(), i) == ' ') {
						return true;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		else if(direction == 1) { // == right
			for(int i = 0; i < board.getColumns(); i++) {	//wenn in der betreffenden Reihe noch min. ein Platz frei ist: TRUE  
				try {
					if(board.getField(line, board.getColumns()-i-1) == ' ') {
						return true;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		else if(direction == 2) { // == up
			for(int i = 0; i< board.getRows(); i++) {	//wenn in der betreffenden Spalte noch min. ein Platz frei ist: TRUE
				try {
					if(board.getField(i, column) == ' ') {
						return true;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		else { // == down
			for(int i = 0; i < board.getRows(); i++) {	//wenn in der betreffenden Spalte noch ein Platz frei ist: TRUE
				try {
					if(board.getField(board.getRows()-i-1, column)  == ' ') {
						return true;
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;	//wenn in der Zeile/Spalte kein Platz mehr ist: FALSE
	}
	// getters / setters:

	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getDirection() {
		return direction;
	}
	private void setDirection(int direction) {
		this.direction = direction;
	}
	
	public String getMoveString() {
		return moveString;
	}

	public void setMoveString(String moveString) {
		this.moveString = moveString;
	}

}

//Aenderung 29.12. Lejana:
//isValisString wird jetzt von vorgegebener Methode isValidMove() mit aufgerufen
//Konstruktor mit String, den GameMain fuer die KI nutzt

//TODO
//input ueber controller, getValidMove() anpassen
//getValidMove() am besten gleich im Konstruktor rufen