package src.controller;

import src.model.GameBoard;
import src.model.KI;
import src.model.Menu;
import src.model.Move;
import src.model.MoveAllTokens;
import src.model.Player;
import src.model.SearchRow;
import src.view.PrintCanvas;

//TODO: yourMove()!
public class GameMain {
	private static final int MAX_DIF = 3;
	
	private boolean running;
	GameBoard board;
	Player player1;
	Player player2;
	Move move;
	SearchRow searchWin;
	KI gameKI = null;
	boolean tournament;
	boolean starts;
	boolean won;
	char token;
	
	/**
	 * Initialisierung von einem GameMain fuers Tournier noetig
	 * @param tournament
	 */
	GameMain(boolean tournament){
		this.tournament = tournament;
		if(tournament) {
			this.board = new GameBoard(7, 7);
		
		//this.gameKI = new KI(this.board, MAX_DIF);
		//initialisierung von Player mit drei Werten fuer Tournier
		player1 = new Player("This", true, true);
		player2 = new Player("Other", true, true);
		searchWin = new SearchRow(board, player1);
		running = true;
		} else {
			game();
		}
	}
	


	public void game() {
		
		

		if (!tournament) {
			
			Menu menu = new Menu();

			while (true) { // ist das Spiel zu Ende, Rückkehr ins Menü
				menu.menuStart(); // das Menü ausführen
				player1 = menu.getPlayer1();
				player2 = menu.getPlayer2();
				if (menu.isPlayer1Begins()) {
					player1.setToken('X');
					player2.setToken('O');
				} else {
					player1.setToken('O');
					player2.setToken('X');
				}
				board = new GameBoard(menu.getHeight(), menu.getWidth());
				searchWin = new SearchRow(board, player1);
				setRunning(true);
				if (menu.getPlayerNumber() == 1) {
					gameKI = new KI(board, player1.getToken(), menu.isKIBegins(), menu.getPlayer2().getKIDifficulty());
				}

				while (isRunning()) { // während niemand gewonnen hat weiterspielen
					if (menu.isPlayer1Begins()) {
						// 1. Zug
						PrintCanvas.print(board.printBoard() + "\n ");
						PrintCanvas.print(player1.getName() + " gib die Koordinaten einer Position ein");
						PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
						PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
						move = new Move(board);
						board = MoveAllTokens.move(board, move.getValidMove(), player1.getToken());
						searchWin.setCurrentBoard(board);
						setRunning(!searchWin.search());
						if (!isRunning()) {
							break;
						}
						PrintCanvas.print(board.printBoard());

						if (menu.getPlayerNumber() == 2) {
							// nächster Zug
							PrintCanvas.print(board.printBoard() + "\n");

							PrintCanvas.print(player2.getName() + " gib die Koordinaten einer Position ein");
							PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
							PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
							move = new Move(board);
							board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
						} else {
							// KI

							gameKI.setBoard(board);
							move = gameKI.kiMove();
							board = MoveAllTokens.move(board, move, gameKI.getKiToken());
							//besser ersichtlich was die KI macht:
							PrintCanvas.print(board.printBoard());
						}

						searchWin.setCurrentBoard(board);
						setRunning(!searchWin.search());
					} else {
						// 1. Zug
						// 1. Zug, 2. Spieler beginnt

						if (menu.getPlayerNumber() == 2) {
							PrintCanvas.print(board.printBoard() + "\n");
							PrintCanvas.print(player2.getName() + " gib die Koordinaten einer Position ein");
							PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
							PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");

							move = new Move(board);
							board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
						} else {
							// KI
							move = gameKI.kiMove();
							while (!move.isValidMove()) {

							}
							board = MoveAllTokens.move(board, move, player2.getToken());
						}

						searchWin.setCurrentBoard(board);
						setRunning(!searchWin.search());
						if (!isRunning()) {
							break;
						}
						// nächster Zug
						PrintCanvas.print(board.printBoard() + "\n");

						PrintCanvas.print(player1.getName() + " gib die Koordinaten einer Position ein");
						PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
						PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");

						move = new Move(board);
						board = MoveAllTokens.move(board, move.getValidMove(), player1.getToken());

						searchWin.setCurrentBoard(board);
						setRunning(!searchWin.search());
					}
				}
				// Ausgabe des Endstands und des Gewinners
				PrintCanvas.print(board.printBoard() + "\n");
				if (searchWin.isPlayer1Won()) {
					PrintCanvas.print(player1.getName() + " gewinnt!\n");
				} else {
					PrintCanvas.print(player2.getName() + " gewinnt!\n");
				}
			}
		} else { //Turnier, game() sollte gar nicht aufgerufen werden
			System.out.println("Here be Errors)");
		}
	}


	public void printBoard() {
		PrintCanvas.print(board.printBoard());
	}

	public boolean whoWon() {
		return won;
	}
	
	/**
	 * makes a kiMove, moves the tokens and checks for win
	 * @return move made by KI as String
	 */
	public String yourMove() {
		gameKI.setBoard(board);
		move = gameKI.kiMove();
		board = MoveAllTokens.move(board, move, gameKI.getKiToken());
		//Kontrollausgabe
		System.out.println("Token yourMove: " + gameKI.getKiToken());
		searchWin.setCurrentBoard(board);
		if(searchWin.search()) {
			setRunning(false);
			won = searchWin.isPlayer1Won();
		}
		//TODO: getMoveString() funktioniert noch nicht richtig
		return move.getMoveString();
	}
	
	/**
	 * moves tokens according to move made by opponent, checks for win
	 * @param myMove
	 */
	public void myMove(String myMove) {
		System.out.println("myMove-String: " + myMove);
		move = new Move(board, myMove);
		if(move.isValidMove()) {
		//dieses board = ... leert feld?
		board = MoveAllTokens.move(board, move, gameKI.getOpponentToken());
		//kontrollausgabe
		System.out.println("Token myMove: " + gameKI.getOpponentToken());
		searchWin.setCurrentBoard(board);
		
		if(searchWin.search()) {
			setRunning(false);
			won = searchWin.isPlayer1Won();
		}
		} else {
			System.out.println("Error in MyMove, move not valid");
		}
		
	}
	
	
	public boolean isRunning() {
		return running;
	}

	private void setRunning(boolean running) {
		this.running = running;
	}

	public void setStart(boolean b) {
		this.starts = b;
		if(b) {
			token = 'X';
		} else {
			token = 'O';
		}
		gameKI = new KI(board, token, b, MAX_DIF);
	}
	
}