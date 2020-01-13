package src.model;

import src.model.MoveAllTokens;

public class Move {
	private static final int NUMBER = 48;
	private static final int LETTER = 97;
	private static final int LEER = 0;

	private GameBoard board;
	private String move = null;
	private int[][] content;

	/**
	 * Initialisiert einen Move auf gegebenem board mit gegebener Richtung
	 * 
	 * @param board
	 * @param move
	 */
	public Move(GameBoard board) {
		this.setBoard(board);

		setContent(board.getBoard());
	}

	public GameBoard makeMove(String ein, int token) {
		int x = 0;
		int y = 0;
		int[][] t = content;

		// Bei Eingabe mit Richtung
		if (ein.length() == 3) {
			int sum = ein.charAt(0) + ein.charAt(1) + ein.charAt(2);
			int[] temp = calculate(ein);
			x = temp[0];
			y = temp[1];
			// System.out.println("Summe: " + sum + " X: " + x + " Y: " + y);
			if (sum == 246 || sum == 252) {
				t = MoveAllTokens.moveAllUp(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllUp(t);
			} else if (sum == 254 || sum == 260) {
				t = MoveAllTokens.moveAllRight(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllRight(t);
			} else if (sum == 266 || sum == 272) {
				t = MoveAllTokens.moveAllLeft(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllLeft(t);
			} else if (sum == 275 || sum == 269) {
				t = MoveAllTokens.moveAllDown(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllDown(t);
			} else {

				System.out.println("Falsche Eingabe! ERROR move 3");
			}
			// Bei Eingabe ohne Richtung
		} else if (ein.length() == 2) {
			int[] temp = calculate(ein);
			x = temp[0];
			y = temp[1];
			switch (x) {
			case 0:
				t = MoveAllTokens.moveAllDown(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllDown(t);
				break;
			case 6:
				t = MoveAllTokens.moveAllUp(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllUp(t);
				break;
			}
			switch (y) {
			case 0:
				t = MoveAllTokens.moveAllRight(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllRight(t);
				break;
			case 6:
				t = MoveAllTokens.moveAllLeft(t);
				t[x][y] = token;
				t = MoveAllTokens.moveAllLeft(t);
			}
		} else {
			System.out.println("Falsche Eingabe! ERROR move 2");
		}
		content = t;
		board.setBoard(content);
		GameBoard result = board.copy();
		return result;
	}

	public int[] calculate(String input) {
		int[] result = new int[2];

		for (int i = 0; i < 2; i++) {
			// Ist der Character eine Zahl von 1 bis 9
			if (input.charAt(i) < 56 && input.charAt(i) > 47) {
				result[0] = content.length - (input.charAt(i) - NUMBER);
			}
			// Ist der Character ein Buchstabe von a bis g
			else if (input.charAt(i) < 104 && input.charAt(i) > 96) {
				result[1] = input.charAt(i) - LETTER;
			} else {
				System.out.println("Falsche Eingabe! ERROR cal");
			}
		}

		return result;
	}

	public boolean isValidMove(String input) {
		boolean validMove = false;

		if (input.length() > 1 && input.length() < 4) {
			if (((input.charAt(0) > 47 && input.charAt(0) <= (48 + content.length))
					&& (input.charAt(1) > 96 && input.charAt(1) <= (97 + content[0].length)))
					|| ((input.charAt(1) > 47 && input.charAt(1) <= (48 + content.length))
							&& (input.charAt(0) > 96 && input.charAt(0) <= (97 + content[0].length)))) {
				int[] coord = calculate(input);
				int x = coord[0];
				int y = coord[1];
				if (input.length() == 2) {

					// X befindet sich am Rand(oben/unten) somit y beliebig
					if (x == 0 || x == 6) {
						if (y >= 0 && y <= 6) {
							for (int i = 0; i < content.length; i++) {
								if (content[i][y] == LEER) {
									validMove = true;
									return validMove;
								}
							}
						} else {
							return validMove;
						}
						// X befindet sich im mittlerem Bereich(1-5) somit ist y am Rand(links/rechts)
					} else if (x > 0 && x < 6) {
						if (y == 0 || y == 6) {
							for (int i = 0; i < content.length; i++) {
								if (content[x][i] == LEER) {
									validMove = true;
									return validMove;
								}
							}
						} else {
							return validMove;
						}
						// Y befindet sich am Rand(links/rechts) somit x beliebig
					} else if (y == 0 || y == 6) {
						if (x >= 0 && x <= 6) {
							for (int i = 0; i < content.length; i++) {
								if (content[x][i] == LEER) {
									validMove = true;
									return validMove;
								}
							}
						} else {
							return validMove;
						}
						// Y befindet sich im mittlerem Bereich(1-5) somit x am Rand(oben/unten)
					} else if (y > 0 && y < 6) {
						if (x == 0 || x == 6) {
							for (int i = 0; i < content.length; i++) {
								if (content[i][y] == LEER) {
									validMove = true;
									return validMove;
								}
							}
						} else {
							return validMove;
						}
					}
				} else if (input.length() == 3) {
					int sum = input.charAt(0) + input.charAt(1) + input.charAt(2);
					if ((sum == 246 || sum == 252) && input.charAt(2) == 'd') {
						for (int i = 0; i < content.length; i++) {
							if (content[i][y] == LEER) {
								validMove = true;
								return validMove;
							}
						}
					} else if ((sum == 254 || sum == 260) && input.charAt(2) == 'l') {
						for (int i = 0; i < content[0].length; i++) {
							if (content[x][i] == LEER) {
								validMove = true;
								return validMove;
							}
						}
					} else if ((sum == 266 || sum == 272) && input.charAt(2) == 'r') {
						for (int i = 0; i < content[0].length; i++) {
							if (content[x][i] == LEER) {
								validMove = true;
								return validMove;
							}
						}
					} else if ((sum == 275 || sum == 269) && input.charAt(2) == 'u') {
						for (int i = 0; i < content.length; i++) {
							if (content[i][y] == LEER) {
								validMove = true;
								return validMove;
							}
						}
					} else {
						return validMove;
					}
				}

			}
		}
		return validMove;
	}

	// Getter/Setter
	public GameBoard getBoard() {
		return board;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public int[][] getContent() {
		return content;
	}

	public void setContent(int[][] content) {
		this.content = content;
	}
}
