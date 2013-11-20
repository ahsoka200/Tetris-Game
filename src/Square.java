import java.awt.Point;

import acm.graphics.GImage;


public class Square extends GImage {

	private int squareGridX;
	private int squareGridY;
	private int rotatingGridX;
	private int rotatingGridY;


	public Square(int color){

		super("Tetris block " + color + ".png");

	}

	public void setGridLocation(int gridX, int gridY){

		squareGridX = gridX;
		squareGridY = gridY;

		setLocation(squareGridX * 40, (squareGridY-2) * 40);

	}

	public int getGridLocationX(){

		return squareGridX;

	}


	public int getGridLocationY(){

		return squareGridY;

	}


	public int getRotatingGridX(){

		return rotatingGridX;


	}

	public int getRotatingGridY(){

		return rotatingGridY;


	}


	public void setRotatingGrid(int x, int y){

		rotatingGridX = x;
		rotatingGridY = y;

	}









}//end brace





