package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchRow1stTest {

	@Test
	void winnerTest() {
		GameBoard testBoard = new GameBoard();
		testBoard.calculateNewBoard();
		MoveAllTokens m = new MoveAllTokens();
		
//		@ToDo wie konstruiere ich einen Player? was ist mit token X / O
		Player testPlayer = new Player(1);
		
		for(int i = 0; i < 4; i++)
			m.move(testBoard, new Move("1b", testBoard, testPlayer));
		
		assertTrue(new SearchRow(testBoard, testPlayer).search());

		testBoard.calculateNewBoard();
		
		for(int i = 0; i < 4; i++)
			m.move(testBoard, new Move("a4", testBoard, testPlayer));
		
		assertTrue(new SearchRow(testBoard, testPlayer).search());
	}
	
}
