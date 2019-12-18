package src.model;

public class SearchRow {
	private GameBoard currentBoard;
	private boolean player1Won = false;	
	private Player player1;

	public SearchRow(GameBoard currentboard, Player player){
		this.setCurrentBoard(currentboard);
		this.setPlayer1(player);
	}

	/**	searches for a line of 4 tokens
	 * 
	 * @return true if 4-in-one-line is found
	 */
	public boolean search() {

		for(int i = 0; i < currentBoard.getRows(); i++) {		//Ränder der Arrays dürfen nicht überschritten werden!
			for(int j = 0; j < currentBoard.getColumns(); j++) {

				//wenn Spieler 1 vier in einer Reihe hat, true setzen ( false = Spieler 2 gewinnt)
				if(player1.getToken() == 'X') {
					if((j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i, j+1) == 'X' && currentBoard.getField(i, j+2) == 'X' && currentBoard.getField(i, j+3) == 'X'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j) == 'X' && currentBoard.getField(i+2,  j) == 'X' && currentBoard.getField(i+3, j) == 'X'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j+1) == 'X' && currentBoard.getField(i+2, j+2) == 'X' && currentBoard.getField(i+3, j+3) == 'X'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j >= 3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j-1) == 'X' && currentBoard.getField(i+2, j-2) == 'X' && currentBoard.getField(i+3, j-3) == 'X'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					//
					if((j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i, j+1) == 'O' && currentBoard.getField(i, j+2) == 'O' && currentBoard.getField(i, j+3) == 'O'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j) == 'O' && currentBoard.getField(i+2,  j) == 'O' && currentBoard.getField(i+3, j) == 'O'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j+1) == 'O' && currentBoard.getField(i+2, j+2) == 'O' && currentBoard.getField(i+3, j+3) == 'O'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j >= 3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j-1) == 'O' && currentBoard.getField(i+2, j-2) == 'O' && currentBoard.getField(i+3, j-3) == 'O'))) {
						
						return true;
					}
				}
				
				if(player1.getToken() == 'O') {
					if((j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i, j+1) == 'O' && currentBoard.getField(i, j+2) == 'O' && currentBoard.getField(i, j+3) == 'O'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j) == 'O' && currentBoard.getField(i+2,  j) == 'O' && currentBoard.getField(i+3, j) == 'O'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j+1) == 'O' && currentBoard.getField(i+2, j+2) == 'O' && currentBoard.getField(i+3, j+3) == 'O'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j >= 3) && ((currentBoard.getField(i,j) == 'O' && currentBoard.getField(i+1, j-1) == 'O' && currentBoard.getField(i+2, j-2) == 'O' && currentBoard.getField(i+3, j-3) == 'O'))) {
						
						this.setPlayer1Won(true);
						return true;
					}
					//
					if((j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i, j+1) == 'X' && currentBoard.getField(i, j+2) == 'X' && currentBoard.getField(i, j+3) == 'X'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j) == 'X' && currentBoard.getField(i+2,  j) == 'X' && currentBoard.getField(i+3, j) == 'X'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j < currentBoard.getColumns()-3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j+1) == 'X' && currentBoard.getField(i+2, j+2) == 'X' && currentBoard.getField(i+3, j+3) == 'X'))) {
						
						return true;
					}
					
					if((i < currentBoard.getRows()-3) && (j >= 3) && ((currentBoard.getField(i,j) == 'X' && currentBoard.getField(i+1, j-1) == 'X' && currentBoard.getField(i+2, j-2) == 'X' && currentBoard.getField(i+3, j-3) == 'X'))) {
						
						return true;
					}
				}
			}
		}
		return false;
	}

	// Getter und Setter:
	
	public GameBoard getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(GameBoard currentBoard) {
		this.currentBoard = currentBoard;
	}

	public Player getPlayer1() {
		return player1;
	}

	private void setPlayer1(Player player) {
		this.player1 = player;
	}

	public boolean isPlayer1Won() {
		return player1Won;
	}

	private void setPlayer1Won(boolean player1Won) {
		this.player1Won = player1Won;
	}
}
