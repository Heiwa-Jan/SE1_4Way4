package test.firsts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.GameBoard;
import src.model.Player;
import src.model.KI;

class KI1stTest {

	
//	In dieser Situatuion kam es zur ArrayIndexOutOfBoundsException bei Christinas KI
	@Test
	void test() {
		GameBoard testBoard = new GameBoard(7, 7);
		Player testPlayer = new Player("X", false);
		testBoard.setField(3, 0, 'X');
		testBoard.setField(2, 0, 'X');
		testBoard.setField(1, 0, 'O');
		testBoard.setField(0, 0, 'O');
		testBoard.setField(0, 1, 'X');
		testBoard.setField(0, 2, 'X');
		testBoard.setField(0, 3, 'O');
		KI hard = new KI(testBoard, 'O', false, 3);
	}

}
