package src.controller;

import java.util.Scanner;

import src.model.GameBoard;
import src.model.Menu;
import src.model.Move;
import src.model.MoveAllTokens;
import src.model.Player;
import src.model.SearchRow;
import src.view.PrintCanvas;

public class GameMain {

	private static boolean running = true;

	public static void main(String[] args) {

		Menu menu = new Menu();
		GameBoard board;
		Player player1;
		Player player2;
		Move move;
		SearchRow searchWin;
		Scanner input = new Scanner(System.in);

		while(true) { //ist das Spiel zu Ende, Rückkehr ins Menü

			menu.menuStart();	//das Menü ausführen
			player1 = new Player(menu.getPlayername1());
			player2 = new Player(menu.getPlayername2());
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
					
					//nächster Zug
					PrintCanvas.print(board.printBoard() + "\n");
					PrintCanvas.print(player2.getName()+" gib die Koordinaten einer Position ein");
					PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
					PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");

					move = new Move(board);
					board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());

					searchWin.setCurrentBoard(board);
					setRunning(!searchWin.search());

				}
				else {
					//1. Zug
					PrintCanvas.print(board.printBoard() + "\n");
					PrintCanvas.print(player2.getName()+" gib die Koordinaten einer Position ein");
					PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
					PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");

					move = new Move(board);
					board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());

					searchWin.setCurrentBoard(board);
					setRunning(!searchWin.search());

					if(!isRunning()) {
						break;
					}

					//nächster Zug
					PrintCanvas.print(board.printBoard() + "\n");
					PrintCanvas.print(player1.getName()+" gib die Koordinaten einer Position ein");
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
}
