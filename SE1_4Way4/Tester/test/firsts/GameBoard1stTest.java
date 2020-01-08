package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.GameBoard;

class GameBoard1stTest {

	@Test
	void DimensionsTest() {
		String emptySevenSquareBoard = "    a   b   c   d   e   f   g   \n"
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
								   	 + " 1|   |   |   |   |   |   |   |  1\n"
								   	 + "  +---+---+---+---+---+---+---+\n"
								   	 + "    a   b   c   d   e   f   g   ";
		
		String emptyRectSquareBoard  = "    a   b   c   d   e   f   g   h   \n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + "10|   |   |   |   |   |   |   |   | 10\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 9|   |   |   |   |   |   |   |   |  9\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 8|   |   |   |   |   |   |   |   |  8\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 7|   |   |   |   |   |   |   |   |  7\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 6|   |   |   |   |   |   |   |   |  6\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 5|   |   |   |   |   |   |   |   |  5\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 4|   |   |   |   |   |   |   |   |  4\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n" 
								   	 + " 3|   |   |   |   |   |   |   |   |  3\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 2|   |   |   |   |   |   |   |   |  2\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + " 1|   |   |   |   |   |   |   |   |  1\n"
								   	 + "  +---+---+---+---+---+---+---+---+\n"
								   	 + "    a   b   c   d   e   f   g   h   ";

		GameBoard testBoard = new GameBoard(7, 7);
		
		assertEquals(7, testBoard.getRows());
		assertEquals(7, testBoard.getColumns());
		
		assertEquals(emptySevenSquareBoard, testBoard.printBoard());
		assertNotEquals(emptyRectSquareBoard, testBoard.printBoard());
		
		testBoard = new GameBoard(10, 8);

		assertEquals(10, testBoard.getRows());
		assertEquals(8, testBoard.getColumns());
		
		assertEquals(emptyRectSquareBoard, testBoard.printBoard());
		assertNotEquals(emptySevenSquareBoard, testBoard.printBoard());
	}
	
	@Test 
	void getSetFields1stTest() {
		GameBoard testBoard = new GameBoard(10, 8);
		
		assertEquals(' ', testBoard.getField(9, 0));
		testBoard.setField(9, 0, 'X');
		assertEquals('X', testBoard.getField(9, 0));
		
		testBoard.setField(0, 7, 'O');
		assertEquals('O', testBoard.getField(0, 7));	
		
		testBoard.setField(4, 5, 'X');
		assertEquals('X', testBoard.getField(4, 5));
		
		String output =   "    a   b   c   d   e   f   g   h   \n"
						+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ "10| X |   |   |   |   |   |   |   | 10\n"
					    + "  +---+---+---+---+---+---+---+---+\n"
					    + " 9|   |   |   |   |   |   |   |   |  9\n"
					  	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 8|   |   |   |   |   |   |   |   |  8\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 7|   |   |   |   |   |   |   |   |  7\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 6|   |   |   |   |   |   |   |   |  6\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 5|   |   |   |   |   | X |   |   |  5\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 4|   |   |   |   |   |   |   |   |  4\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n" 
					   	+ " 3|   |   |   |   |   |   |   |   |  3\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 2|   |   |   |   |   |   |   |   |  2\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ " 1|   |   |   |   |   |   |   | O |  1\n"
					   	+ "  +---+---+---+---+---+---+---+---+\n"
					   	+ "    a   b   c   d   e   f   g   h   ";
		
		assertEquals(output, testBoard.printBoard());
		
		testBoard.setField(8, 0, 'X');//changing the board, so its not equal anymore 
		assertNotEquals(output, testBoard.printBoard());//negative testing
	}

}
