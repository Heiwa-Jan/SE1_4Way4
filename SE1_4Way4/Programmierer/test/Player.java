package model;

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

	public Player(String playerName, boolean isKI) {
		this.setName(playerName);
		this.setKI(isKI);
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

}
