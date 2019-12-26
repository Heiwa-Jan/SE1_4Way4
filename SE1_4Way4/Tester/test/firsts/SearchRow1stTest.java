package test.firsts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.model.GameBoard;
import src.model.Move;
import src.model.MoveAllTokens;
import src.model.Player;
import src.model.SearchRow;


class SearchRow1stTest {
		
	@Test
	void winnerTest() {
		GameBoard testBoard = new GameBoard(7, 7);
		Move newMove = new Move(0, 0, 3, testBoard);//a1u (von oben einwerfen)
		testBoard = MoveAllTokens.move(testBoard, newMove, 'X');

		Player testPlayer = new Player("Player_1", false);
		testPlayer.setToken('X');

		for(int i = 0; i < 4; i++) 
			testBoard = MoveAllTokens.move(testBoard, new Move(1, 2, 3, testBoard), 'X');//1bu

		//Test auf vertikal
		assertTrue(new SearchRow(testBoard, testPlayer).search());

		testBoard = new GameBoard(7, 7);
		for(int i = 0; i < 4; i++) 
			testBoard = MoveAllTokens.move(testBoard, new Move(2, 1, 0, testBoard), 'X');//2al

		//Test auf horizontal
		assertTrue(new SearchRow(testBoard, testPlayer).search());
		
		testBoard = new GameBoard(7, 7);
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 0, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 0, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 0, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 0, 3, testBoard), 'X');

		testBoard = MoveAllTokens.move(testBoard, new Move(0, 1, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 1, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 1, 3, testBoard), 'X');

		testBoard = MoveAllTokens.move(testBoard, new Move(0, 2, 3, testBoard), 'O');
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 2, 3, testBoard), 'X');
		
		testBoard = MoveAllTokens.move(testBoard, new Move(0, 3, 3, testBoard), 'X');
		
		assertTrue(new SearchRow(testBoard, testPlayer).search());
	}
	
}
