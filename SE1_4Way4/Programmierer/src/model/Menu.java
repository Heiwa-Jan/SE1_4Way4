package model;

import java.util.Scanner;

@SuppressWarnings("unused")
public class Menu {
	private boolean running;
	private boolean AI;
	private int difficulty;
	private int currentStep;
	
	// prototypen
	private int NumPlayers;
	private int width;
	private int height;
	private String playername1;	
	private String playername2;
	
	Scanner input = new Scanner(System.in);
	
//	public boolean isRunning() {
//		return running;
//	}
//
//	public void setRunning(boolean running) {
//		this.running = running;
//	}
//
//	public boolean isAI() {
//		return AI;
//	}
//
//	public void setAI(boolean aI) {
//		AI = aI;
//	}
//
	public int getDifficulty() {
		return difficulty;
	}

//
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

//
//	public int getCurrentStep() {
//		return currentStep;
//	}
//
//	public void setCurrentStep(int currentStep) {
//		this.currentStep = currentStep;
//	}
//
	// TODO
	private void menuStart() {
		System.out.println("Sie haben 4Way4 gestartet: Viel Spass!");
		getNumPlayers();
//		getDifficulty();  Nicht für Prototyp
		getPlayerNames();
		whoStarts();
		instantiatePlayers();
		getBoardSize();
		instantiateGameboard();
		instantiateStartGame();

	}
	// getNumPlayer Platzhalter Aufruf in UserInput -- Übergabe an >>> Player
	private void getNumPlayers() {
		System.out.println("Wie viele Spieler?");
		System.out.println("1. Alleine gegen den Computer."); // Nicht im Prototyp
		System.out.println("2. Zuzweit gegen einander.");
		this.NumPlayers = input.nextInt();
	}

	private void instantiateStartGame() {
		// Game currentGame = startGame();
	}

	private void instantiateGameboard() {
		
		
		//Gameboard currentBoard new Gameboard(width,heigth);

	}
	// getBoardSize Platzhalter Aufruf in UserInput -- Übergabe an >>> Gameboard
	private void getBoardSize() {
		
		// width
		System.out.println("Wie viele Spalten soll das Spielfeld haben?");
			this.width = input.nextInt();
		
		// height	
		System.out.println("Wie viele Zeilen soll das Spielfeld haben?");
			this.height = input.nextInt();
	}

	private void instantiatePlayers() {
		if(this.NumPlayers == 1) {
			Player Player1 = new Player();
		}
		if(this.NumPlayers == 2) {
			Player Player1 = new Player(this.playername1);
			Player Player2 = new Player(this.playername2);
			}
	}

	private void whoStarts() {
		// TODO Auto-generated method stub

	}

	private void getPlayerNames() {
		if(this.NumPlayers == 1) {
			System.out.println("Bitte geben Sie ihren Namen ein:");
			this.playername1 = input.nextLine();
		}
		if(this.NumPlayers == 2) {
			System.out.println("Bitte geben Sie den Namen des ersten Spielers ein:");
			this.playername1 = input.nextLine();
			System.out.println("Bitte geben Sie den Namen des zweiten Spielers ein:");
			this.playername2 = input.nextLine();
			}

	}
//
//	public String toString() {
//		return null;
//	}

}
