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
		
		Player testPlayer = new Player(1);

		Move testMove = new Move("some string", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move(String.valueOf('a' - 1) + "8", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("h0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move(String.valueOf('A' - 1) + "8", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("H0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g8", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1" + DOWN, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1" + LEFT, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g1" + RIGHT, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g1" + DOWN, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7" + UP, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7" + LEFT, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a7" + RIGHT, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a7" + UP, testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("b0", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a1", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("g7u and some more stuff", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("a4r", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("3au", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("7b7", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("1a2r", testBoard, testPlayer);
		assertFalse(testMove.isValidString());

		testMove = new Move("b1", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
		testMove = new Move("7b", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());

		testMove = new Move("g2", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
		testMove = new Move("2G", testBoard, testPlayer);
		assertTrue(testMove.isValidString());

		testMove = new Move("6a", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
		testMove = new Move("A6", testBoard, testPlayer);
		assertTrue(testMove.isValidString());

		testMove = new Move("f7", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
		testMove = new Move("1f", testBoard, testPlayer);
		assertTrue(testMove.isValidsString());

		testMove = new Move("A1"+RIGHT, testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
		testMove = new Move("7A"+RIGHT, testBoard, testPlayer);
		assertTrue(testMove.isValidsString());

		testMove = new Move("7g"+LEFT, testBoard, testPlayer);
		assertTrue(testMove.isValidString());
		testMove = new Move("G7"+LEFT, testBoard, testPlayer);
		assertTrue(testMove.isValidStirng());

		testMove = new Move("a7"+DOWN, testBoard, testPlayer);
		assertTrue(testMove.isValidsString());

		testMove = new Move("1a"+UP, testBoard, testPlayer);
		assertTrue(testMove.isValidsString());
	}
	
	
	@Test
	void validMoveTest() {
//		@ToDo MoveAllTokens
		
		GameBoard testBoard = new GameBoard(9, 8);
		testBoard.calculateNewBoard();
		MoveAllTokens mover = new MoveAllTokens();
		Player testPlayer = new Player(1);
		
		
		for(int i = 0; i < 9; i++) {
			mover.move(testBoard, new Move("a1"+UP, testBoard, testPlayer));
		}
		for(int i = 0; i < 8; i++)
			mover.move(testBoard, new Move("a2", testBoard, testPlayer));
		
		
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
		
		Move testMove = new Move("a1"+UP, testBoard, testPlayer);
		assertFalse(testMove.isValidMove());
		
		testMove = new Move("a1"+DOWN, testBoard, testPlayer);
		assertFalse(testMove.isValidMove());
		
		testMove = new Move("b9", testBoard, testPlayer);
		assertTrue(testMove.isValidMove());
		
		testMove = new Move("a5", testBoard, testPlayer);
		assertTrue(testMove.isValidMove());

		testBoard.calculateNewGameBoard();
		
		for(int i = 0; i < 9; i++) 
			mover.move(testBoard, new Move("h1"+UP, testBoard, testPlayer));	
		mover.move(testBoard, new Move("a1"+RIGHT, testBoard, testPlayer));
		mover.move(testBoard, new Move("a9"+RIGHT, testBoard, testPlayer));
		
		for(int i = 2; i < 9; i++)
			mover.move(testBoard, new Move("a"+i, testBoard, testPlayer));
		
		testField = "   a  b  c  d  e  f  g  h   \n"
		 		  + " +------------------------+ \n"
		 		  + "9| X  O                   |9\n"
		 		  + "8| O  X                   |8\n"
		 		  + "7| X  X                   |7\n"
		 		  + "6| O  O                   |6\n"
		 		  + "5| X                      |5\n"
		 		  + "4| O  X                   |4\n"
		 		  + "3| X  O                   |3\n"
		 		  + "2| O  X                   |2\n"
		 		  + "1| X  O                   |1\n"
		 		  + " +------------------------+ \n"
		 		  + "   a  b  c  d  e  f  g  h   \n";
		
		testMove = new Move("b9", testField);
		assertTrue(testMove.isValidMove());
	
		testField = "   a  b  c  d  e  f  g  h   \n"
		 		  + " +------------------------+ \n"
		 		  + "9| X  O                   |9\n"
		 		  + "8| O  X                   |8\n"
		 		  + "7| X  X  O  X     O  X  X |7\n"
		 		  + "6| O  O                   |6\n"
		 		  + "5| X  O                   |5\n"
		 		  + "4| O  X                   |4\n"
		 		  + "3| X  O                   |3\n"
		 		  + "2| O  X                   |2\n"
		 		  + "1| X  O                   |1\n"
		 		  + " +------------------------+ \n"
		 		  + "   a  b  c  d  e  f  g  h   \n";
		
		testMove = new Move("7h", testField);
	}

}
