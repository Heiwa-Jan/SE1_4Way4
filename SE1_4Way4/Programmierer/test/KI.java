package test;

import java.lang.Math;

import src.controller.GameMain;
import src.model.Menu;
import src.model.GameBoard;
import src.model.Constants;

public class KI {
	private int currentWidth;
	private int currentHeight;

	public KI(int heigth,int width){
		setCurrentHight(heigth);
		setCurrentWidth(width);
	}

	public String kiMove() {
		int randomColumns = (int) (Math.random() * getCurrentWidth());
		int randomRows = (int) (Math.random() * getCurrentHight());

		String kiMove = "" + Constants.coordinatesColumns.charAt(randomColumns)
				+ Constants.coordinatesRows.charAt(randomRows);

		return kiMove;
	}

	public int getCurrentHight() {
		return currentHeight;
	}

	public void setCurrentHight(int currentHight) {
		this.currentHeight = currentHight;
	}

	public int getCurrentWidth() {
		return currentWidth;
	}

	public void setCurrentWidth(int currentWidth) {
		this.currentWidth = currentWidth;
	}

}
