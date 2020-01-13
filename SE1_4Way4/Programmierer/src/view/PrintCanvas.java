package src.view;

import src.model.GameBoard;

public class PrintCanvas {
	/**
	 * Gibt output auf Konsole aus
	 * 
	 * @param output
	 */
	public static void print(String output) {
		System.out.println(output);
	}

	public static void printBoard(GameBoard board) {
		int[][] tIn = board.getBoard();
		for (int reihe = 0; reihe < tIn.length; ++reihe) {
			for (int spalte = 0; spalte < tIn[reihe].length; ++spalte) {
				System.out.print(tIn[reihe][spalte] + "\t");
			}
			System.out.println("\n");

		}
		System.out.println("\n");
	}

	public static void printUserIn() {
		System.out.println(" gib die Koordinaten einer Position ein");
		System.out.println("(z.B.: 'a1d' oder '1ad' oder 'a2' oder '2a')");
		System.out.println("(r = rechts, l = links, u = oben, d = unten)");
	}

	public static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}

	public static void printBoardBorders(GameBoard board) {
		char[][] net = new char[][] {
				{ ' ', '|', 'a', '|', 'b', '|', 'c', '|', 'd', '|', 'e', '|', 'f', '|', 'g', '|', ' ' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '7', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '7' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '6', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '6' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '5', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '5' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '4', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '4' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '3', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '3' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '2', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '2' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ '1', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', ' ', '|', '1' },
				{ '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-', '+', '-' },
				{ ' ', '|', 'a', '|', 'b', '|', 'c', '|', 'd', '|', 'e', '|', 'f', '|', 'g', '|', ' ' } };

		int[][] content = board.getBoard();

		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[0].length; j++) {
				if (content[i][j] == 0) {
					net[(i * 2) + 2][(j * 2) + 2] = ' ';
				} else if (content[i][j] == 1) {
					net[(i * 2) + 2][(j * 2) + 2] = 'X';
				} else if (content[i][j] == -1) {
					net[(i * 2) + 2][(j * 2) + 2] = 'O';
				}

			}
		}
		for (int i = 0; i < net.length; i++) {
			for (int j = 0; j < net[0].length; j++) {
				System.out.print(net[i][j]);
			}
			System.out.print("\n");
		}
	}
}
