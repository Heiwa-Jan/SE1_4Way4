package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Move1stTest {

	public static final String UP = "u";
	public static final String DOWN = "d";
	public static final String LEFT = "l";
	public static final String RIGHT = "r";

	@Test
	void validStringTest() {
		GameBoard testBoard = new GameBoard(7, 7);
		testBoard.calculateNewBoard();

		Move testMove = new Move("some string", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move(String.valueOf('a' - 1) + "8", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("h0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move(String.valueOf('A' - 1) + "8", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("H0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g8", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1" + DOWN, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1" + LEFT, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g1" + RIGHT, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g1" + DOWN, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7" + UP, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7" + LEFT, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a7" + RIGHT, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a7" + UP, testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7u and some more stuff", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("a4r", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("3au", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("7b7", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("1a2r", testBoard);
		assertFalse(testMove.isValidString());

		testMove = new Move("b1", testBoard);
		assertTrue(testMove.isValidMove);
		testMove = new Move("7b", testBoard);
		assertTrue(testMove.isValidMove);

		testMove = new Move("g2", testBoard);
		assertTrue(testMove.isValidMove());
		testMove = new Move("2G", testBoard);
		assertTrue(testMove.isValidString());

		testMove = new Move("6a", testBoard);
		assertTrue(testMove.isValidMove());
		testMove = new Move("A6", testBoard);
		assertTrue(testMove.isValidString());

		testMove = new Move("f7", testBoard);
		assertTrue(testMove.isValidMove);
		testMove = new Move("1f", testBoard);
		assertTrue(testMove.isValidMove);

		testMove = new Move("A1r", testBoard);
		assertTrue(testMove.isValidMove);
		testMove = new Move("7Ar", testBoard);
		assertTrue(testMove.isValidMove);

		testMove = new Move("7gl", testBoard);
		assertTrue(testMove.isValidString());
		testMove = new Move("G7l", testBoard);
		assertTrue(testMove.isValidStirng());

		testMove = new Move("a7d", testBoard);
		assertTrue(testMove.isValidMove);

		testMove = new Move("1au", testBoard);
		assertTrue(testMove.isValidMove);
	}
	
	
	@Test
	void validMoveTest() {
		GameBoard testBoard = new GameBoard(9, 8);
		testBoard.calculateNewBoard();
		MoveAllTokens mover = new MoveAllTokens();
		
		for(int i = 0; i < 9; i++) {
			mover.move(testBoard, new Move("a1u"));
		}
		for(int i = 0; i < 8; i++)
			mover.move(testBoard, new Move("a2"));
		
		
		String testField =  "   a  b  c  d  e  f  g  h   \n"
				 		  + " +------------------------+ \n"
				 		  + "9| X  O                   |9\n"
				 		  + "8| O  X                   |8\n"
				 		  + "7| X  X                   |7\n"
				 		  + "6| O  O                   |6\n"
				 		  + "5| X  X                   |5\n"
				 		  + "4| O  X                   |4\n"
				 		  + "3| X  O                   |3\n"
				 		  + "2| O  X                   |2\n"
				 		  + "1| X                      |1\n"
				 		  + " +------------------------+ \n"
				 		  + "   a  b  c  d  e  f  g  h   \n";
		
		assertEquals(testField, testBoard.toString());
		
		Move testMove = new Move("a1u", testBoard);
		assertFalse(testMove.isValidMove());
		
		testMove = new Move("a1d", testBoard);
		assertFalse(testMove.isValidMove());
		
		testMove = new Move("b9", testBoard);
		assertTrue(testMove.isValidMove());
		
		testMove = new Move("a5", testBoard);
		assertTrue(testMove.isValidMove());

	}

}
