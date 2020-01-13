package src.model;

import src.view.PrintCanvas;

public class KI {
	private static final int LEER = 0;
	private static final int MAX = 1;
	private static final int MIN = -1;
	// Bemerkung: MAX=true, MIN=false

	// Gewichte fuer die Bewertungsfunktion
	private static final int MAX2FAKTOR = 1;
	private static final int MIN2FAKTOR = 10;
	private static final int MAX3FAKTOR = 100;
	private static final int MIN3FAKTOR = 1000;

	// Positiv und negativ Unendlich
	private static final int POS_INFINITY = (int) Double.POSITIVE_INFINITY;
	private static final int NEG_INFINITY = (int) Double.NEGATIVE_INFINITY;

	/**
	 * Ermittelt die aktuelle SpielSituation
	 */
	static class SpielSituation {

		private int[][] feld = new int[7][7];
		private boolean zug;
		private int width = 7;
		private int height = 7;

		/**
		 * Konstruktor, der zu Beginn, alle Felder auf LEER setzt und den zug auf false
		 */
		public SpielSituation(GameBoard board) {
			int[][] trans = board.getBoard();

			for (int reihe = 0; reihe < 7; reihe++) {
				for (int spalte = 0; spalte < 7; spalte++) {
					this.feld[reihe][spalte] = trans[reihe][spalte];
				}
			}

			this.zug = board.getTurn();
		}

		public SpielSituation() {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					this.feld[x][y] = LEER;
				}
			}
			zug = false;
		}

		// get-/set- Methoden für feld und zug
		/**
		 * Gibt das Spielfeld zurück
		 * 
		 * @return this.feld
		 */
		public int[][] getFeld() {
			return this.feld;
		}

		/**
		 * Gibt den aktuellen Spielzug zurück
		 * 
		 * @return zug
		 */
		public boolean getZug() {
			return this.zug;
		}

		/**
		 * Setzt den aktuellen Spieler als zug
		 * 
		 * @param spieler
		 */
		public void setZug(boolean spieler) {
			this.zug = spieler;
		}

		/**
		 * Setzt den übergebenen Wert and die Position [x][y] im Spielfeld
		 * 
		 * @param x
		 * @param y
		 * @param wert
		 */
		public void setFeld(int x, int y, int wert) {
			this.feld[x][y] = wert;
		}

		/**
		 * Prüft ob ein Stein in gegebene Spalte geworfen werden kann
		 * 
		 * @param spalte
		 * @return boolean
		 */
		boolean gueltigerZug(int spalte) {
			if ((0 <= spalte) && (spalte < width)) {
				int y = 0;
				while ((y < height) && (this.feld[spalte][y] != LEER)) {
					y++;
				}
				if (y < height) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		}

		/**
		 * Führt einen Zug aus, wenn dieser ausgeführt werden kann
		 * 
		 * @param spalte
		 * @return boolean
		 */
		public boolean setzeStein(int spalte) {
			if (!this.gueltigerZug(spalte)) {
				return false; // da ungültige Position
			}
			int y = 0;
			while ((y < height) && (this.feld[spalte][y] != LEER)) {
				y++;
			}
			if (this.zug) {
				this.feld[spalte][y] = MAX;
			} else {
				this.feld[spalte][y] = MIN;
			}
			this.zug = !this.zug;
			return true; // ein gültiger Zug
		}

		/**
		 * Kopiert die aktuelle SpielSituation und gibt diese zurück
		 * 
		 * @return spielKopie
		 */
		private SpielSituation copy() {
			SpielSituation spielKopie = new SpielSituation();
			spielKopie.setZug(this.zug);
			for (int x = 0; x < this.width; x++) {
				for (int y = 0; y < this.height; y++) {
					spielKopie.setFeld(x, y, this.getFeld()[x][y]);
				}
			}
			return spielKopie;
		}
	}

	/**
	 * Erkennt 2er, 3er oder 4er Reihen dx und dy geben die Richtung an, in die
	 * gesucht wird (ab Position x,y)
	 * 
	 * @param feld
	 * @param spieler
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @return anzahl der Steine
	 */
	private static int istReihe(int[][] feld, int spieler, int x, int y, int dx, int dy) {
		int anzahl = 0;
		// 4 freie oder von spieler belegte Felder in Richtung (dx,dy)
		if (((feld[x][y] == spieler) || (feld[x][y] == LEER))
				&& ((feld[x + 1 * dx][y + 1 * dy] == spieler) || (feld[x + 1 * dx][y + 1 * dy] == LEER))
				&& ((feld[x + 2 * dx][y + 2 * dy] == spieler) || (feld[x + 2 * dx][y + 2 * dy] == LEER))
				&& ((feld[x + 3 * dx][y + 3 * dy] == spieler) || (feld[x + 3 * dx][y + 3 * dy] == LEER))) {
			// Zaehle die Anzahl von spieler belegter Felder
			// for (int i = 0; i < 4; i++) {
			for (int i = 0; ((x + i * dx) < 7) && (((y + i * dy) < 7) && ((y + i * dy) > -1)); i++) {
				// System.out.println("Bin in der for-schleife");
				/*
				 * if (feld[x + i * dx][y + i * dy] == spieler) { if (anzahl != 0 && (feld[x + i
				 * * dx][y + i * dy] != spieler * -1)) { break; //
				 * System.out.println("Habs in if geschafft"); }else if(anzahl == 0) { anzahl++;
				 * }else { anzahl++; } }
				 */
				if (anzahl != 0 && (feld[x + i * dx][y + i * dy] == spieler * -1)) {
					break;
				} else if (feld[x + i * dx][y + i * dy] == spieler) {
					anzahl++;
				}
			}
		}
		return anzahl;
	}

	/**
	 * Bewertet das Spielfeld anhand der gegeben Spielsituation
	 * 
	 * @param spiel
	 * @return bewertung
	 */
	static int bewertung(SpielSituation spiel) {

		int[][] feld = spiel.getFeld();

		int min2er = 0;
		int max2er = 0;
		int min3er = 0;
		int max3er = 0;
		// Gehe das Spielfeld durch und bewerte,...
		for (int x = 0; x < spiel.width; x++) {
			for (int y = 0; y < spiel.height; y++) {
				if (spiel.height - y >= 4) {
					// bei 4 gleichen Steinen:
					// Folgender Block bestimmt Anzahlen nach rechts
					if (istReihe(feld, MAX, x, y, 0, 1) >= 4)
						return POS_INFINITY; // gewonnen
					else if (istReihe(feld, MIN, x, y, 0, 1) >= 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 0, 1) == 3)
						max3er++;
					else if (istReihe(feld, MIN, x, y, 0, 1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 0, 1) == 2)
						max2er++;
					else if (istReihe(feld, MIN, x, y, 0, 1) == 2)
						min2er++;
				}
				if ((spiel.height - y >= 4) && (spiel.width - x >= 4)) {
					// bei 4 gleichen Steinen:
					// Bestimmung Anzahl nach rechts unten
					if (istReihe(feld, MAX, x, y, 1, 1) >= 4)
						return POS_INFINITY; // gewonnen
					else if (istReihe(feld, MIN, x, y, 1, 1) >= 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, 1) == 3)
						max3er++;
					else if (istReihe(feld, MIN, x, y, 1, 1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, 1) == 2)
						max2er++;
					else if (istReihe(feld, MIN, x, y, 1, 1) == 2)
						min2er++;
				}
				if (spiel.width - x >= 4) {
					// bei 4 gleichen Steinen:
					// Bestimmung Anzahl nach unten
					if (istReihe(feld, MAX, x, y, 1, 0) >= 4)
						return POS_INFINITY; // gewonnen
					else if (istReihe(feld, MIN, x, y, 1, 0) >= 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, 0) == 3)
						max3er++;
					else if (istReihe(feld, MIN, x, y, 1, 0) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, 0) == 2)
						max2er++;
					else if (istReihe(feld, MIN, x, y, 1, 0) == 2)
						min2er++;
				}
				if ((spiel.width - x >= 4) && (y >= 3)) {
					// bei 4 gleichen Steinen:
					// Bestimmung anzahl unten links
					if (istReihe(feld, MAX, x, y, 1, -1) >= 4)
						return POS_INFINITY; // gewonnen
					else if (istReihe(feld, MIN, x, y, 1, -1) >= 4)
						return NEG_INFINITY; // verloren
					// bei 3 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, -1) == 3)
						max3er++;
					else if (istReihe(feld, MIN, x, y, 1, -1) == 3)
						min3er++;
					// bei 2 gleichen Steinen:
					else if (istReihe(feld, MAX, x, y, 1, -1) == 2)
						max2er++;
					else if (istReihe(feld, MIN, x, y, 1, -1) == 2)
						min2er++;
				}
			}

		}
		return MAX2FAKTOR * max2er + MAX3FAKTOR * max3er - MIN2FAKTOR * min2er - MIN3FAKTOR * min3er;
	}

	/**
	 * Berechnet den Minimax-Wert
	 * 
	 * @param spiel
	 * @param tiefe
	 * @param alpha
	 * @param beta
	 * @return minimaxLokal
	 */
	private static int minimaxWert(SpielSituation spiel, int tiefe, int alpha, int beta) {
		// Temporäre Spielsituation
		SpielSituation spielTemporaer;
		// Temporärer Minimax-Wert
		int minimaxTemporaer;
		// Lokaler Minimx-Wert
		int minimaxLokal;
		// Initialisiere den bisher besten gefundenen Wert
		if (spiel.getZug()) { // Ist Computer (MAX) am Zug
			minimaxLokal = alpha;
		} else {
			minimaxLokal = beta;
		}
		// Abbruch bei erreichter Maximaltiefe, aktuelle Situation
		// wird mit Funktion bewertung() bewertet.
		if (tiefe == 0) {
			return bewertung(spiel);
		} else { // Ansonsten untersuche rekursiv alle möglichen Züge
			for (int spalte = 0; spalte < spiel.width; spalte++) {
				// System.out.println("Spalte: " + spalte);
				spielTemporaer = spiel.copy();
				// Ist es ein gültiger Zug
				if (spielTemporaer.setzeStein(spalte)) {
					minimaxTemporaer = minimaxWert(spielTemporaer, tiefe - 1, alpha, beta);
					// Merke dir min./max. Bewertung, je nachdem wer am Zug ist
					if (spiel.getZug()) {
						minimaxLokal = java.lang.Math.max(minimaxTemporaer, minimaxLokal);
						alpha = minimaxLokal;
						if (alpha > beta) {
							return beta;
						}
					} else {
						minimaxLokal = java.lang.Math.min(minimaxTemporaer, minimaxLokal);
						beta = minimaxLokal;
						if (beta < alpha) {
							return alpha;
						}
					}
				}
			}
			return minimaxLokal;
		}

	}

	/**
	 * Berechnet den besten Zug (für MAX)
	 * 
	 * @param spiel
	 * @param tiefe
	 * @return zug
	 */
	private static int[] berechneZugArray(SpielSituation spiel, int tiefe) {
		// Merke dir die Bewertungen der Züge
		int[] wert = new int[spiel.width];

		System.out.print("Suche Zug: ");

		SpielSituation spielTemporaer;
		// probiere alle möglichen Zuege
		final long timeStart = System.currentTimeMillis();
		for (int spalte = 0; spalte < spiel.width; spalte++) {
			if (spiel.gueltigerZug(spalte)) {
				spielTemporaer = spiel.copy();
				spielTemporaer.setzeStein(spalte);
				wert[spalte] = minimaxWert(spielTemporaer, tiefe, NEG_INFINITY, POS_INFINITY);
				System.out.print(spalte + ":" + wert[spalte] + " ");
			} else {
				System.out.print(spalte + ":-- ");
			}
		}
		// Eventuell falsch verschachtelt???
		// Suche hoechsten Wert
		int zug = 0;
		// POS ändern zu NEG
		int max = POS_INFINITY;
		for (int spalte = 0; spalte < spiel.width; spalte++) {
			// >= zu > somit sollte es keine Probleme mit gleichen Werten geben
			// < ändern zu >
			if ((wert[spalte] < max) && (spiel.gueltigerZug(spalte))) {
				zug = spalte;
				max = wert[spalte];
			}
		}
		System.out.println(" => " + zug);
		final long timeEnd = System.currentTimeMillis();
		System.out.println("Verlaufszeit der Schleife: " + (timeEnd - timeStart) + " Millisek.");
		int[] result = new int[] { zug, max };
		return result;
	}

	public static String giveMove(GameBoard board, int tiefe) {
		int[][] save = new int[4][2];
		Move check = new Move(board);

		GameBoard rotate = board.copy();

		for (int i = 0; i < 4; i++) {
			// /*
			// System.out.println("Boardausgbae vor dem rotieren:");
			// PrintCanvas.printBoard(rotate);
			GameBoard copy = rotate.copy();
			int[][] t = copy.getBoard();
			// System.out.println("Ausgabe Inhalt von Copy:");
			// PrintCanvas.printArray(t);
			t = MoveAllTokens.moveAllDown(t);
			// System.out.println("Ausgabe Inhalt von Copy nach MoveAllDown:");
			// PrintCanvas.printArray(t);
			copy.setBoard(t);
			SpielSituation temp = new SpielSituation(copy);
			save[i] = berechneZugArray(temp, tiefe);
			rotate.setBoard(rotate.rotateBoard());
			// System.out.println("Boardausgbae nach dem rotieren:");
			// PrintCanvas.printBoard(rotate);
			// */
		}

		int compare = save[0][1];
		int rem = 0;
		for (int i = 1; i < 4; i++) {
			if (save[i][1] < compare) {
				if (save[i][1] != NEG_INFINITY) {
					compare = save[i][1];
					rem = i;
				}
			}
		}
		String move = createMove(save, rem);
		if (check.isValidMove(move)) {
			return move;
		} else {
			compare = save[0][1];
			for (int i = 1; i < rem; i++) {
				if (save[i][1] <= compare) {
					if (save[i][1] != NEG_INFINITY) {
						compare = save[i][1];
						rem = i;
					}
				}
				move = createMove(save, rem);
			}
			return move;
		}
	}

	public static String createMove(int[][] save, int rem) {
		String move = "";
		switch (rem) {
		case 0:
			move += (char) (save[rem][0] + 97);
			move += "7";
			if (move.equals("a7") || move.equals("g7")) {
				move += "u";
			}
			break;
		case 1:
			move += (save[rem][0] + 1);
			move += "g";
			if (move.equals("1g") || move.equals("7g")) {
				move += "r";
			}
			break;
		case 2:
			move += (char) ((save[rem][0]) + 97);
			move += "1";
			if (move.equals("a1") || move.equals("g1")) {
				move += "d";
			}
			break;
		case 3:
			move += ((save[rem][0]) + 1);
			move += "a";
			if (move.equals("1a") || move.equals("7a")) {
				move += "l";
			}
			break;
		}
		return move;
	}
}
