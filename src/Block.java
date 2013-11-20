import java.util.ArrayList;

import acm.util.RandomGenerator;


public class Block {

	public Square[][] grid = new Square[4][4];
	private int blockX;
	private int blockY;
	private RandomGenerator rgen = RandomGenerator.getInstance();


	public Block(){

		blockX = 5;
		blockY = 0;

		pickRandomBlock();
		shiftToUpperLeft();
		setSquareLocations();

	}//block brace


	private void setSquareLocations(){

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				Square currSq = grid[i][j];


				if(currSq != null){

					currSq.setGridLocation(blockX + i, blockY + j);

				}

			}
		}

	}




	public void blockMoveDown(){

		blockY ++;
		setSquareLocations();

		if(isValidMove() == false){

			blockY --;
			setSquareLocations();
			//set spot on game grid

			addBlockToBoard();

			TetrisGame.game.nextBlock();


		}

	}


	public void blockMoveLeft(){

		blockX --;
		setSquareLocations();

		if(isValidMove() == false){

			blockX ++;
			setSquareLocations();

		}



	}

	public void blockMoveRight(){

		blockX ++;
		setSquareLocations();

		if(isValidMove() == false){

			blockX --;
			setSquareLocations();

		}

	}

	public boolean isValidMove(){


		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				Square currSq = grid[i][j];

				if(currSq != null){

					if(currSq.getGridLocationX() < 0){
						return false;
					}

					if(currSq.getGridLocationX() > 9){
						return false;
					}


					if(currSq.getGridLocationY() > 19){
						return false;
					}

					if(TetrisGame.game.grid[currSq.getGridLocationX() ][currSq.getGridLocationY() ] != null){

						return false;



					}



				}

			}
		}

		return true;

	}


	public void addBlockToBoard(){


		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				Square currSq = grid[i][j];


				if(currSq != null){

					TetrisGame.game.grid[currSq.getGridLocationX() ][currSq.getGridLocationY() ] = currSq;

				}

			}
		}



	}

	public void turnBlock(){

		convertBlockGrid();
		convertBack();
		shiftToUpperLeft();
		setSquareLocations();
		
		if(isValidMove() == false){
			//was iligal move. this spins it back around.
			//three more turns is a complete circle.
			convertBlockGrid();
			convertBack();
			convertBlockGrid();
			convertBack();
			convertBlockGrid();
			convertBack();
			
			shiftToUpperLeft();
			setSquareLocations();
			
			
		}

	}

	public void shiftToUpperLeft(){

		while(isFirstRowBlank() == true){

			shiftRowUp();

		}

		while(isFirstColumbBlank() == true){

			shiftCoulumbLeft();

		}



	}

	public boolean isFirstColumbBlank(){

		for(int i=0;i<4;i++){

			if(grid[0][i] != null){

				return false;

			}

		}

		return true;

	}



	public boolean isFirstRowBlank(){

		for(int i=0;i<4;i++){

			if(grid[i][0] != null){

				return false;

			}

		}

		return true;

	}

	public void shiftCoulumbLeft(){

		for(int i=0;i<4;i++){

			grid[0][i] = grid[1][i];
			grid[1][i] = grid[2][i];
			grid[2][i] = grid[3][i];
			grid[3][i] = null;


		}



	}

	public void shiftRowUp(){

		for(int i=0;i<4;i++){

			grid[i][0] = grid[i][1];
			grid[i][1] = grid[i][2];
			grid[i][2] = grid[i][3];
			grid[i][3] = null;


		}



	}


	public void convertBlockGrid(){

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				Square currSq = grid[i][j];


				if(currSq != null){

					int x = i - 2;
					int y = j - 2;
					if(x >= 0){
						x++;	
					}
					if(y >=0){
						y++;
					}
					currSq.setRotatingGrid(y, - x);
				}

			}
		}



	}

	public void convertBack(){

		Square[][] newGrid = new Square[4][4];

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				Square currSq = grid[i][j];


				if(currSq != null){

					int x = currSq.getRotatingGridX() +2;
					int y = currSq.getRotatingGridY() +2;
					if(x >= 3){
						x--;	
					}
					if(y >= 3){
						y--;
					}
					newGrid[x][y] = currSq;
				}

			}
		}

		grid = newGrid;


	}








	public void squareBlock(){

		//square block
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){

				Square sq = new Square(4);
				grid[j][i] = sq;
				TetrisGame.game.add(sq);

			}

		}



	}// squareBlock brace


	public void leftTurnBlock(){

		//left turn
		Square sq = new Square(2);
		grid[0][0] = sq;
		TetrisGame.game.add(sq);

		Square sq2 = new Square(2);
		grid[0][1] = sq2;
		TetrisGame.game.add(sq2);

		Square sq3 = new Square(2);
		grid[0][2] = sq3;
		TetrisGame.game.add(sq3);

		Square sq4 = new Square(2);
		grid[1][2] = sq4;
		TetrisGame.game.add(sq4);

	}//leftTurn



	public void rightTurnBlock(){

		//right turn
		Square sq = new Square(3);
		grid[1][0] = sq;
		TetrisGame.game.add(sq);

		Square sq2 = new Square(3);
		grid[1][1] = sq2;
		TetrisGame.game.add(sq2);

		Square sq3 = new Square(3);
		grid[1][2] = sq3;
		TetrisGame.game.add(sq3);

		Square sq4 = new Square(3);
		grid[0][2] = sq4;
		TetrisGame.game.add(sq4);


	}//rightTurn



	public void zBlock(){

		//z
		Square sq = new Square(7);
		grid[0][0] = sq;
		TetrisGame.game.add(sq);

		Square sq2 = new Square(7);
		grid[1][0] = sq2;
		TetrisGame.game.add(sq2);

		Square sq3 = new Square(7);
		grid[1][1] = sq3;
		TetrisGame.game.add(sq3);

		Square sq4 = new Square(7);
		grid[2][1] = sq4;
		TetrisGame.game.add(sq4);

	}//zBlock brace





	public void sBlock(){

		//s
		Square sq = new Square(5);
		grid[0][1] = sq;
		TetrisGame.game.add(sq);

		Square sq2 = new Square(5);
		grid[1][1] = sq2;
		TetrisGame.game.add(sq2);

		Square sq3 = new Square(5);
		grid[1][0] = sq3;
		TetrisGame.game.add(sq3);

		Square sq4 = new Square(5);
		grid[2][0] = sq4;
		TetrisGame.game.add(sq4);

	}//sBlock brace



	public void tallBlock(){

		//long tall block
		for(int i = 0; i < 4; i ++){

			Square sq = new Square(1);
			grid[0][i] = sq;
			TetrisGame.game.add(sq);

		}

	}//tallBlock brace


	public void tBlock(){

		//t
		Square sq = new Square(6);
		grid[0][1] = sq;
		TetrisGame.game.add(sq);

		Square sq2 = new Square(6);
		grid[1][1] = sq2;
		TetrisGame.game.add(sq2);

		Square sq4 = new Square(6);
		grid[2][1] = sq4;
		TetrisGame.game.add(sq4);

		Square sq3 = new Square(6);
		grid[1][0] = sq3;
		TetrisGame.game.add(sq3);


	}//tBlock brace

	public void pickRandomBlock(){

		int blockNum = rgen.nextInt(7);


		if(blockNum == 0){

			squareBlock();

		}else if(blockNum == 1){

			leftTurnBlock();

		}else if (blockNum == 2){

			rightTurnBlock();

		}else if(blockNum == 3){

			zBlock();

		}else if(blockNum == 4){

			sBlock();

		}else if(blockNum == 5){

			tallBlock();

		}else if(blockNum == 6){

			tBlock();

		}

	}//pickRandomBlock brace



}//end brace
