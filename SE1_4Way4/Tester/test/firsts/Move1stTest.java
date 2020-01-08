package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.GameBoard;
import src.model.Move;
import src.model.MoveAllTokens;

class Move1stTest {

	//this may seem weird, but it is indeed intended this way:
	
	/** The token will fall left */
	public static final String RIGHT = "r";
	/** The token will fall right */
	public static final String LEFT = "l";
	/** The token will fall up */
	public static final String DOWN = "d";
	/** The token will fall down */
	public static final String UP = "u";
	
	
	/** The token will fall left */
	public static final int DIRECTION_RIGHT = 0;
	/** The token will fall right */
	public static final int DIRECTION_LEFT = 1;
	/** The token will fall up */
	public static final int DIRECTION_DOWN = 2; 
	/** The token will fall down */
	public static final int DIRECTION_UP = 3;
	
	

	@Test
	void validStringTest() {
		GameBoard testBoard = new GameBoard(7, 7);
	
		Move testMove = new Move(testBoard);
		
		assertFalse(testMove.isValidString("some string"));

		assertFalse(testMove.isValidString(String.valueOf('a' - 1) + "8"));

		assertFalse(testMove.isValidString("h0"));

		assertFalse(testMove.isValidString(String.valueOf('A' - 1) + "8"));

		assertFalse(testMove.isValidString("H0"));

		assertFalse(testMove.isValidString("g8"));

		assertFalse(testMove.isValidString("b0"));
		
		assertFalse(testMove.isValidString("g7" + LEFT));

		assertFalse(testMove.isValidString("a7" + RIGHT));

		//resetting the Move's memory, because if you don't, 
		//it takes the 7 from last turn's Test case 
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

		assertFalse(testMove.isValidString("A1" + RIGHT));

		assertFalse(testMove.isValidString("7A"+RIGHT));

		assertFalse(testMove.isValidString("7g"+LEFT));
		
		assertFalse(testMove.isValidString("G7"+LEFT));

		assertFalse(testMove.isValidString("a7"+DOWN));

		assertFalse(testMove.isValidString("1a"+UP));
		
		
		//test auf string-ende		
		assertFalse(testMove.isValidString("\0" + "\0"));
		
		
		//test auf nicht-ascii-zeichen
		assertFalse(testMove.isValidString("üß"));
		
		assertFalse(testMove.isValidString("šč"));
		
		assertFalse(testMove.isValidString("дб"));
		
		assertFalse(testMove.isValidString("пг"));
		
		
		//valid moves here
		assertTrue(testMove.isValidString("b1"));
		
		assertTrue(testMove.isValidString("7b"));

		assertTrue(testMove.isValidString("g2"));

		assertTrue(testMove.isValidString("2G"));

		assertTrue(testMove.isValidString("6a"));

		assertTrue(testMove.isValidString("A6"));

		assertTrue(testMove.isValidString("f7"));

		assertTrue(testMove.isValidString("1f"));
		
		assertTrue(testMove.isValidString("a1" + DOWN));

		assertTrue(testMove.isValidString("a1" + LEFT));

		assertTrue(testMove.isValidString("g1" + RIGHT));

		assertTrue(testMove.isValidString("g1" + DOWN));

		assertTrue(testMove.isValidString("g7" + UP));

		assertTrue(testMove.isValidString("a7" + UP));
	}
	
	
	@Test
	void validMoveEmptyBoard() {//testing out the possible directions from the borders
		GameBoard testBoard = new GameBoard(7, 7);
		assertTrue(new Move(0, 0, DIRECTION_UP, testBoard).isValidMove());
		assertTrue(new Move(4, 6, DIRECTION_LEFT, testBoard).isValidMove());

		assertTrue(new Move(6, 3, DIRECTION_DOWN, testBoard).isValidMove());
		assertTrue(new Move(1, 0, DIRECTION_RIGHT, testBoard).isValidMove());
		
		//is this intended to work?
		assertTrue(new Move(0, 0, DIRECTION_DOWN, testBoard).isValidMove());
	}
}
