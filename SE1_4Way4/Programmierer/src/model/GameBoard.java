package src.model;

public class GameBoard {
	// Das Spielfeld als 2D int Array
	private int[][] board;
	private boolean turn;
	

	// Höhe
	int rows;
	// Breite
	int columns;
	// Einträge für das Feld
	private static final int LEER = 0;
	private static final int MAX = 1;
	private static final int MIN = -1;

	/**
	 * Initialisiert ein neues, mit LEER bewertetes GameBoard der Größe
	 * [rows]x[columns]
	 * 
	 * @param rows
	 * @param columns
	 */
	public GameBoard(int rows, int columns) {
		this.setRows(rows);
		this.setColumns(columns);
		this.board = new int[rows][columns];
		// Jedes Feld auf dem Spielfeld ist zu Beginn LEER
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.board[i][j] = LEER;
			}
		}
	}
	
	public GameBoard(int[][] array) {
		this.setBoard(array);
	}
	
	public GameBoard copy() {
		int[][] temp = new int[7][7];
	//	System.out.println("copy rows: " + temp.length);
	//	System.out.println("copy columns: " + temp[0].length);
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				temp[i][j] = this.board[i][j];
			}
		}
		
		GameBoard result = new GameBoard(temp);
		result.setTurn(this.turn);
		return result;
	}

	// printBoard Methode fehlt noch

	public int[][] rotateBoard() {
		int[][] neuesArray = new int[7][7];

		for (int i = 0; i < neuesArray.length; i++) {
			for (int j = 0; j < neuesArray[0].length; j++) {
				neuesArray[i][j] = this.board[j][this.board[j].length - i - 1];
			}
		}

		return neuesArray;
	}

	// Getter/Setter
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = new int[board.length][board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public int getValue(int row, int column) {
		return this.board[row][column];
	}
	
	public void setValue(int row, int column, int value) {
		this.board[row][column] = value;
	}
	public boolean getTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
}
