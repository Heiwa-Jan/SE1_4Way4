package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoard1stTest {

	@Test
	void DimensionsTest() {
		String emptySevenSquareBoard = "   a  b  c  d  e  f  g   \n"
								   	 + " +---------------------+ \n"
								   	 + "7|                     |7\n"
								   	 + "6|                     |6\n"
								   	 + "5|                     |5\n"
								   	 + "4|                     |4\n"
								   	 + "3|                     |3\n"
								   	 + "2|                     |2\n"
								   	 + "1|                     |1\n"
								   	 + " +---------------------+ \n"
								   	 + "   a  b  c  d  e  f  g   \n";
		
		String emptyRectSquareBoard =  "   a  b  c  d  e  f  g  h   \n"
									 + " +------------------------+ \n"
									 + "9|                        |9\n"
									 + "8|                        |8\n"
									 + "7|                        |7\n"
									 + "6|                        |6\n"
									 + "5|                        |5\n"
									 + "4|                        |4\n"
									 + "3|                        |3\n"
									 + "2|                        |2\n"
									 + "1|                        |1\n"
									 + " +------------------------+ \n"
									 + "   a  b  c  d  e  f  g  h   \n";
		
		GameBoard testBoard = new GameBoard(7, 7);
		testBoard.calculateNewBoard();
		testBoard.calculatenewGameBoard();
		assertEquals(emptySevenSquareBoard, testBoard.toString());
		
		testBoard = new GameBoard(9, 8);
		testBoard.calculatenewGameBoard();
		asssertEquals(emptyRectSquareBoard, testBoard.toString());
	}

}
