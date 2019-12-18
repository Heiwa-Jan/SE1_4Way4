package src.model;

//import java.lang.Math;
import java.util.Random;

import src.controller.GameMain;
import src.model.Menu;
import src.model.GameBoard;
import src.model.Constants;

public class KI {
	private GameBoard board;

	public String kiMove() {
		Random rand = new Random();
		
		int randRow = 0;
		int randCol = 0;
		int randDir = rand.nextInt(4); // 0=l, 1=r, 2=u, 3=d
		
		if(randDir == 0) { // left
			randRow = rand.nextInt(this.getBoard().getRows());
			randCol = 0;
		}
		else if(randDir == 1) { // right
			randRow = rand.nextInt(this.getBoard().getRows());
			randCol = this.getBoard().getColumns()-1;
		}
		else if(randDir == 2) { // up
			randCol = rand.nextInt(this.getBoard().getColumns())+1;
			randRow = this.getBoard().getRows()-1;
		}
		else if(randDir == 3) { // down
			randCol = rand.nextInt(this.getBoard().getColumns()+1);
			randRow = 0;
		}
		
		String kiMove = "" + Constants.coordinatesColumns.charAt(randCol) + Constants.coordinatesRows.charAt(randRow);
		
		if((randRow == 0 && randCol == 0) || (randRow == 0 && randCol == this.getBoard().getColumns()-1) || (randRow == this.getBoard().getRows()-1 && randCol == 0) || randRow == this.getBoard().getRows()-1 && randCol == this.getBoard().getColumns()-1) {
			kiMove = kiMove + Constants.directions.charAt(randDir);
		}
		
		return kiMove;
}
//-----------------------------------------------------------------------------------------
//		int randomColumns = (int) (Math.random() * this.getBoard().getColumns());
//		int randomRows = (int) (Math.random() * this.getBoard().getRows());
//
//		String kiMove = "" + Constants.coordinatesColumns.charAt(randomColumns)
//				+ Constants.coordinatesRows.charAt(randomRows);
//
//		return kiMove;
//	}

	public KI(GameBoard board){
		this.setBoard(board);
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	private void setBoard(GameBoard board) {
		this.board = board;
	}
}
