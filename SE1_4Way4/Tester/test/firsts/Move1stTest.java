package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.GameBoard;
import src.model.Move;
import src.model.Player;

class Move1stTest {

	public static final String UP = "u";//ist die richtung, aus der eingeworfen wird
	public static final String DOWN = "d";//nicht die richtung, in die der Token faellt!
	public static final String LEFT = "l";
	public static final String RIGHT = "r";
	
	public static final int DIRECTION_LEFT = 1;  // laut kommentar eigentlich RIGHT
	public static final int DIRECTION_RIGHT = 0; // laut kommentar eigentich LEFT
	public static final int DIRECTION_UP = 3;
	public static final int DIRECTION_DOWN = 2; 


	@Test
	void validStringTest() {
		GameBoard testBoard = new GameBoard(7, 7);
		
		Player testPlayer = new Player("test");

		Move testMove = new Move(testBoard);
		
		assertFalse(testMove.isValidString("some string"));

		assertFalse(testMove.isValidString(String.valueOf('a' - 1) + "8"));

		assertFalse(testMove.isValidString("h0"));

		assertFalse(testMove.isValidString(String.valueOf('A' - 1) + "8"));

		assertFalse(testMove.isValidString("H0"));

		assertFalse(testMove.isValidString("g8"));

		assertFalse(testMove.isValidString("b0"));

		assertTrue(testMove.isValidString("a1" + DOWN));

		assertTrue(testMove.isValidString("a1" + LEFT));

		assertTrue(testMove.isValidString("g1" + RIGHT));

		assertTrue(testMove.isValidString("g1" + DOWN));

		assertTrue(testMove.isValidString("g7" + UP));

		assertFalse(testMove.isValidString("g7" + LEFT));

		assertFalse(testMove.isValidString("a7" + RIGHT));

		assertTrue(testMove.isValidString("a7" + UP));

		//resetting the Move, because if you don't, 
		//then it takes the 7 from last turn's Test 
		//instead of the 0 of the next Move
		testMove = new Move(testBoard);
		
		assertFalse(testMove.isValidString("b0"));

		assertFalse(testMove.isValidString("a1"));

		assertFalse(testMove.isValidString("g7"));

		assertFalse(testMove.isValidString("g7u and some more stuff"));

		assertFalse(testMove.isValidString("a4r"));

		assertFalse(testMove.isValidString("3au"));

		assertFalse(testMove.isValidString("7b7"));

		assertFalse(testMove.isValidString("1a2r"));

		assertTrue(testMove.isValidString("b1"));
		
		assertTrue(testMove.isValidString("7b"));

		assertTrue(testMove.isValidString("g2"));

		assertTrue(testMove.isValidString("2G"));

		assertTrue(testMove.isValidString("6a"));

		assertTrue(testMove.isValidString("A6"));

		assertTrue(testMove.isValidString("f7"));

		assertTrue(testMove.isValidString("1f"));

		assertFalse(testMove.isValidString("A1" + RIGHT));

		assertFalse(testMove.isValidString("7A"+RIGHT));

		assertFalse(testMove.isValidString("7g"+LEFT));
		
		assertFalse(testMove.isValidString("G7"+LEFT));

		assertFalse(testMove.isValidString("a7"+DOWN));

		assertFalse(testMove.isValidString("1a"+UP));
	}
	
	
	@Test
	void validMoveEmptyBoard() {
		GameBoard testBoard = new GameBoard(7, 7);
		assertTrue(new Move(0, 0, DIRECTION_UP, testBoard).isValidMove());
		assertTrue(new Move(4, 6, DIRECTION_LEFT, testBoard).isValidMove());

		assertTrue(new Move(6, 3, DIRECTION_DOWN, testBoard).isValidMove());
		assertTrue(new Move(1, 0, DIRECTION_RIGHT, testBoard).isValidMove());
	}
	
	
//		@ToDo MoveAllTokens
		/*
		GameBoard testBoard = new GameBoard(9, 8);
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
*/
}
