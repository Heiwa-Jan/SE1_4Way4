package test;
	import java.util.Scanner;

	import src.model.Player;
	import src.view.PrintCanvas;
/*
 *	Line 78+- Readplayername if(playernumber==1)
 *	Ki Aufruf hinzugefügt
 * 
 * */
	
	
	
	@SuppressWarnings("unused")
	public class Menu {
		private boolean running;
		private boolean AI;
		private int difficulty;
		private int currentStep;
		
		// prototypen
		private int playerNumber; 	//durch boolean ersetzen? KI Game = true/false
		private int width;
		private int height;
		private String playername1;	
		private String playername2;
		private boolean player1Begins = true;
		private KI gameKI;
		
		Scanner input = new Scanner(System.in);

		/**
		 * defines the order, in which the menu parts are called
		 */
		public void menuStart() {
			this.readBoardSize();
			this.readPlayerNumber();
			this.readPlayerNames();
			this.whoStarts();
//			instantiatePlayers();
//			instantiateGameboard();
//			instantiateStartGame();
			
			//input.close();
		}
		// getNumPlayer Platzhalter Aufruf in UserInput -- Übergabe an >>> Player

		/**
		 * gets the user input for [playerNumber] ( number of players)
		 */
		private void readPlayerNumber() {
			boolean inputInvalid = true;
			
			PrintCanvas.print("Wie viele Spieler?");
			PrintCanvas.print("(1: Alleine gegen den Computer."); // Nicht im Prototyp enthalten
			PrintCanvas.print(" 2: Zuzweit gegen einander.)");
			
			while(inputInvalid) {	//wenn der Input fehlerhaft ist, wird ein neuer abgefragt, bis dieser korrekt ist
				this.setPlayerNumber(input.nextInt());
				if(this.getPlayerNumber() == 1 || this.getPlayerNumber() == 2) {
					inputInvalid = false;
					if(this.getPlayerNumber()==1) {
						// Einzelspieler Auswahl um gegen KI zu spielen
						setGameKI( new KI(this.getHeight(),this.getWidth()));
						
					}
				}
				else {
					PrintCanvas.print("Bitte 1 oder 2 angeben!\n");
				}
			}
		}
		
		/**
		 * gets user input for [playerName1] and [playerName2]
		 */
		private void readPlayerNames() {
			if(this.playerNumber == 1) {	//wenn ein Spieler gegen die KI spielt, muss dieser seinen Namen angeben
				PrintCanvas.print("Bitte gib deinen Namen ein: ");
				this.setPlayername1(input.next());
				this.setPlayername2("KI");
				
//				PrintCanvas.print("In dieser Version noch nicht verfügbar\n");	//solange keine KI implementiert ist, ist 2 Spieler default
//				this.setPlayerNumber(2);
				
			}
			if(this.playerNumber == 2) {	//wenn 2 Spieler spielen, müssen beide ihre Namen angeben
				PrintCanvas.print("Spieler 1, bitte gib deinen Namen ein: ");
				this.setPlayername1(input.next());
				PrintCanvas.print("Spieler 2, bitte gib deinen Namen ein: ");
				this.setPlayername2(input.next());
			}		
		}
		
		/**
		 * gets user input for the [width] of the board (number of columns)
		 */
		private void readWidth() {
			boolean inputInvalid = true;
			
			PrintCanvas.print("Wie breit soll das Spielfeld sein?");
			PrintCanvas.print("(Bitte nur Werte zwischen 7 und 10 angeben)");
			
			while(inputInvalid) {	//ist der Input fehlerhaft, wird ein neuer abgefragt, bis dieser korrekt ist

				this.setWidth(input.nextInt());	
				
				if(this.getWidth() >= 7 && this.getWidth() <= 10){	//die Breite muss im Bereich (7 <= input <= 10) sein
					inputInvalid = false;
				}
				else {
					PrintCanvas.print("Bitte nur Werte zwischen 7 und 10 angeben");
				}
			}
		}
		
		/**
		 * gets user input for the [height] of the board (number of rows)
		 */
		private void readHeight() {
			boolean inputInvalid = true;
			
			PrintCanvas.print("Wie hoch soll das Spielfeld sein?");
			
			while(inputInvalid) {	//ist der Input fehlerhaft, wird ein neuer abgefragt, bis dieser korrekt ist
				PrintCanvas.print("(Bitte nur Werte zwischen 7 und 10 angeben)");	
				this.setHeight(input.nextInt());	
				
				if(this.getHeight() >= 7 && this.getHeight() <= 10){	//die Höhe muss in Bereich (7 <= input <= 10) sein
					inputInvalid = false;
				}
				else {
					PrintCanvas.print("Wie hoch soll das Spielfeld sein?");
				}
			}
		}

//		private void instantiateStartGame() {
//			// Game currentGame = startGame();
//		}
	//
//		private void instantiateGameboard() {
//			
//			
//			//Gameboard currentBoard new Gameboard(width,heigth);
	//
//		}
//		// getBoardSize Platzhalter Aufruf in UserInput -- Übergabe an >>> Gameboard
		
		/**
		 * gets user input for the size of the board (width x height)
		 */
		private void readBoardSize() {
			this.readWidth();
			this.readHeight();
		}

		/**
		 * instantiates one or two players, depending on [playerNumber]
		 */
		private void instantiatePlayers() {
			if(this.playerNumber == 1) {
				Player Player1 = new Player(this.playername1, false);// vorher: parameterloser Konstruktor
				Player KI	   = new Player(this.playername2,true);
			}
			if(this.playerNumber == 2) {
				Player Player1 = new Player(this.playername1, false);
				Player Player2 = new Player(this.playername2, false);
				}
		}

		/**
		 * gets user input to decide, which player starts
		 */
		private void whoStarts() {
			boolean inputInvalid = true;
			
			PrintCanvas.print("Wer beginnt? Spieler 1 oder 2?");
			
			while (inputInvalid) {	//wenn nich 1 oder 2 eingegeben wird, wird ein neuer verlangt, bis 1/2 angegeben wurde
				
				int next = input.nextInt();
				if(next == 1) {
					inputInvalid = false;
				}
				else if(next == 2) {
					this.setPlayer1Begins(false);
					inputInvalid = false;
				}
				else {
					PrintCanvas.print("Bitte nur 1 oder 2");
				}
			}
			
			
		}

	//
//		public String toString() {
//			return null;
//		}

		// Getter und Setter:

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

		public KI getGameKI() {
			return gameKI;
		}

		public void setGameKI(KI gameKI) {
			this.gameKI = gameKI;
		}
		
//		public boolean isRunning() {
//			return running;
//		}
	//
//		public void setRunning(boolean running) {
//			this.running = running;
//		}
	//
//		public boolean isAI() {
//			return AI;
//		}
	//
//		public void setAI(boolean aI) {
//			AI = aI;
//		}
	//	
//		public int getCurrentStep() {
//			return currentStep;
//		}
	//
//		public void setCurrentStep(int currentStep) {
//			this.currentStep = currentStep;
//		}
	}

