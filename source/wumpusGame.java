public class wumpusGame{


	public static void main(String[]args){
		//torus & gameboard
		int [][] board = new int [20][20];
		//runs game
		Superbats superbats = new Superbats();
		Treasure treasure = new Treasure();
		Status status = new Status();
		Wumpus wumpus = new Wumpus();
		Pits pits = new Pits();
		Exit exit = new Exit();
		play(board,superbats,wumpus,status,pits,treasure,exit);
	}


	public static void play(int[][] board, Superbats superbats, Wumpus wumpus, Status status, Pits pits, Treasure treasure, Exit exit){
		initiateboard(board);
		dropthesprites(board, superbats,wumpus,status,pits,treasure);
		Player player = new Player(0,0,true,8,false,false,false,0, superbats,treasure, status,wumpus,pits, exit);
		player.setLocation(board);
		System.out.println("Your current location is: " + (player.getX() + 1) + " , " + (player.getY() + 1));
		player.run(board);
	}

	public static void initiateboard(int[][] board){
		for(int row = 0; row < 20; row++){
			for(int col= 0; col < 20 ; col++){
				board[row][col] = 0;
			}
		}
		// clear the board
	}

	public static void dropthesprites(int[][] board, Superbats superbats, Wumpus wumpus, Status status, Pits pits, Treasure treasure){
		System.out.println("Generating the treasure and monsters!!!!!!! They are coming.....");

		wumpus.setLocation(board);
		treasure.setLocation(board);
		pits.generatepits(board);
		superbats.generatesuperbats(board);
		status.dropstatus(board);
	}

}