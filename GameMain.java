package src.controller;

import src.model.GameBoard;
import src.model.KI;
import src.model.Menu;
import src.model.Move;
import src.model.MoveAllTokens;
import src.model.Player;
import src.model.SearchRow;
import src.view.PrintCanvas;

public class GameMain {
	private static boolean running;
	public static void main(String[] args) {
		Menu menu = new Menu();
		GameBoard board;
		Player player1;
		Player player2;
		Move move;
		SearchRow searchWin;
		KI gameKI = null;
	
		while(true) { //ist das Spiel zu Ende, Rückkehr ins Menü
			menu.menuStart();	//das Menü ausführen
			player1 = menu.getPlayer1();
			player2 = menu.getPlayer2();
			if(menu.isPlayer1Begins()) {
				player1.setToken('X');
				player2.setToken('O');
			}
			else {
				player1.setToken('O');
				player2.setToken('X');
			}
			board = new GameBoard(menu.getHeight(), menu.getWidth());
			searchWin = new SearchRow(board, player1);
			setRunning(true);
			if(menu.getPlayerNumber() == 1) {
				gameKI = new KI(board, player1.getToken(), menu.isKIBegins(), menu.getPlayer2().getKIDifficulty());
			}


			while(isRunning()) { //während niemand gewonnen hat weiterspielen
				if(menu.isPlayer1Begins()) {
					//1. Zug
					PrintCanvas.print(board.printBoard()+ "\n ");
					PrintCanvas.print(player1.getName()+" gib die Koordinaten einer Position ein");
					PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
					PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
					move = new Move(board);
					board = MoveAllTokens.move(board, move.getValidMove(), player1.getToken());
					searchWin.setCurrentBoard(board);
					setRunning(!searchWin.search());
					if(!isRunning()) {
						break;
					}
					PrintCanvas.print(board.printBoard());

					if (menu.getPlayerNumber() == 2) {
						//nächster Zug
						PrintCanvas.print(board.printBoard() + "\n");

						PrintCanvas.print(player2.getName() + " gib die Koordinaten einer Position ein");
						PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
						PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
						move = new Move(board);
						board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
					}
					else { 
						//KI
						
						gameKI.setBoard(board);
						move = gameKI.kiMove();
						board = MoveAllTokens.move(board, move, gameKI.getKiToken());
					}

					searchWin.setCurrentBoard(board);
					setRunning(!searchWin.search());
				}
				else {
					//1. Zug
					//1. Zug, 2. Spieler beginnt

					if (menu.getPlayerNumber() == 2) {
						PrintCanvas.print(board.printBoard() + "\n");
						PrintCanvas.print(player2.getName()+" gib die Koordinaten einer Position ein");
						PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
						PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");

						move = new Move(board);
						board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
					}
					else {			
						//KI
						move = gameKI.kiMove();
						while(!move.isValidMove()) {

						}
						board = MoveAllTokens.move(board, move, player2.getToken());
					}

					searchWin.setCurrentBoard(board);
					setRunning(!searchWin.search());
					if(!isRunning()) {
						break;
					}
					//nächster Zug
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
			//Ausgabe des Endstands und des Gewinners
			PrintCanvas.print(board.printBoard() + "\n");
			if(searchWin.isPlayer1Won()) {
				PrintCanvas.print(player1.getName() + " gewinnt!\n");
			}
			else {
				PrintCanvas.print(player2.getName() + " gewinnt!\n");
			}
		}
	}


	//Getter und Setter
	public static boolean isRunning() {
		return running;
	}
	private static void setRunning(boolean running) {
		GameMain.running = running;
	}

	//TODO
	//GameMain in Game und Controller/Wrapperklasse zerlegen, Menuaufruf nicht aus Game
	//Pruefen ob Turnier gegen anderes Spiel stattfindet als erstes in isRunning
	//whoWon()
	//yourMove() myMove() hinzufuegen
	//Ausgaben alle ueber view, fehlende toString()s anlegen

	//siehe Menu, Gameboard, Move, KI
}