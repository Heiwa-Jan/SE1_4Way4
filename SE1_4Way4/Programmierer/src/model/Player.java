package src.model;

public class Player {

	private String name;
	private char token = ' ';
	

	public Player(String playerName) {
		this.setName(playerName);
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
}
