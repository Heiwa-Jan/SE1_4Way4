package src.controller;

import java.util.Scanner;

import src.model.GameBoard;
import src.model.Menu;
import src.model.Move;
import src.model.MoveAllTokens;
import src.model.Player;
import src.view.PrintCanvas;




public class GameMain {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		GameBoard board;
		Player player1;
		Player player2;
		Move move;
		Scanner input = new Scanner(System.in);
		
		menu.menuStart();
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
		while(true) { // später: isRunning()
			
			if(menu.isPlayer1Begins()) {
				PrintCanvas.print(board.printBoard());
				PrintCanvas.print(player1.getName()+" gib die Koordinaten einer Position ein");
				PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
				PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
				
				move = new Move(board);
				board = MoveAllTokens.move(board, move.getValidMove(), player1.getToken());
				
				PrintCanvas.print(board.printBoard());
				PrintCanvas.print(player2.getName()+" gib die Koordinaten einer Position ein");
				PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
				PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
				
				move = new Move(board);
				board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
				
			}
			else {
				PrintCanvas.print(board.printBoard());
				PrintCanvas.print(player2.getName()+" gib die Koordinaten einer Position ein");
				PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
				PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
				
				move = new Move(board);
				board = MoveAllTokens.move(board, move.getValidMove(), player2.getToken());
				
				PrintCanvas.print(board.printBoard());
				PrintCanvas.print(player1.getName()+" gib die Koordinaten einer Position ein");
				PrintCanvas.print("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
				PrintCanvas.print("(r = rechts, l = links, u = oben, d = unten)");
				
				move = new Move(board);
				board = MoveAllTokens.move(board, move.getValidMove(), player1.getToken());
				
			}		
		}
		//input.close();		//erst nutzbar, wenn keine Endlosschleife mehr vorhanden!
								//ACHTUNG: sobald einmal geschlossen, kein weiterer Userinput möglich, bis das gesamte Programm neugestartet wird
	
		//die gesamte Eingabe auslagern in separate Klasse in package "View"?
	
	}
}
