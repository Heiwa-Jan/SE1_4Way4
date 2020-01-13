package src.model;

import src.controller.UserInput;
import src.model.Player;
import src.view.PrintCanvas;

public class Menu {
	//Auskommentiert, da im Original nicht benutzt
	//private boolean running;
	//private boolean AI;
	private int difficulty;
	//private int currentStep;

	// Prototypen
	private int playerNumber;
	private int width;
	private int height;
	private String playername1;
	private String playername2;
	private boolean player1Begins = true;
	private boolean KIBegins;

	// private KI gameKI;--> in GameMain
	private Player player1;
	private Player player2;
	
	/**
	 * Legt fest wie die Menüreihenfolge aussieht
	 */
	public void menuStart() {

		//this.readPlayerNumber();
		//this.readPlayerNames();
		this.readBoardSize();
		//this.whoStarts();
		//this.instantiatePlayers();
	}

	/**
	 * Abfrage ob Ein- oder Zwei-Spieler Modus
	 */
	private void readPlayerNumber() {
		boolean inputInvalid = true;
		// Spieltyp Abfrage
		PrintCanvas.print("Wie viele Spieler?");
		PrintCanvas.print("(1: Alleine gegen den Computer.");
		PrintCanvas.print(" 2: Zuzweit gegen einander.)");

		while (inputInvalid) {
			try {
				this.setPlayerNumber(Integer.parseInt(UserInput.readString()));
			} catch (NumberFormatException e) {
			}
			if (this.getPlayerNumber() == 1 || this.getPlayerNumber() == 2) {
				inputInvalid = false;
			} else {
				PrintCanvas.print("Bitte 1 oder 2 angeben!\n");
			}
		}
	}

	/**
	 * Ermöglicht die Eingabe der Spielernamen
	 */
	private void readPlayerNames() {
		// wenn ein Spieler gegen die KI spielt, muss dieser seinen Namen angeben
		if (this.playerNumber == 1) {
			PrintCanvas.print("Bitte gib deinen Namen ein: ");
			this.setPlayername1(UserInput.readString());
			this.setPlayername2("KI");
		}
		// wenn 2 Spieler spielen, müssen beide ihre Namen angeben
		if (this.playerNumber == 2) {
			PrintCanvas.print("Spieler 1, bitte gib deinen Namen ein: ");
			this.setPlayername1(UserInput.readString());
			PrintCanvas.print("Spieler 2, bitte gib deinen Namen ein: ");
			this.setPlayername2(UserInput.readString());
		}
	}

	/**
	 * Ermöglicht Eingabe der Spielfeld Weite [board:columns]
	 */
	private void readWidth() {
		boolean inputInvalid = true;

		PrintCanvas.print("Wie breit soll das Spielfeld sein?");
		PrintCanvas.print("(Bitte nur Werte zwischen 7 und 10 angeben)");

		while (inputInvalid) {
			try {
				this.setWidth(Integer.parseInt(UserInput.readString()));
			} catch (NumberFormatException e) {

			}

			if (this.getWidth() >= 7 && this.getWidth() <= 10) {
				inputInvalid = false;
			} else {
				PrintCanvas.print("Bitte nur Werte zwischen 7 und 10 angeben");
			}
		}
	}

	/**
	 * Ermöglicht Eingabe der Spielfeld Höhe [board:rows]
	 */
	private void readHeight() {
		boolean inputInvalid = true;

		PrintCanvas.print("Wie hoch soll das Spielfeld sein?");

		while (inputInvalid) {
			PrintCanvas.print("(Bitte nur Werte zwischen 7 und 10 angeben)");
			try {
				this.setHeight(Integer.parseInt(UserInput.readString()));
			} catch (NumberFormatException e) {

			}

			if (this.getHeight() >= 7 && this.getHeight() <= 10) {
				inputInvalid = false;
			} else {
				PrintCanvas.print("Wie hoch soll das Spielfeld sein?");
			}
		}
	}

	/**
	 * Benutzereingabe für die Spielfeld Größe [boardsize:widthxheight]
	 */
	private void readBoardSize() {
		this.readWidth();
		this.readHeight();
	}

	/**
	 * Instanziert Ein- oder Zwei-Player, anhand [playerNumber]
	 */
	private void instantiatePlayers() {
		if (this.playerNumber == 1) {
			player1 = new Player(this.playername1, false);
			player2 = new Player(this.playername2, true);
		}
		if (this.playerNumber == 2) {
			player1 = new Player(this.playername1, false);
			player2 = new Player(this.playername2, false);
		}
	}

	/**
	 * Welcher Spieler beginnt, anhand der Benutzereingabe
	 */
	private void whoStarts() {
		boolean inputInvalid = true;

		if (this.getPlayerNumber() == 2) {
			PrintCanvas.print("Wer beginnt? Spieler 1 oder 2?");
			while (inputInvalid) { // wenn nicht 1 oder 2 eingegeben wird, wird ein neuer verlangt, bis 1/2
									// angegeben wurde

				try {
					int next = Integer.parseInt(UserInput.readString());
					if (next == 1) {
						inputInvalid = false;
					} else if (next == 2) {
						this.setPlayer1Begins(false);
						inputInvalid = false;
					} else {
						PrintCanvas.print("Bitte nur 1 oder 2");
					}
				} catch (NumberFormatException e) {
					PrintCanvas.print("Bitte nur 1 oder 2");
				}
			}
		} else {
			PrintCanvas.print("Wer beginnt? Spieler oder KI?");
			while (inputInvalid) { // wenn nicht 1 oder 2 eingegeben wird, wird ein neuer verlangt, bis 1/2
									// angegeben wurde

				int next;
				try {
					next = Integer.parseInt(UserInput.readString());
					if (next == 1) {
						this.setKIBegins(false);
						inputInvalid = false;
					} else if (next == 2) {
						this.setKIBegins(true);
						inputInvalid = false;
					} else {
						PrintCanvas.print("Bitte nur 1 oder 2");
					}
				} catch (NumberFormatException e) {
					PrintCanvas.print("Bitte nur 1 oder 2");
				}
			}
		}
	}

	// Getter/Setter
	public String getPlayername1() {
		return playername1;
	}

	private void setPlayer1Begins(boolean player1Begins) {
		this.player1Begins = player1Begins;
	}

	public boolean isPlayer1Begins() {
		return player1Begins;
	}

	private void setPlayername1(String playername1) {
		this.playername1 = playername1;
	}

	public String getPlayername2() {
		return playername2;
	}

	private void setPlayername2(String playername2) {
		this.playername2 = playername2;
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	private void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getDifficulty() {
		return difficulty;
	}

	private void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isKIBegins() {
		return KIBegins;
	}

	public void setKIBegins(boolean kIBegins) {
		KIBegins = kIBegins;
	}
}
