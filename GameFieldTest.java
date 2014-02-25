import static org.junit.Assert.*;

import org.junit.Test;


public class GameFieldTest {
	private static int NUM_OF_CHECKS = 5;
	private static byte WIDTH = 10;
	private static byte BOMB = 99;
	@Test
	public void testGameField() {
		checkBunchOfBoards(NUM_OF_CHECKS);
		
	}
	
	private void checkBunchOfBoards(int n){
		for (int i=0; i<n; i++){			
			checkOneBoard();			
		}
	}	
 
	private void checkOneBoard() {
		//create board
		GameBoard gameBoard = new GameBoard(WIDTH, Difficulty.MODERATE);
		//randomly pick 10 cells
		for (int i=0; i<10; i++){
			byte row = (byte) (Math.random()*WIDTH);
			byte col = (byte) (Math.random()*WIDTH);
			if (gameBoard.getBoard()[row][col] != BOMB){
				checkCell(gameBoard, row, col);
			}
		}	
	}

	private void checkCell(GameBoard gameBoard, byte row, byte col) {
		byte[][] board = gameBoard.getBoard();
		int numOfBombs = 0;
		//row
		for (int c=0; c < gameBoard.getWidth(); c++){
			if (board[row][c] == BOMB){
				numOfBombs++;
			}
		}
		//col
		for (byte r=0; r < gameBoard.getWidth(); r++){
			if (board[r][col] == BOMB){
				numOfBombs++;
			}
		}
		//diagonal down-right
		for (int r = row+1, c=col+1; r < gameBoard.getWidth() && c < gameBoard.getWidth(); r++, c++){
			if (board[r][c] == BOMB){
				numOfBombs++;				
			}
		}
		//diagonal down-left
		for (int r = row+1, c=col-1; r < gameBoard.getWidth() && c >= 0; r++, c--){
			if (board[r][c] == BOMB){
				numOfBombs++;				
			}
		}
		//diagonal up-left
		for (int r = row-1, c=col-1; r >= 0 && c >= 0; r--, c--){
			if (board[r][c] == BOMB){
				numOfBombs++;				
			}
		}
		//diagonal up-right
		for (int r = row-1, c=col+1; r >= 0 && c < gameBoard.getWidth(); r--, c++){
			if (board[r][c] == BOMB){
				numOfBombs++;				
			}
		}
		assertEquals(board[row][col], numOfBombs);
	}

}
