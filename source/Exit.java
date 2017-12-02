public class Exit{

	Player player;
	public void setPlayer(Player player){
		this.player = player;
	}

	Wumpus wumpus;
	public void setWumpus(Wumpus wumpus){
		this.wumpus = wumpus;
	}

	int x = (int)(Math.random() * 19 + 0);
	int y = (int)(Math.random() * 19 + 0);
	
	
	public void setLocation(int[][] board){
		//avoid dropping exit on spaces that are not empty and contain other elements
		while(board[x][y] == 3 || board[x][y] == 7 || board[x][y] == 1 || board[x][y] == 5 || board[x][y] == 4){
			 x = (int)(Math.random() * 19 + 0);
			 y = (int)(Math.random() * 19 + 0);
		}
		//player can only exit if they have collected the treasure and killed the wumpus
		//part of extension
		if(player.isTreasurecheck() && (!wumpus.alive)){
			board[x][y] = 11;
		}
		else{
			board[x][y] = 0;
		}
	}
	
	//getters
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	//tests percepts. whether or not player is adjacent and notifies. 
	public void notify(int [][] board){

		int x_exit = player.getX() - 1;
		int y_exit = player.getY() - 1;
		if (x_exit < 0) x_exit = 19;
		if (y_exit < 0) y_exit = 19;
		// if the detector reached the boundary, it will move as a torus
		if (board[(x_exit) % 20][player.getY()] == 11 ||
				board[player.getX()][(player.getY() + 1) % 20] == 11 ||
				board[(player.getX() + 1) % 20][player.getY()] == 11 ||
				board[player.getX()][(y_exit) % 20] == 5) {
			// detector for exit
			System.out.println("The exit is close by");
		}
	}
}
  

