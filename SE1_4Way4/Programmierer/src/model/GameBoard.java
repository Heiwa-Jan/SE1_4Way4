package src.model;

public class GameBoard {
	private char[][] board;  // = fields
	int rows;  // = height
	int columns;  // = width
	/**
	 * initialize a new, empty game board of size [rows] x [columns]
	 * @param rows
	 * @param columns
	 */
	public GameBoard(int rows, int columns){
		this.setRows(rows);
		this.setColumns(columns);
		
		this.board = new char[rows][columns];
		// jedes Feld auf dem Spielfeld ist anfangs leer (" ")
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				this.setField(i, j, ' ');
			}
		}	
	}
	/**
	 * 
	 * @return string for output on screen
	 */
	public String printBoard() {
		
		StringBuilder buildBoard = new StringBuilder();
		
		// Einrückung abhängig von Seitenrändern und Koordinaten (muss manuell angepasst werden!)
		// Koordinaten -> oberer Rand (a-j)
		// -> an Feldgröße automatisch angepasst
		buildBoard.append("    ");
		for(int i = 1; i <= this.getColumns(); i++) {
			buildBoard.append(Constants.coordinatesColumns.charAt(i-1) + "   ");
		}
		
		// oberer Spielfeldrand (  +---+---+ )
		// -> an Feldgröße automatisch angepasst 
		buildBoard.append("\n  +");
		for(int i = 0; i < (this.getColumns() ); i++) {
			buildBoard.append("---+");
		}
		buildBoard.append("\n");
		
		// seitliche Koordinaten und Spielfedbegrenzung (links/rechts) je Zeile,
		// sowie alle Felder des Spielfelds (( 1| X | O | X | O | 1 )* Zeilenzahl)
		for(int i = 0; i < this.getRows(); i++) {
			if(this.getRows()-i == 10) {
				buildBoard.append(Integer.toString(this.getRows()-i) + "| ");
			}
			else {
				buildBoard.append(" " + Integer.toString(this.getRows()-i) + "| ");
			}
			
			for(int j = 0; j < this.getColumns(); j++) {
				buildBoard.append(board[this.getRows()-1-i][j] + " | ");
			}
			if(this.getRows()-i == 10) {
				buildBoard.append("" + Integer.toString(this.getRows()-i) +"\n");
			}
			else {
				buildBoard.append(" " + Integer.toString(this.getRows()-i) +"\n");
			}
			
			// untere Zeilenbegrenzung ( +---+---+ )
			// -> an Feldgröße automatisch angepasst
			buildBoard.append("  +");
			for(int j = 0; j < (this.getColumns() ); j++) {
				buildBoard.append("---+");
			}
			buildBoard.append("\n");
		}
		
		// Koordinaten -> unterer Rand (a-j)
		// -> an Feldgröße automatisch angepasst
		buildBoard.append("    ");
		for(int i = 1; i <= this.getColumns(); i++) {
			buildBoard.append(Constants.coordinatesColumns.charAt(i-1) + "   ");
		}
		
		return buildBoard.toString();
		
	}
	/**
	 * Kopiert die aktuelle SpielSituation und gibt diese zurück
	 * 
	 * @return spielKopie
	 */
	
	public GameBoard copy() {
		GameBoard copy = new GameBoard(this.getRows(), this.getColumns());
		for (int x = 0; x < copy.getColumns(); x++) {
			for (int y = 0; y < copy.getRows(); y++) {
				copy.setField(y, x, this.getField(y,x));
			}
		}
		return copy;
	}

	
	
	// Getter und Setter:
	public int getRows() {
		return rows;
	}
	private void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	private void setColumns(int columns) {
		this.columns = columns;
	}
	public void setField(int row, int column, char token) {
		this.board[row][column] = token;
	}
	public char getField(int row, int column) {
		return board[row][column];
	}
}

//TODO einheitlich toString() statt printBoard()