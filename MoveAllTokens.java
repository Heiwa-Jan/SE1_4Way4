package src.model;

public class MoveAllTokens {

	/**
	 * moves all tokens on the board in the direction of [currentMove]
	 * @param currentBoard
	 * @param currentMove
	 * @param token
	 * @return Board with all tokens moved
	 */
	public static GameBoard moveAll(GameBoard currentBoard, Move currentMove) {
		GameBoard newBoard = new GameBoard(currentBoard.getRows(), currentBoard.getColumns());

		if(currentMove.isValidMove()) {
			if(currentMove.getDirection() == 0) { // == left

				for(int i = 0; i < currentBoard.getRows(); i++) { //jede Reihe wird zuerst vollständig durchschritten
					int emptyField = currentBoard.getColumns()-1;

					for(int j = currentBoard.getColumns()-1; j >= 0; j--) { //die Spalten werden von der gegenüberliegenden Seite aus durchschritten
						if(currentBoard.getField(i, j) != ' ') { //wenn das Feld besetzt ist wird der Wert auf das nächste leere Feld von rechts gesetzt
							newBoard.setField(i, emptyField, currentBoard.getField(i,j));
							emptyField--; //die Position des nächsten, leeren Feldes wird aktualisiert
						}
					}
					//					if(i == currentMove.getLine()) {  //Spielstein wird eingeworfen
					//						newBoard.setField(i, emptyField, token);
					//					}
				}

			}

			else if(currentMove.getDirection() == 1) { // == right

				for(int i = 0; i < currentBoard.getRows(); i++) { //jede Reihe wird zuerst vollständig durchschritten
					int emptyField = 0;

					for(int j = 0; j < currentBoard.getColumns(); j++) { //die Spalten werden von der gegenüberliegenden Seite aus durchschritten
						if(currentBoard.getField(i, j) != ' ') { //wenn das Feld besetzt ist wird der Wert auf das nächste leere Feld von links gesetzt
							newBoard.setField(i, emptyField, currentBoard.getField(i,j));
							emptyField++; //die Position des nächsten, leeren Feldes wird aktualisiert
						}
					}
					//					if(i == currentMove.getLine()) {  //Spielstein wird eingeworfen
					//						newBoard.setField(i, emptyField, token);
					//					}
				}
			}

			else if(currentMove.getDirection() == 3) { // == up	//!!!!

				for(int i = 0; i < currentBoard.getColumns(); i++) { //jede Reihe wird zuerst vollständig durchschritten
					int emptyField = currentBoard.getRows()-1;

					for(int j = currentBoard.getRows()-1; j >= 0; j--) { //die Spalten werden von der gegenüberliegenden Seite aus durchschritten
						if(currentBoard.getField(j, i) != ' ') { //wenn das Feld besetzt ist wird der Wert auf das nächste leere Feld von rechts gesetzt
							newBoard.setField(emptyField, i, currentBoard.getField(j,i));
							emptyField--; //die Position des nächsten, leeren Feldes wird aktualisiert
						}
					}
					//					if(i == currentMove.getColumn()) {  //Spielstein wird eingeworfen
					//						newBoard.setField(emptyField, i, token);
					//					}
				}
			}
			else{ // == down

				for(int i = 0; i < currentBoard.getColumns(); i++) { //jede Reihe wird zuerst vollständig durchschritten
					int emptyField = 0;

					for(int j = 0; j < currentBoard.getRows(); j++) { //die Spalten werden von der gegenüberliegenden Seite aus durchschritten
						if(currentBoard.getField(j, i) != ' ') { //wenn das Feld besetzt ist wird der Wert auf das nächste leere Feld von rechts gesetzt
							newBoard.setField(emptyField, i, currentBoard.getField(j,i));
							emptyField++; //die Position des nächsten, leeren Feldes wird aktualisiert
						}
					}
					//					if(i == currentMove.getColumn()) {  //Spielstein wird eingeworfen
					//						newBoard.setField(emptyField, i, token);
					//					}
				}
			}
		}
		else {
			/*
			 * Fehlerbehandlung?
			 */
		}

		return newBoard;
	}

	public static GameBoard moveLast(GameBoard currentBoard, Move currentMove, char token) {
		int emptyField = 0;


		if(currentMove.getDirection() == 0) {
			emptyField = currentBoard.getColumns();

			for(int i = currentBoard.getColumns()-1; i >= 0; i--) {
				if(currentBoard.getField(currentMove.getLine(), i) != ' ') {
					emptyField--;
				}
				else {
					emptyField--;
					break;
				}
			}
			currentBoard.setField(currentMove.getLine(), emptyField, token);
		}
		else if(currentMove.getDirection() == 1) {
			emptyField = -1;

			for(int i = 0; i < currentBoard.getColumns(); i++) {
				if(currentBoard.getField(currentMove.getLine(), i) != ' ') {
					emptyField++;
				}
				else {
					emptyField++;
					break;
				}
			}
			currentBoard.setField(currentMove.getLine(), emptyField, token);
		}
		else if(currentMove.getDirection() == 3) {
			emptyField = currentBoard.getRows();

			for(int i = currentBoard.getRows()-1; i >= 0; i--) {
				if(currentBoard.getField(i, currentMove.getColumn()) != ' ') {
					emptyField--;
				}
				else {
					emptyField--;
					break;
				}
			}

			currentBoard.setField(emptyField, currentMove.getColumn(), token);
		}
		else {
			emptyField = -1;

			for(int i = 0; i < currentBoard.getRows(); i++) {
				if(currentBoard.getField(i, currentMove.getColumn()) != ' ') {
					emptyField++;
				}
				else {
					emptyField++;
					break;
				}
			}

			currentBoard.setField(emptyField, currentMove.getColumn(), token);
		}
	return currentBoard;
}
	
	public static GameBoard move(GameBoard currentBoard, Move currentMove, char token) {
		GameBoard newBoard = MoveAllTokens.moveAll(currentBoard, currentMove);
		
		return MoveAllTokens.moveLast(newBoard, currentMove, token);
	}


}
