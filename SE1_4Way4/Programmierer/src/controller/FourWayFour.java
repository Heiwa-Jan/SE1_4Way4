
package src.controller;

//TODO: sicherstellen dass player1 gleich definiert ist und 
//ob Einwurfrichtung von anderem Spiel gleich intrpretiert wird
//50 mal jewils beginnen lassen?
public class FourWayFour {

	static int points1 = 0;
	static int points2 = 0;

	public static void main(String[] args) {

		// Diese Zeile fuert das "normale" Spiel aus:
		// GameMain normal = new GameMain(false);

		for (int n = 0; n < 100; n++) {

			// true steht fuer Tourniermodus
			GameMain a = new GameMain(true);
			GameMain b = new GameMain(true);

			wrapper(a, b);

		}

		System.out.println("Spieler A: " + points1);
		System.out.println("Spieler B: " + points2);

	}

	/**
	 * Spielablauf + Gewinnbedingung
	 * 
	 * @param a
	 * @param b
	 */
	public static void wrapper(GameMain a, GameMain b) {

		// A Beginnt
		a.setStart(true);
		b.setStart(false);

		String move = null;
		a.printBoard();

		do {

			// Spieler 1 wirft ein
			System.out.println("Spieler 1 ist am Zug: ");
			move = a.yourMove();

			// Kontrollausgabe
			System.out.println("Move-Str a: " + move);
			b.myMove(move);
			a.printBoard();

			if (a.isRunning() && b.isRunning()) {

				// Spieler 2 wirft ein
				System.out.println("Spieler 2 ist am Zug: ");
				move = b.yourMove();
				// Kontrollausgabe
				System.out.println("Move-Str b: " + move);
				a.myMove(move);
				b.printBoard();
			}

		} while (a.isRunning() && b.isRunning());

		if (!a.isRunning() && !b.isRunning()) {

			// Wer hat gewonnen?
			System.out.println("//////////////////////////////////////////////");
			if (a.whoWon() && b.whoWon()) {
				System.out.println("Spieler 1 gewinnt.");
				points1 += 2;
			} else if (!a.whoWon() && !b.whoWon()) {
				System.out.println("Spieler 2 gewinnt.");
				points2 += 2;
			} else {
				System.out.println("Fehler in WhoWon()");
			}
			System.out.println("//////////////////////////////////////////////");

		} else {
			System.out.println("Fehler in isRunning()");
		}

	}

}
