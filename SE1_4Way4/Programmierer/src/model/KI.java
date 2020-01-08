package src.model;

//import java.lang.Math;
//import java.util.Random;

import src.controller.GameMain;
import src.model.Menu;
import src.model.GameBoard;
import src.model.Constants;

public class KI {
	// noch keine saubere loesung, INFINITY eig nur bei floating-point:
	private static final int POS_INFINITY = (int) Double.POSITIVE_INFINITY;
	private static final int NEG_INFINITY = (int) Double.NEGATIVE_INFINITY;

	// Die Einträge für das Feld
	// --> scheinbar (hier nutzloses) Überbleibsel aus 4Gewinnt
	// private static final int LEER = 0;

	// TODO: muss variabel sein:
	// --> Token des Spielers abfragen / übergeben lassen (done)
	private char kiToken;
	private char opponentToken;
	// Bemerkung: kiToken=true, opponentToken=false = urspruenglich int
	// --> ???

	// blockierte Reihen(Gegner) werden stärker gewertet, als
	// offene/geschlossene(KI)!
	private static final int kiToken2FAKTOR = 1;
	private static final int opponentToken2FAKTOR = 1;
	private static final int opponentBlocked2FAKTOR = 5;
	private static final int kiToken3FAKTOR = 100;
	private static final int opponentToken3FAKTOR = 500;
	private static final int opponentBlocked3FAKTOR = 1000;

	private GameBoard board;
	private static int difficulty = 1;

	private boolean firstMoveKI; // true == erster Zug && KI beginnt
	// false == jeder andere Zug der KI
	private boolean kiMove = false;

	// Konstruktor----------------------------------------------------------------------
	public KI(GameBoard board, char playerToken, boolean firstMoveKI, int difficulty) {
		this.setFirstMoveKI(true);
		this.setDifficulty(difficulty);
		this.opponentToken = playerToken;
		if (this.getOpponentToken() == 'X') {
			this.kiToken = 'O';
		} else {
			this.kiToken = 'X';
		}

		this.setBoard(board);

		this.setFirstMoveKI(firstMoveKI);
	}

	/**
	 * wählt einen Zug, je nach gewähltem Schwierigkeitsgrad
	 * 
	 * @return Zug der KI
	 */
	public Move kiMove() {
		if (difficulty == 1) {
			return randMove();
		} else if (difficulty == 2) {
			return mediumMove();
		} else if (difficulty == 3) {
			return hardMove();
		} else
			return null;
	}

	// TODO: statt random moeglicherweise KI mit sehr geringer Suchtiefe
	/**
	 * KI zieht eine beliebige Richtung (leichtester Schwierigkeitsgrad)
	 * 
	 * @return
	 */
	public Move randMove() {
		// Random rand = new Random();
		//
		// int randRow = 0;
		// int randCol = 0;
		// int randDir = rand.nextInt(4); // 0=l, 1=r, 2=u, 3=d
		//
		// if(randDir == 0) { // left
		// randRow = rand.nextInt(this.getBoard().getRows());
		// randCol = 0;
		// }
		// else if(randDir == 1) { // right
		// randRow = rand.nextInt(this.getBoard().getRows());
		// randCol = this.getBoard().getColumns()-1;
		// }
		// else if(randDir == 2) { // up
		// randCol = rand.nextInt(this.getBoard().getColumns())+1;
		// randRow = this.getBoard().getRows()-1;
		// }
		// else if(randDir == 3) { // down
		// randCol = rand.nextInt(this.getBoard().getColumns()+1);
		// randRow = 0;
		// }
		//
		// String kiMove = "" + Constants.coordinatesColumns.charAt(randCol) +
		// Constants.coordinatesRows.charAt(randRow);
		//
		// if((randRow == 0 && randCol == 0) || (randRow == 0 && randCol ==
		// this.getBoard().getColumns()-1) || (randRow == this.getBoard().getRows()-1 &&
		// randCol == 0) || randRow == this.getBoard().getRows()-1 && randCol ==
		// this.getBoard().getColumns()-1) {
		// kiMove = kiMove + Constants.directions.charAt(randDir);
		// }
		//
		// return kiMove;

		int depth = 0;
		Move currentMove;
		GameBoard tempBoard;
//		GameBoard boardDirection;

		int[] valuesLeft = new int[this.getBoard().getRows()];
		int[] valuesRight = new int[this.getBoard().getRows()];
		int[] valuesUp = new int[this.getBoard().getColumns()];
		int[] valuesDown = new int[this.getBoard().getColumns()];

		for (int i = 0; i < this.getBoard().getRows(); i++) {
			valuesLeft[i] = NEG_INFINITY;
			valuesRight[i] = NEG_INFINITY;
		}
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			valuesUp[i] = NEG_INFINITY;
			valuesDown[i] = NEG_INFINITY;
		}

		// jetzt soll die Wertung fuer ein Feld ausgewertet werden, bei dem das
		// aktuelle Feld mit der linken Seite nach oben gedreht wurde
		// => 4-Gewinnt-KI kann so am besten angenaehert werden

		// --> lieber Seiten einzeln prüfen?

		// Züge von LINKS
		currentMove = new Move(0, 0, 0, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesLeft[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}
		}

		// Züge von RECHTS
		currentMove = new Move(0, this.getBoard().getColumns() - 1, 1, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesRight[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von UNTEN
		currentMove = new Move(0, 0, 2, this.getBoard());
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesDown[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von OBEN
		currentMove = new Move(0, this.getBoard().getRows() - 1, 3, this.getBoard());

		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesUp[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}
		}
		// wenn kein Siegeszug gefunden wurde, werden die anderen 3 Richtungen auch
		// ueberprueft

		Move kiMove = null;
		int max = NEG_INFINITY;
		// linke Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesLeft[i] > max)) {
				kiMove = new Move(i, 0, 0, this.getBoard());
				max = valuesLeft[i];
			}
		}

		// rechte Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesRight[i] > max)) {
				kiMove = new Move(i, this.getBoard().getColumns() - 1, 1, this.getBoard());
				max = valuesRight[i];
			}
		}

		// obere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesUp[i] > max)) {
				kiMove = new Move(this.getBoard().getRows() - 1, i, 3, this.getBoard());
				max = valuesUp[i];
			}
		}

		// untere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesDown[i] > max)) {
				kiMove = new Move(0, i, 2, this.getBoard());
				max = valuesDown[i];
			}
		}

		return kiMove;
		// TODO: moeglichst gute KI auf Basis 4-gewinnt-KI, soll rate() zur Wertung
		// berechneter Boards nutzen
		// soll im Gegensatz zu 4Gewinnt-KI erkennen, dass Reihen durch seitl. Einwerfen
		// unterbrechbar
		// --> ???

	}

	public Move mediumMove() {
		// TODO: hardMove mit geringerer Suchtiefe

		int depth = 1;
		Move currentMove;
		GameBoard tempBoard;
//		GameBoard boardDirection;

		int[] valuesLeft = new int[this.getBoard().getRows()];
		int[] valuesRight = new int[this.getBoard().getRows()];
		int[] valuesUp = new int[this.getBoard().getColumns()];
		int[] valuesDown = new int[this.getBoard().getColumns()];

		for (int i = 0; i < this.getBoard().getRows(); i++) {
			valuesLeft[i] = NEG_INFINITY;
			valuesRight[i] = NEG_INFINITY;
		}
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			valuesUp[i] = NEG_INFINITY;
			valuesDown[i] = NEG_INFINITY;
		}

		// jetzt soll die Wertung fuer ein Feld ausgewertet werden, bei dem das
		// aktuelle Feld mit der linken Seite nach oben gedreht wurde
		// => 4-Gewinnt-KI kann so am besten angenaehert werden

		// --> lieber Seiten einzeln prüfen?

		// Züge von LINKS
		currentMove = new Move(0, 0, 0, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesLeft[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von RECHTS
		currentMove = new Move(0, this.getBoard().getColumns() - 1, 1, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesRight[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von UNTEN
		currentMove = new Move(0, 0, 2, this.getBoard());
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesDown[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von OBEN
		currentMove = new Move(0, this.getBoard().getRows() - 1, 3, this.getBoard());

		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesUp[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}
		}
		// wenn kein Siegeszug gefunden wurde, werden die anderen 3 Richtungen auch
		// ueberprueft

		Move kiMove = null;
		int max = NEG_INFINITY;

		// linke Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesLeft[i] > max)) {
				kiMove = new Move(i, 0, 0, this.getBoard());
				max = valuesLeft[i];
			}
		}

		// rechte Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesRight[i] > max)) {
				kiMove = new Move(i, this.getBoard().getColumns() - 1, 1, this.getBoard());
				max = valuesRight[i];
			}
		}

		// obere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesUp[i] > max)) {
				kiMove = new Move(this.getBoard().getRows() - 1, i, 3, this.getBoard());
				max = valuesUp[i];
			}
		}

		// untere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesDown[i] > max)) {
				kiMove = new Move(0, i, 2, this.getBoard());
				max = valuesDown[i];
			}
		}

		return kiMove;

		// TODO: moeglichst gute KI auf Basis 4-gewinnt-KI, soll rate() zur Wertung
		// berechneter Boards nutzen
		// soll im Gegensatz zu 4Gewinnt-KI erkennen, dass Reihen durch seitl. Einwerfen
		// unterbrechbar
		// --> ???

	}

	public Move hardMove() {

		int depth = 3;
		Move currentMove;
		GameBoard tempBoard;
//		GameBoard boardDirection;

		int[] valuesLeft = new int[this.getBoard().getRows()];
		int[] valuesRight = new int[this.getBoard().getRows()];
		int[] valuesUp = new int[this.getBoard().getColumns()];
		int[] valuesDown = new int[this.getBoard().getColumns()];

		for (int i = 0; i < this.getBoard().getRows(); i++) {
			valuesLeft[i] = NEG_INFINITY;
			valuesRight[i] = NEG_INFINITY;
		}
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			valuesUp[i] = NEG_INFINITY;
			valuesDown[i] = NEG_INFINITY;
		}

		// jetzt soll die Wertung fuer ein Feld ausgewertet werden, bei dem das
		// aktuelle Feld mit der linken Seite nach oben gedreht wurde
		// => 4-Gewinnt-KI kann so am besten angenaehert werden

		// --> lieber Seiten einzeln prüfen?

		// Züge von LINKS
		currentMove = new Move(0, 0, 0, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesLeft[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von RECHTS
		currentMove = new Move(0, this.getBoard().getColumns() - 1, 1, this.getBoard());
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			currentMove.setLine(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesRight[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von UNTEN
		currentMove = new Move(0, 0, 2, this.getBoard());
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesDown[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}

		}

		// Züge von OBEN
		currentMove = new Move(0, this.getBoard().getRows() - 1, 3, this.getBoard());

		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			currentMove.setColumn(i);
			if (currentMove.isValidMove()) {
				tempBoard = this.getBoard().copy();
				tempBoard = MoveAllTokens.move(tempBoard, currentMove, kiToken);
				valuesUp[i] = minMax(tempBoard, currentMove, depth, NEG_INFINITY, POS_INFINITY);
				this.setFirstMoveKI(true);
			}
		}
		// wenn kein Siegeszug gefunden wurde, werden die anderen 3 Richtungen auch
		// ueberprueft

		Move kiMove = null;
		int max = NEG_INFINITY;
		// linke Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesLeft[i] > max)) {
				kiMove = new Move(i, 0, 0, this.getBoard());
				max = valuesLeft[i];
			}
		}

		// rechte Seite
		for (int i = 0; i < this.getBoard().getRows(); i++) {
			if ((valuesRight[i] > max)) {
				kiMove = new Move(i, this.getBoard().getColumns() - 1, 1, this.getBoard());
				max = valuesRight[i];
			}
		}

		// obere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesUp[i] > max)) {
				kiMove = new Move(this.getBoard().getRows() - 1, i, 3, this.getBoard());
				max = valuesUp[i];
			}
		}

		// untere Seite
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			if ((valuesDown[i] > max)) {
				kiMove = new Move(0, i, 2, this.getBoard());
				max = valuesDown[i];
			}
		}

		return kiMove;
		// TODO: moeglichst gute KI auf Basis 4-gewinnt-KI, soll rate() zur Wertung
		// berechneter Boards nutzen
		// soll im Gegensatz zu 4Gewinnt-KI erkennen, dass Reihen durch seitl. Einwerfen
		// unterbrechbar
		// --> ???

	}

	private int minMax(GameBoard tempBoard, Move currentMove, int depth, int alpha, int beta) {
		GameBoard temp = tempBoard.copy();
		int minMaxTemp;
		int minMaxLocal;
		boolean kiMove;

		if (depth % 2 == 0) {
			kiMove = true;
		} else {
			kiMove = false;
		}

		if (!this.isFirstMoveKI()) {
			minMaxLocal = alpha;
		} else {
			minMaxLocal = beta;
		}

		if (depth == 0) {
			return rate(temp);
		} else {
			if (currentMove.getDirection() == 0) { // links
				currentMove.setColumn(0);
				for (int i = 0; i < this.getBoard().getRows(); i++) {
					currentMove.setLine(i);

					if (kiMove) {
						temp = MoveAllTokens.move(tempBoard, currentMove, kiToken);
					} else {
						temp = MoveAllTokens.move(tempBoard, currentMove, opponentToken);
					}

					if (currentMove.isValidMove()) {
						minMaxTemp = minMax(temp, currentMove, depth - 1, alpha, beta);

						if (!this.isFirstMoveKI()) {
							minMaxLocal = Math.max(minMaxTemp, minMaxLocal);
							alpha = minMaxLocal;
							if (alpha >= beta) {
								return beta;
							}
						} else {
							minMaxLocal = Math.min(minMaxTemp, minMaxLocal);
							beta = minMaxLocal;
							if (beta <= alpha) {
								return alpha;
							}
						}
						this.setFirstMoveKI(false);
					}
				}
			} else if (currentMove.getDirection() == 1) { // rechts

				currentMove.setColumn(tempBoard.getColumns() - 1);
				for (int i = 0; i < this.getBoard().getRows(); i++) {
					currentMove.setLine(i);

					if (kiMove) {
						temp = MoveAllTokens.move(tempBoard, currentMove, kiToken);
					} else {
						temp = MoveAllTokens.move(tempBoard, currentMove, opponentToken);
					}

					if (currentMove.isValidMove()) {
						minMaxTemp = minMax(temp, currentMove, depth - 1, alpha, beta);

						if (!this.isFirstMoveKI()) {
							minMaxLocal = Math.max(minMaxTemp, minMaxLocal);
							alpha = minMaxLocal;
							if (alpha >= beta) {
								return beta;
							}
						} else {
							minMaxLocal = Math.min(minMaxTemp, minMaxLocal);
							beta = minMaxLocal;
							if (beta <= alpha) {
								return alpha;
							}
						}
						this.setFirstMoveKI(false);
					}
				}
			} else if (currentMove.getDirection() == 2) { // unten

				currentMove.setLine(0);
				for (int i = 0; i < this.getBoard().getColumns(); i++) {
					currentMove.setColumn(i);
					// temp = MoveAllTokens.moveLast(tempBoard, currentMove, kiToken);
					if (kiMove) {
						temp = MoveAllTokens.move(tempBoard, currentMove, kiToken);
					} else {
						temp = MoveAllTokens.move(tempBoard, currentMove, opponentToken);
					}
					if (currentMove.isValidMove()) {
						minMaxTemp = minMax(temp, currentMove, depth - 1, alpha, beta);

						if (!this.isFirstMoveKI()) {
							minMaxLocal = Math.max(minMaxTemp, minMaxLocal);
							alpha = minMaxLocal;
							if (alpha >= beta) {
								return beta;
							}
						} else {
							minMaxLocal = Math.min(minMaxTemp, minMaxLocal);
							beta = minMaxLocal;
							if (beta <= alpha) {
								return alpha;
							}
						}
						this.setFirstMoveKI(false);
					}
				}
			}

			else { // oben
				currentMove.setLine(tempBoard.getRows() - 1);
				for (int i = 0; i < this.getBoard().getColumns(); i++) {
					currentMove.setColumn(i);
					// temp = MoveAllTokens.moveLast(tempBoard, currentMove, kiToken);
					if (kiMove) {
						temp = MoveAllTokens.move(tempBoard, currentMove, kiToken);
					} else {
						temp = MoveAllTokens.move(tempBoard, currentMove, opponentToken);
					}
					if (currentMove.isValidMove()) {
						minMaxTemp = minMax(temp, currentMove, depth - 1, alpha, beta);

						if (!this.isFirstMoveKI()) {
							minMaxLocal = Math.max(minMaxTemp, minMaxLocal);
							alpha = minMaxLocal;
							if (alpha >= beta) {
								return beta;
							}
						} else {
							minMaxLocal = Math.min(minMaxTemp, minMaxLocal);
							beta = minMaxLocal;
							if (beta <= alpha) {
								return alpha;
							}
						}
						this.setFirstMoveKI(false);
					}
				}
			}
		}
		return minMaxLocal;
	}

	/**
	 * Erkennt 2er, 3er oder 4er Reihen dx und dy geben die Richtung an, in die
	 * gesucht wird (ab Position x,y)
	 * 
	 * @param board == aktuelle Spielsituation
	 * @param token == auf dem aktuell geprüften Feld liegendes Token
	 * @param x     == Position des aktuellen Feldes (Spalte)
	 * @param y     == Position des aktuellen Feldes (Reihe)
	 * @param dx    == Richtung, die auf Reihen untersucht wird (Spalte)
	 * @param dy    == Richtung, die auf Reihe untersucht wird (Reihe)
	 * @return Länge der untersuchten Reihe im Original istReihe() !
	 */
	private static int row(GameBoard board, char token, int x, int y, int dx, int dy) {

		// ACHTUNG: keinen Prüfung, ob Array-Grenzen überschritten werden!!!

		int length = 0; // == Länge der Reihe ab [x,y] in Richtung [dx,dy]
		// 4 freie oder von token belegte felder in Richtung (dx,dy)
		// if (((feld.getField(x,y) == token) || (feld.getField(x,y) == ' '))
		// && ((feld.getField(x + 1 * dx, y + 1 * dy) == token) || (feld.getField(x + 1
		// * dx, y + 1 * dy) == ' '))
		// && ((feld.getField(x + 2 * dx, y + 2 * dy) == token) || (feld.getField(x + 2
		// * dx, y + 2 * dy) == ' '))
		// && ((feld.getField(x + 3 * dx, y + 3 * dy) == token) || (feld.getField(x + 3
		// * dx, y + 3 * dy) == ' '))) {
		// Zaehle die Anzahl von token belegter Felder

		for (int i = 0; i < 4; i++) {
			try {
				if (board.getField(x + i * dx, y + i * dy) == token) {
					length++;
				} else {
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}

		}

		// }
		return length;

	}

	/**
	 * Bewertet das Spielfeld anhand der gegeben Spielsituation
	 * 
	 * @param spiel
	 * @return bewertung
	 */
	int rate(GameBoard tempBoard) {

		// Prüfung ob Array-Grenzen überschritten werden hier!!!

		// erst als konstante Felder moeglich wenn KI von Anfang ein board hat:
		int WIDTH = tempBoard.getColumns();
		int HEIGHT = tempBoard.getRows();

		GameBoard board = tempBoard.copy();

		int min2erOpen = 0; // Anzahl gegnerischer 2er-Reihen
		int min2erBlocked = 0;
		int max2erOpen = 0; // Anzahl eigener 2er-Reihen
		int max2erBlocked = 0;
		int min3erOpen = 0; // Anzahl gegnerischer 3er-Reihen
		int min3erBlocked = 0;
		int max3erOpen = 0; // Anzahl eigener 3er-Reihen
		int max3erBlocked = 0;

		// Gehe das Spielfeld durch und bewerte,...
		for (int column = 0; column < WIDTH; column++) {
			for (int row = 0; row < HEIGHT; row++) {

				// ...ob 4 Steine nach OBEN möglich sind
				if (row < HEIGHT - 4) {
					// bei 4 gleichen Steinen:
					if (row(board, kiToken, row, column, 1, 0) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(board, opponentToken, row, column, 1, 0) == 4)
						return NEG_INFINITY; // verloren
				}
				// bei 3 gleichen Steinen:
				if (row < HEIGHT - 3) {
					if (row(board, kiToken, row, column, 1, 0) == 3) { // eigene 3er-Reihe nach oben
						if (row > 0) {
							if (board.getField(row - 1, column) == opponentToken) {
								max3erBlocked++;
							} else {
								// max3erBlocked++;
								max3erOpen++;
							}
						} else {
							// max3erBlocked++;
							max3erOpen++;
						}
						if (row < HEIGHT - 2) {
							if (board.getField(row + 3, column) == opponentToken) {
								max3erBlocked++;
								// max3erOpen++;
							} else {
								// max3erBlocked++;
								max3erOpen++;
							}
						}

						else {
							// max3erBlocked++;
							max3erOpen++;
						}
					}

					else if (row(board, opponentToken, row, column, 1, 0) == 3) { // gegnerische 3er-Reihe nach oben

						if (row > 0) {
							if (board.getField(row - 1, column) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
						if (row < HEIGHT - 2) {
							if (board.getField(row + 3, column) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
					}
				}

				// bei 2 gleichen Steinen:
				if (row < HEIGHT - 2) {
					if (row(board, kiToken, row, column, 1, 0) == 2) { // eigene 2er-Reihe nach oben

						if (row > 0) {
							if (board.getField(row - 1, column) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
						if (row < HEIGHT - 1) {
							if (board.getField(row + 2, column) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
					}

					else if (row(board, opponentToken, row, column, 1, 0) == 2) { // gegnerische 2er-Reihe nach oben

						if (row > 0) {
							if (board.getField(row - 1, column) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
						if (row < HEIGHT - 2) {
							if (board.getField(row + 2, column) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
					}
				}
				// ...ob 4 gleiche Steine nach RECHTS OBEN möglich sind
				if ((HEIGHT - column > 5) && (WIDTH - row > 5)) {
					// bei 4 gleichen Steinen:
					if (row(board, kiToken, row, column, 1, 1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(board, opponentToken, row, column, 1, 1) == 4)
						return NEG_INFINITY; // verloren
				}
				if ((HEIGHT - column > 4) && (WIDTH - row > 4)) {
					// bei 3 gleichen Steinen:
					if (row(board, kiToken, row, column, 1, 1) == 3) { // eigene 3er-Reihe nach rechts oben
						if (row > 0 && column > 0) {
							if (board.getField(row - 1, column - 1) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
						if (row < HEIGHT - 3 && column < WIDTH - 3) {
							if (board.getField(row + 3, column + 3) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
					} else if (row(board, opponentToken, row, column, 1, 1) == 3) { // gegnerische 3er-Reihe nach rechts
																					// oben

						if (row > 0 && column > 0) {
							if (board.getField(row - 1, column - 1) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
						if (row < HEIGHT - 3 && column < WIDTH - 3) {
							if (board.getField(row + 3, column + 3) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
					}
				}
				// bei 2 gleichen Steinen:
				if ((HEIGHT - column > 3) && (WIDTH - row > 3)) {
					if (row(board, kiToken, row, column, 1, 1) == 2) { // eigene 2er-Reihe nach rechts oben

						if (row > 0 && column > 0) {
							if (board.getField(row - 1, column - 1) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
						if (row < HEIGHT - 2 && column < WIDTH - 2) {
							if (board.getField(row + 2, column + 2) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
					} else if (row(board, opponentToken, row, column, 1, 1) == 2) { // gegnerische 2er-Reihe nach rechts
																					// oben

						if (row > 0 && column > 0) {
							if (board.getField(row - 1, column - 1) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
						if (row < HEIGHT - 2 && column < WIDTH - 2) {
							if (board.getField(row + 2, column + 2) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
					}
				}

				// ...ob 4 gleiche Steine nach RECHTS moeglich sind
				if (column < WIDTH - 4) {
					// bei 4 gleichen Steinen:
					if (row(board, kiToken, row, column, 0, 1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(board, opponentToken, row, column, 0, 1) == 4)
						return NEG_INFINITY; // verloren
				}
				// bei 3 gleichen Steinen:
				if (column < WIDTH - 3) {
					if (row(board, kiToken, row, column, 0, 1) == 3) { // eigene 3er-Reihe nach rechts

						if (column > 0) {
							if (board.getField(row, column - 1) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
						if (column < WIDTH - 2) {
							if (board.getField(row, column + 3) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
					} else if (row(board, opponentToken, row, column, 0, 1) == 3) {// gegnerische 3er-Reihe nach rechts

						if (column > 0) {
							if (board.getField(row, column - 1) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
						if (column < WIDTH - 2) {
							if (board.getField(row, column + 3) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
					}
				}
				// bei 2 gleichen Steinen:
				if (column < WIDTH - 2) {
					if (row(board, kiToken, row, column, 0, 1) == 2) { // eigene 2er-Reihe nach rechts

						if (column > 0) {
							if (board.getField(row, column - 1) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
						if (column < WIDTH - 1) {
							if (board.getField(row, column + 2) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
					} else if (row(board, opponentToken, row, column, 0, 1) == 2) { // gegnerische 2er-Reihe nach rechts

						if (column > 0) {
							if (board.getField(row, column - 1) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
						if (column < WIDTH - 1) {
							if (board.getField(row, column + 2) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
					}
				}
				// ...ob 4 gleiche Steine nach RECHTS UNTEN möglich sind
				if ((row > 4) && (column < WIDTH - 3)) {
					// bei 4 gleichen Steinen:
					if (row(board, kiToken, row, column, -1, 1) == 4)
						return POS_INFINITY; // gewonnen
					else if (row(board, opponentToken, row, column, -1, 1) == 4)
						return NEG_INFINITY; // verloren
				}
				if ((row > 3) && (column < WIDTH - 2)) {
					// bei 3 gleichen Steinen:
					if (row(board, kiToken, row, column, -1, 1) == 3) { // eigene 3er-Reihe nach rechts unten

						if (row < HEIGHT - 1 && column > 0) {
							if (board.getField(row + 1, column - 1) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
						if (row > 2 && column < WIDTH - 3) {
							if (board.getField(row - 3, column + 3) == opponentToken) {
								max3erBlocked++;
							} else {
								max3erOpen++;
							}
						} else {
							max3erOpen++;
						}
					} else if (row(board, opponentToken, row, column, -1, 1) == 3) { // gegnerische 3er-Reihe nach
																						// rechts unten

						if (row < HEIGHT - 1 && column > 0) {
							if (board.getField(row + 1, column - 1) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
						if (row > 2 && column < WIDTH - 3) {
							if (board.getField(row - 3, column + 3) == kiToken) {
								min3erBlocked++;
							} else {
								min3erOpen++;
							}
						} else {
							min3erOpen++;
						}
					}
				}
				// bei 2 gleichen Steinen:
				if ((row > 2) && (column < WIDTH - 1)) {
					if (row(board, kiToken, row, column, -1, 1) == 2) { // eigene 2er-Reihe nach rechts unten

						if (row < HEIGHT - 1 && column > 0) {
							if (board.getField(row + 1, column - 1) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
						if (row > 1 && column < WIDTH - 2) {

							if (board.getField(row - 2, column + 2) == opponentToken) {
								max2erBlocked++;
							} else {
								max2erOpen++;
							}
						} else {
							max2erOpen++;
						}
					} else if (row(board, opponentToken, row, column, -1, 1) == 2) { // gegnerische 2er-Reihe nach
																						// rechts unten

						if (row < HEIGHT - 1 && column > 0) {// row > 2 &&
							if (board.getField(row + 1, column - 1) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
						if (row > 1 && column < WIDTH - 2) {

							if (board.getField(row - 2, column + 2) == kiToken) {
								min2erBlocked++;
							} else {
								min2erOpen++;
							}
						} else {
							min2erOpen++;
						}
					}
				}
			}
		}

		// gewichtete Reihen-Anzahl des Spielers der dran ist - die des anderen
		// Spielers:
		// max steht fuer den Ziehenden (also die KI) min fuer den Gegner

		// kiToken2FAKTOR == 1;
		// opponentToken2FAKTOR == 1;
		// opponentBlocked2FAKTOR == 5
		// kiToken3FAKTOR == 100;
		// opponentToken3FAKTOR == 500;
		// opponentBlocked3FAKTOR == 1000;

		return kiToken2FAKTOR * max2erOpen + opponentBlocked2FAKTOR * min2erBlocked + kiToken3FAKTOR * max3erOpen
				+ opponentBlocked3FAKTOR * min3erBlocked - opponentToken2FAKTOR * min2erOpen
				- kiToken2FAKTOR * max2erBlocked - opponentToken3FAKTOR * min3erOpen - kiToken3FAKTOR * max3erBlocked;
	}

	// Getter / Setter
	// -----------------------------------------------------------------
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		KI.difficulty = difficulty;
	}

	public static int getPosInfinity() {
		return POS_INFINITY;
	}

	public static int getNegInfinity() {
		return NEG_INFINITY;
	}

	public char getKiToken() {
		return kiToken;
	}

	public char getOpponentToken() {
		return opponentToken;
	}

	public static int getKitoken2faktor() {
		return kiToken2FAKTOR;
	}

	public static int getOpponenttoken2faktor() {
		return opponentToken2FAKTOR;
	}

	public static int getKitoken3faktor() {
		return kiToken3FAKTOR;
	}

	public static int getOpponenttoken3faktor() {
		return opponentToken3FAKTOR;
	}

	public GameBoard getBoard() {
		return board;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public void setFirstMoveKI(boolean firstMoveKI) {
		this.firstMoveKI = firstMoveKI;
	}

	public boolean isFirstMoveKI() {
		return this.firstMoveKI;
	}

	public boolean isKiMove() {
		return kiMove;
	}

	public void setKiMove(boolean kiMove) {
		this.kiMove = kiMove;
	}
}
//TODO 
//mit Grundlage von 4 Gewinnt KI fertig implementieren:
//Initialisierung von KI mit ihrer Schwierigkeit und einem leeren Board fuer WIDTH,HEIGTH --> done
//difficulty vom menu abfragen und medium + hardMove() fertig implementieren --> done? Problem mit minMax()
//Erkennung von kleineren Reihen hier oder in SearchRow fuer Zugbewertung  --> done
//=>erstmal hier um nur diese Klasse aendern zu muessen (DONE? siehe row()) --> done (row() angepasst)
//variabel token das die KI diese Runde verwendet bestimmen --> done