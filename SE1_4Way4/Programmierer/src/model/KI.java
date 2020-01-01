package src.model;

//import java.lang.Math;
import java.util.Random;

import src.controller.GameMain;
import src.model.Menu;
import src.model.GameBoard;
import src.model.Constants;

public class KI {
	//noch keine saubere loesung, INFINITY eig nur bei floating-point:
	private static final int POS_INFINITY = (int) Double.POSITIVE_INFINITY;
	private static final int NEG_INFINITY = (int) Double.NEGATIVE_INFINITY;
	// Die Einträge für das Feld
	private static final int LEER = 0;
	
	//TODO: muss variabel sein:
	private final char kiToken = 'X';
	private final char opponentToken = '0';
	// Bemerkung: kiToken=true, opponentToken=false = urspruenglich int
	private static final int kiToken2FAKTOR = 1;
	private static final int opponentToken2FAKTOR = 1;
	private static final int kiToken3FAKTOR = 100;
	private static final int opponentToken3FAKTOR = 500;

	
	private GameBoard board;
	private static int difficulty = 1;

	public String kiMove() {
	if(difficulty == 1) {
		return randMove();
	} else  if (difficulty == 2) {
		return mediumMove();
	} else if (difficulty == 3) {
		return hardMove();
	} else return null;
	
	
}
//-----------------------------------------------------------------------------------------
//		int randomColumns = (int) (Math.random() * this.getBoard().getColumns());
//		int randomRows = (int) (Math.random() * this.getBoard().getRows());
//
//		String kiMove = "" + Constants.coordinatesColumns.charAt(randomColumns)
//				+ Constants.coordinatesRows.charAt(randomRows);
//
//		return kiMove;
//	}
	
	public String randMove() {
	Random rand = new Random();
		
		int randRow = 0;
		int randCol = 0;
		int randDir = rand.nextInt(4); // 0=l, 1=r, 2=u, 3=d
		
		if(randDir == 0) { // left
			randRow = rand.nextInt(this.getBoard().getRows());
			randCol = 0;
		}
		else if(randDir == 1) { // right
			randRow = rand.nextInt(this.getBoard().getRows());
			randCol = this.getBoard().getColumns()-1;
		}
		else if(randDir == 2) { // up
			randCol = rand.nextInt(this.getBoard().getColumns())+1;
			randRow = this.getBoard().getRows()-1;
		}
		else if(randDir == 3) { // down
			randCol = rand.nextInt(this.getBoard().getColumns()+1);
			randRow = 0;
		}
		
		String kiMove = "" + Constants.coordinatesColumns.charAt(randCol) + Constants.coordinatesRows.charAt(randRow);
		
		if((randRow == 0 && randCol == 0) || (randRow == 0 && randCol == this.getBoard().getColumns()-1) || (randRow == this.getBoard().getRows()-1 && randCol == 0) || randRow == this.getBoard().getRows()-1 && randCol == this.getBoard().getColumns()-1) {
			kiMove = kiMove + Constants.directions.charAt(randDir);
		}
		
		return kiMove;
		//TODO: statt random moeglicherweise KI mit sehr geringer Suchtiefe
	}
	
	public String hardMove() {
		int row = 0;
		int col = 0;
		int dir = 0; // 0=l, 1=r, 2=u, 3=d
		
		dir = 0;
		//jetzt soll die Wertung fuer ein Feld ausgewertet werden, bei dem das
		//aktuelle Feld mit der linken seit nach oben gedreht wurde
		//=>4-Gewinnt-KI kann so am besten angenaehert werden
		
	
		// wenn kein Siegeszug gefunden wurde, werden die anderen 3 Richtungen auch ueberprueft
		
		
		
		String kiMove = "" + Constants.coordinatesColumns.charAt(col) + Constants.coordinatesRows.charAt(row);
		
		if((row == 0 && col == 0) || (row == 0 && col == this.getBoard().getColumns()-1) || (row == this.getBoard().getRows()-1 && col == 0) || row == this.getBoard().getRows()-1 && col == this.getBoard().getColumns()-1) {
			kiMove = kiMove + Constants.directions.charAt(dir);
		}
		
		return kiMove;
		//TODO: moeglichst gute KI auf basis 4-gewinnt-KI, soll rate() zur Wertung berechneter Boards nutzen
		//soll im Gegensatz zu 4Gewinnt-KI erkennen, dass Reihen durch seitl. Einwerfen unterbrechbar
		
	}
	
	public String mediumMove() {
		return null;
		//TODO: hardMove mit geringerer Suchtiefe
	}
	
	/**
	 * Erkennt 2er, 3er oder 4er Reihen dx und dy geben die Richtung an, in die
	 * gesucht wird (ab Position x,y)
	 * 
	 * @param feld
	 * @param token
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @return anzahl der Steine
	 * im Original istReihe() !
	 */
	private static int row (GameBoard feld, char token , int x, int y, int dx, int dy) {
	
		
		int length = 0;
		// 4 freie oder von token belegte fielder in Richtung (dx,dy)
		if (((feld.getField(x,y) == token) || (feld.getField(x,y) == '\0'))
				&& ((feld.getField(x + 1 * dx, y + 1 * dy) == token) || (feld.getField(x + 1 * dx, y + 1 * dy) == '\0'))
				&& ((feld.getField(x + 2 * dx, y + 2 * dy) == token) || (feld.getField(x + 2 * dx, y + 2 * dy) == '\0'))
				&& ((feld.getField(x + 3 * dx, y + 3 * dy) == token) || (feld.getField(x + 3 * dx, y + 3 * dy) == '\0'))) {
			// Zaehle die Anzahl von token belegter fielder
			for (int i = 0; i < 4; i++) {
				if (feld.getField(x + i * dx, y + i * dy) == token) {
					length++;
				}
			}
		}
		return length;
		
	}
	
	/**
	 * Bewertet das Spielfeld anhand der gegeben Spielsituation
	 * 
	 * @param spiel
	 * @return bewertung
	 */
	int rate() {
		
		//erst als konstante Felder moeglich wenn KI von Anfang ein board hat:
		int WIDTH = board.getColumns();
		int HEIGHT = board.getRows();

		GameBoard feld = this.board;

		int min2er = 0;
		int max2er = 0;
		int min3er = 0;
		int max3er = 0;
		// Gehe das Spielfeld durch und bewerte,...
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				// ...ob 4 Steine nach oben möglich sind
				if (HEIGHT - y >= 4) {
					// bei 4 gleichen Steinen:
					if (row(feld, kiToken, x, y, 0, 1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(feld, opponentToken, x, y, 0, 1) == 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 0, 1) == 3)
						max3er++;
					else if (row(feld, opponentToken, x, y, 0, 1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 0, 1) == 2)
						max2er++;
					else if (row(feld, opponentToken, x, y, 0, 1) == 2)
						min2er++;
				}
				// ...ob 4 gleiche Steine nach rechts oben möglich sind
				if ((HEIGHT - y >= 4) && (WIDTH - x >= 4)) {
					// bei 4 gleichen Steinen:
					if (row(feld, kiToken, x, y, 1, 1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(feld, opponentToken, x, y, 1, 1) == 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, 1) == 3)
						max3er++;
					else if (row(feld, opponentToken, x, y, 1, 1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, 1) == 2)
						max2er++;
					else if (row(feld, opponentToken, x, y, 1, 1) == 2)
						min2er++;
				}
				// ...ob 4 gleiche Steine nach rechts moeglich sind
				if (WIDTH - x >= 4) {
					// bei 4 gleichen Steinen:
					if (row(feld, kiToken, x, y, 1, 0) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(feld, opponentToken, x, y, 1, 0) == 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, 0) == 3)
						max3er++;
					else if (row(feld, opponentToken, x, y, 1, 0) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, 0) == 2)
						max2er++;
					else if (row(feld, opponentToken, x, y, 1, 0) == 2)
						min2er++;
				}
				// ...ob 4 gleiche Steine nach rechts unten möglich sind
				if ((WIDTH - x >= 4) && (y >= 3)) {
					// bei 4 gleichen Steinen:
					if (row(feld, kiToken, x, y, 1, -1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(feld, opponentToken, x, y, 1, -1) == 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, -1) == 3)
						max3er++;
					else if (row(feld, opponentToken, x, y, 1, -1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (row(feld, kiToken, x, y, 1, -1) == 2)
						max2er++;
					else if (row(feld, opponentToken, x, y, 1, -1) == 2)
						min2er++;
				}
			}

		}
		//gewichtete Reihen-Anzahl des Spielers der dran ist - die des anderen Spielers:
		//max steht fuer den ziehenden, also die ki, min fuer den Gegner
		return kiToken2FAKTOR * max2er + kiToken3FAKTOR * max3er - opponentToken2FAKTOR * min2er - opponentToken3FAKTOR * min3er;
	}

	public KI(GameBoard board){
		this.setBoard(board);
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	private void setBoard(GameBoard board) {
		this.board = board;
	}
}
//TODO 
//mit Grundlage von 4 Gewinnt KI fertig implementieren:
//initialisierung von KI mit ihrer Schwierigkeit und einem leeren Board fuer WIDTH,HEIGTH
//difficulty vom menu abfragen und medium + hardMove() fertig implementieren
//erkennung von kleineren Reihen hier oder in SearchRow fuer Zugbewertung 
//=>erstmal hier um nur diese Klasse aendern zu muessen (DONE? siehe row())
//variabel token das die KI diese Runde verwendet bestimmen
