import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class TetrisGame extends GraphicsProgram {

	public static final int APPLICATION_WIDTH = 40 * 10;
	public static final int APPLICATION_HEIGHT = 40 * 18;

	private RandomGenerator rgen = RandomGenerator.getInstance();


	private boolean downHeld = false;
	private boolean upHeld = false;
	private boolean leftHeld = false;
	private boolean rightHeld = false;
	private Block block;
	private GLabel youLoseLabel;
	private GLabel scoreLabel;
	private int SCORE = 0;
	private int level = 1;
	private int lineCleared = 0;
	private int pauseLength = 100;
	private boolean start = false;
	private GLabel messageLabel = new GLabel("");

	public Square[][] grid = new Square[10][20];

	public static TetrisGame game;
	private int counter = 0;


	public void run(){

		setup();
		GameLoop();


	}//run brace




	public void setup(){

		game = this;

		makeScoreLabel();
		setBackground(Color.gray);
		addKeyListeners();
		addMouseListeners();
		statusMessage("Click to start");


		block = new Block(); 

	}//setup brace





	public void GameLoop(){



		boolean alreadyStarted = false;


		while (true){

			//necessary to get start variable to work
			if(!alreadyStarted){
				println(start);
				if(start){
					alreadyStarted = true;
				}
			}

			
			

			
			//makes click to start: o {)
			if(start == true){

				

				if(leftHeld == true){

					block.blockMoveLeft();

				}


				if(rightHeld == true){

					block.blockMoveRight();

				}


				if(upHeld == true){

					block.turnBlock();

				}

				if(downHeld == true){

					counter = counter + 3;

				}


				if(counter >= 5){

					block.blockMoveDown();

					counter = 0;
				}



				//if the blocks go off the top of the screen, you lose :/
				if(isGameOver() == true){

					youLoseLabel = new GLabel("You Lost :]", 100, 100);
					youLoseLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
					youLoseLabel.setColor(Color.white);
					add(youLoseLabel);



					break;
				}




				prossesFullRows();
				pause(pauseLength);

				counter ++;


			}//if start == true  brace


		}//while true brace



	}//GameLoop brace


	public void statusMessage(String message){

		remove(messageLabel);
		messageLabel = new GLabel(message,100, 400);
		messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		messageLabel.setColor(Color.white);
		messageLabel.setLocation(APPLICATION_WIDTH/2 - messageLabel.getWidth()/2,300);
		add(messageLabel);

	}


	public void mouseClicked(MouseEvent mouse){

		start = true;
		remove(messageLabel);
	}


	public void keyPressed(KeyEvent a) {

		//if up is pushed
		if(a.getKeyCode() == KeyEvent.VK_UP) {
			upHeld = true;
		}

		//if down is pushed
		if(a.getKeyCode() == KeyEvent.VK_DOWN) {
			downHeld = true;
		}
		//if left is pushed
		if(a.getKeyCode() == KeyEvent.VK_LEFT) {
			leftHeld = true;
		}

		//if right is pushed
		if(a.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightHeld = true;
		}


	}//keyPressed "a" brace




	public void keyReleased(KeyEvent d) {

		//if up is released
		if(d.getKeyCode() == KeyEvent.VK_UP) {
			upHeld = false;
		}

		//if down is released
		if(d.getKeyCode() == KeyEvent.VK_DOWN) {
			downHeld = false;
		}
		//if left is released
		if(d.getKeyCode() == KeyEvent.VK_LEFT) {
			leftHeld = false;
		}

		//if right is released
		if(d.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightHeld = false;
		}


	}//keyReleased brace


	public void nextBlock(){

		block = new Block(); 


	}




	public boolean isRowFull(int row){

		for(int i=0;i<10;i++){

			if(grid[i][row] == null){

				return false;
			}
		}

		return true;
	}


	public void shiftRowDown(int row){

		for(int i=0;i<10;i++){

			remove(grid[i][row]);

		}

		for(int i=row - 1;i>=0;i--){
			for(int j=0;j<10;j++){

				grid[j][i + 1] = grid[j][i];

				if(grid[j][i + 1] != null){

					grid[j][i + 1].setGridLocation(j, i + 1);

				}

			}
		}
	}//shiftCoulumbDown brace



	public void prossesFullRows(){

		for(int j=0;j<20;j++){

			if(isRowFull(j) == true){

				shiftRowDown(j);

				SCORE = SCORE  + 40 * level;
				scoreLabel.setLabel("score: "+ SCORE);
				lineCleared ++;

				if(lineCleared % 10 == 0){

					level ++;
					pauseLength = (int) (pauseLength * 0.9);

				}

			}
		}

	}


	public boolean isGameOver(){

		for(int i=0;i<10;i++){

			if(grid[i][1] != null){

				return true;

			}
		}
		return false;

	}


	public void makeScoreLabel(){

		scoreLabel = new GLabel("Score: " + SCORE, 10, 20);
		scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,15));
		scoreLabel.setColor(Color.white);
		add(scoreLabel);


	}








}//end brace
