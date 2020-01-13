package src.model;

public class MoveAllTokens {
	private static final int LEER = 0;

	public static int[][] moveAllDown(int[][] tIn) {
		int[][] tNew = new int[tIn.length][tIn[0].length];

		for (int y = 0; y < tIn.length; y++) {
			int hitCount = 0;
			for (int x = tIn.length - 1; x >= 0; x--) {
				if (tIn[x][y] != LEER) {
					tNew[(tIn.length - 1) - hitCount][y] = tIn[x][y];
					hitCount++;
				}
			}
		}
		return tNew;
	}

	public static int[][] moveAllUp(int[][] tIn) {
		int[][] tNew = new int[tIn.length][tIn[0].length];

		for (int y = 0; y < tIn[0].length; y++) {
			int hitCount = 0;
			for (int x = 0; x < tIn.length; x++) {
				if (tIn[x][y] != LEER) {
					tNew[hitCount][y] = tIn[x][y];
					hitCount++;
				}
			}
		}
		return tNew;
	}

	public static int[][] moveAllRight(int[][] tIn) {
		int[][] tNew = new int[tIn.length][tIn[0].length];

		for (int x = 0; x < tIn.length; x++) {
			int hitCount = 0;
			for (int y = tIn[0].length - 1; y >= 0; y--) {
				if (tIn[x][y] != LEER) {
					tNew[x][(tIn[0].length - 1) - hitCount] = tIn[x][y];
					hitCount++;
				}
			}
		}
		return tNew;
	}

	public static int[][] moveAllLeft(int[][] tIn) {
		int[][] tNew = new int[tIn.length][tIn[0].length];

		for (int x = 0; x < tIn.length; x++) {
			int hitCount = 0;
			for (int y = 0; y < tIn[0].length; y++) {
				if (tIn[x][y] != LEER) {
					tNew[x][hitCount] = tIn[x][y];
					hitCount++;
				}
			}
		}
		return tNew;
	}
}
