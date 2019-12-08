package test;
import src.model.*;

public class TryOut {

	public static void main(String[] args) {
		GameBoard g = new GameBoard(7,7);
		Move m = new Move(g);
		System.out.println(m.isValidString("g8"));
	}
	
}
