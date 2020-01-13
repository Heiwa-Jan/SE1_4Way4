package src.model;

import src.controller.UserInput;
import src.view.PrintCanvas;

public class Player {
	private String name;
	private char token = ' ';
	private boolean isKI;
	private int KIDifficulty;

	/**
	 * Initialisiert einen neuen Spieler und fragt bei 1Spieler die Schwierigkeit
	 * der KI ab.
	 * 
	 * @param playerName
	 * @param isKI
	 */
	public Player(String playerName, boolean isKI) {
		this.setName(playerName);
		this.setKI(isKI);
		boolean invalidInput = true;

		// Wird gegen die KI gespielt, Abfrage der Schwierigkeit
		if (this.isKI()) {
			PrintCanvas.print("Welche Schwierigkeit?");
			PrintCanvas.print("1 = leicht, 2 = mittel, 3 = schwer");
			while (invalidInput) {
				try {
					this.setKIDifficulty(Integer.parseInt(UserInput.readString()));
				} catch (NumberFormatException e) {

				}
				if (this.getKIDifficulty() > 3 || this.getKIDifficulty() < 1) {
					PrintCanvas.print("Bitte nur 1, 2 oder 3 eingeben");
				} else {
					invalidInput = false;
				}
			}
		}
	}

	// Getter/Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getToken() {
		return token;
	}

	public void setToken(char token) {
		this.token = token;
	}

	public boolean isKI() {
		return isKI;
	}

	public void setKI(boolean isKI) {
		this.isKI = isKI;
	}

	public int getKIDifficulty() {
		return KIDifficulty;
	}

	public void setKIDifficulty(int kIDifficulty) {
		KIDifficulty = kIDifficulty;
	}

}
