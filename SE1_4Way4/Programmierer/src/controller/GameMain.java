package src.controller;

import src.model.*;
import src.view.PrintCanvas;
import java.util.Scanner;

public class GameMain {
	private static final int MIN = -1;
	private static final int MAX = 1;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int turn = 0;

		Menu menu = new Menu();
		menu.menuStart();
		GameBoard board = new GameBoard(menu.getHeight(), menu.getWidth());
		Move move = new Move(board);
		//KI ki = new KI(board);
		String input;
		board.setTurn(false);
		while (turn < 49) {

			PrintCanvas.printBoardBorders(board);
			if(turn % 2 == 1) {
				/*
				input = KI02.giveMove(board, 3);
				System.out.println("------");
				System.out.println(input + " KI 1");
				System.out.println("------");
				*/
				PrintCanvas.printUserIn();
				input = in.next();	
			}else {
				input = KI.giveMove(board, 4);
				System.out.println("------");
				System.out.println(input + " KI 2");
				System.out.println("------");
			}
			
			//System.out.println(input.length());
			//System.out.println(move.isValidMove(input));
			
			if (move.isValidMove(input)) {
				if (turn % 2 == 0) {
					board = move.makeMove(input, MAX);
					turn++;
					board.setTurn(true);
				} else {
					board = move.makeMove(input, MIN);
					turn++;
					board.setTurn(false);
					//!board.getTurn()
				}
			}
		}
		in.close();
	}

}
