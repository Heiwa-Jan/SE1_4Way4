package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.*;

class MoveAllTokens1stTest {
	
	
//	ACHTUNG - in den kommentaren in MoveAllTokens sind left und right wohl 
//	vertauscht
	public static final int LEFT = 1;  // laut kommentar eigentlich RIGHT
	public static final int RIGHT = 0; // laut kommentar eigentich LEFT
	public static final int UP = 3;
	public static final int DOWN = 2; 

	@Test
	void basicTest() {

		GameBoard testBoard = new GameBoard(7,7);
		Move testMove = new Move(4, 0, LEFT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		String boardState = "    a   b   c   d   e   f   g   \n"
			   	 		  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 7|   |   |   |   |   |   |   |  7\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 6|   |   |   |   |   |   |   |  6\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 5| X |   |   |   |   |   |   |  5\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 4|   |   |   |   |   |   |   |  4\n"
					   	  + "  +---+---+---+---+---+---+---+\n" 
					   	  + " 3|   |   |   |   |   |   |   |  3\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 2|   |   |   |   |   |   |   |  2\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + " 1|   |   |   |   |   |   |   |  1\n"
					   	  + "  +---+---+---+---+---+---+---+\n"
					   	  + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testBoard = new GameBoard(7,7);
		testMove = new Move(0, 2, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'O');
		
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7|   |   | O |   |   |   |   |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6|   |   |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   |   |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   |   |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   |   |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   |   |   |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testBoard = new GameBoard(7,7);
		testMove = new Move(6, 6, RIGHT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7|   |   |   |   |   |   | X |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6|   |   |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   |   |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   |   |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   |   |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   |   |   |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testBoard = new GameBoard(7,7);
		testMove = new Move(6, 5, DOWN, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'O');
		
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7|   |   |   |   |   |   |   |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6|   |   |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   |   |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   |   |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   |   |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   | O |   |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
	}
	
	@Test 
	void testMultiMove(){
		GameBoard testBoard = new GameBoard(7, 7);
		Move testMove = new Move(0, 0, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		testMove = new Move(0, 0, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		String boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7| X |   |   |   |   |   |   |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6| X |   |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   |   |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   |   |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   |   |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   |   |   |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testMove = new Move(5, 6, LEFT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		testMove = new Move(0, 1, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7| X | X |   |   |   |   |   |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6| X | X |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   |   |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   |   |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   |   |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   |   |   |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testMove = new Move(4, 0, RIGHT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');	
		
		testMove = new Move(6, 6, DOWN, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7|   |   |   |   |   |   |   |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6|   |   |   |   |   |   |   |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   |   |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   | X |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   | X |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   | X | X |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   | X | X |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
		testMove = new Move(6, 6, LEFT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		testMove = new Move(0, 4, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		testMove = new Move(0, 0, RIGHT, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
		
		testMove = new Move(0, 6, UP, testBoard);
		testBoard = MoveAllTokens.move(testBoard, testMove, 'X');
				
		boardState = "    a   b   c   d   e   f   g   \n"
	   	   		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 7|   |   |   |   | X | X | X |  7\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 6|   |   |   |   |   | X | X |  6\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 5|   |   |   |   |   |   | X |  5\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 4|   |   |   |   |   |   | X |  4\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n" 
	   	 		   + " 3|   |   |   |   |   |   | X |  3\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 2|   |   |   |   |   |   | X |  2\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + " 1|   |   |   |   |   |   | X |  1\n"
	   	 		   + "  +---+---+---+---+---+---+---+\n"
	   	 		   + "    a   b   c   d   e   f   g   ";
		
		assertEquals(boardState, testBoard.printBoard());
		
	}
	
	@Test
	void MoveAllTokensTest() {
		GameBoard testBoard = new GameBoard(7, 7);
		testBoard.setField(0, 0, 'X');
		testBoard.setField(1, 0, 'O');
		testBoard.setField(2, 0, 'X');
		testBoard.setField(3, 0, 'O');
		testBoard.setField(4, 0, 'X');
		testBoard.setField(5, 0, 'O');
		testBoard.setField(6, 0, 'X');
		
		testBoard.setField(0, 1, 'O');
		testBoard.setField(1, 1, 'X');
		testBoard.setField(2, 1, 'O');
		testBoard.setField(3, 1, 'X');
		testBoard.setField(4, 1, 'O');
		testBoard.setField(5, 1, 'X');
		
		
		//testBoard looks like this now
		String stringBoard =  
		   "    a   b   c   d   e   f   g   \n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 7| X |   |   |   |   |   |   |  7\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 6| O | X |   |   |   |   |   |  6\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 5| X | O |   |   |   |   |   |  5\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 4| O | X |   |   |   |   |   |  4\n"
		 + "  +---+---+---+---+---+---+---+\n" 
		 + " 3| X | O |   |   |   |   |   |  3\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 2| O | X |   |   |   |   |   |  2\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 1| X | O |   |   |   |   |   |  1\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + "    a   b   c   d   e   f   g   ";

		assertEquals(testBoard.printBoard(), stringBoard);
		
		//throwing an 'O'-token from left to right, in order to move all tokens right
		testBoard = MoveAllTokens.move(testBoard, new Move(6, 1, 0, testBoard), 'O');
		
		stringBoard =
		   "    a   b   c   d   e   f   g   \n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 7|   |   |   |   |   | O | X |  7\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 6|   |   |   |   |   | O | X |  6\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 5|   |   |   |   |   | X | O |  5\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 4|   |   |   |   |   | O | X |  4\n"
		 + "  +---+---+---+---+---+---+---+\n" 
		 + " 3|   |   |   |   |   | X | O |  3\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 2|   |   |   |   |   | O | X |  2\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + " 1|   |   |   |   |   | X | O |  1\n"
		 + "  +---+---+---+---+---+---+---+\n"
		 + "    a   b   c   d   e   f   g   ";
		 
		assertEquals(testBoard.printBoard(), stringBoard);
	}	

}
