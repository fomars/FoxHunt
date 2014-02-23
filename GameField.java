
class GameField {
	private static byte BOMB = 99; //'99' stands for a BOMB as we are not going to exceed 98 bombs count in any cell
	private byte bombsNum;
	private byte[][] board;
	private byte width;

	public GameField(byte width, Difficulty difficulty){
		this.width = width;		
		this.resolve_Difficulty(difficulty); //Setting the number of bombs considering difficulty
		this.board = new byte[width][width]; //Creating a board
		this.setBombs();
			
	}
	
	//Sets the number of bombs considering difficulty
	private void resolve_Difficulty(Difficulty difficulty){
		switch (difficulty){
		case EASY:
			this.setBombsNum((byte) (width/2));
			break;
		case HARD:
			this.setBombsNum((byte) (width*3/2));
			break;
		case MODERATE:
			this.setBombsNum(width);
			break;
		}
	}
	
	// Randomly placing bombs on a board
	private void setBombs(){
		for (byte i=0; i < this.bombsNum; i++){			
			boolean bombIsSet = false;			
			while (!bombIsSet){
				//Randomly picking a cell
				byte row = (byte) (Math.random()*this.width);
				byte col = (byte) (Math.random()*this.width);
				//Assign bomb if cell is empty
				if (this.board[row][col] != BOMB){
					this.board[row][col] = BOMB;
					bombIsSet = true;
				}
				updateCells(row, col);
			}			
		}
	}
	
	//assigns representatives values to the cells, affected by the bomb in (row,col)
	private void updateCells(byte row, byte col) {
		//row
		for (int c=0; c < this.width; c++){
			if (this.board[row][c] != BOMB){
				this.board[row][c]++;
			}
		}
		//col
		for (byte r=0; r < this.width; r++){
			if (this.board[r][col] != BOMB){
				this.board[r][col]++;
			}
		}
		//diagonal down-right
		for (int r = row+1, c=col+1; r < this.width && c < this.width; r++, c++){
			if (this.board[r][c] != BOMB){
				this.board[r][c]++;				
			}
		}
		//diagonal down-left
		for (int r = row+1, c=col-1; r < this.width && c >= 0; r++, c--){
			if (this.board[r][c] != BOMB){
				this.board[r][c]++;				
			}
		}
		//diagonal up-left
		for (int r = row-1, c=col-1; r >= 0 && c >= 0; r--, c--){
			if (this.board[r][c] != BOMB){
				this.board[r][c]++;				
			}
		}
		//diagonal up-right
		for (int r = row-1, c=col+1; r >= 0 && c < this.width; r--, c++){
			if (this.board[r][c] != BOMB){
				this.board[r][c]++;				
			}
		}		
	}

	public byte getBombsNum() {
		return bombsNum;
	}
	
	public void setBombsNum(byte bombsNum) {
		this.bombsNum = bombsNum;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final byte WIDTH = 8;		
		GameField  field = new GameField(WIDTH, Difficulty.EASY);
		byte[][] board = field.getBoard();
		for (byte[] row : board){
			for (byte cell : row){
				System.out.print(cell);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
		
	}

	private byte[][] getBoard() {
		
		return this.board;
	}

}
