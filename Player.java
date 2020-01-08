package src.model;

import src.controller.UserInput;
import src.view.PrintCanvas;

/*
 *  Instanzvariable isKI + getter & setter
 * 	Konstruktor mit boolean erweitert
 * 
 * 
 * */

public class Player {
	private String name;
	private char token = ' ';
	private boolean isKI;
	private int KIDifficulty;

	
	public Player(String playerName, boolean isKI) {
		this.setName(playerName);
		this.setKI(isKI);
		boolean invalidInput = true;
		
		
		if(this.isKI()) {
			PrintCanvas.print("Welche Schwierigkeit?");
			PrintCanvas.print("1 = leicht, 2 = mittel, 3 = schwer");
			while(invalidInput) {
				try {
					this.setKIDifficulty(Integer.parseInt(UserInput.readString()));
				} catch (NumberFormatException e) {

				}
				if(this.getKIDifficulty() > 3 || this.getKIDifficulty() < 1) {
					PrintCanvas.print("Bitte nur 1, 2 oder 3 eingeben");
				}
				else {
					invalidInput = false;
				}
			}
		}
	}

	// Getter und Setter

	public char getToken() {
		return token;
	}

	public void setToken(char token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
