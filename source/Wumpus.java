import java.util.Random;

public class Wumpus extends Player{
	//how to set random location
	int x = (int)(Math.random() * 19 + 0);
	int y = (int)(Math.random() * 19 + 0);
	//attributes
	boolean alive = true;
	int x2;
	int y2;
	Player player;
	public void setPlayer(Player player){
		this.player = player;
	}

	/*set location of wumpus
	doesn't need to worry about being placed in a occupied cave because
	the wumpus is dropped first in the main wumpusGame class*/

	public void setLocation(int[][] board){
		board[x][y] = 3;
	}
	
	public int getX(){
		return x;
	} 
	public int getY(){
		return y;
	}

	//tests percepts. whether or not player is adjacent and notifies.
	public void notify(int [][] board){
		int x_player = player.getX() - 1;
		if (x_player < 0) x_player = 19;
		int y_player = player.getY() - 1;
		if (y_player < 0) y_player = 19;
		if(board[x_player % 20][player.getY()] == 3 ||
		   board[player.getX()][(player.getY()+1) % 20] == 3 ||
		   board[(player.getX()+1) % 20][player.getY()] == 3 ||
		   board[player.getX()][y_player % 20] == 3){
		   	   System.out.println("There is a faint Wumpus odor");		
	   }
   }

	//variables & method to creates random movement to adjacent spots if wumpus is disturbed
   int adjacentMoveX = (int)(Math.random() * 1 + 0 );
   int adjacentMoveY = (int)(Math.random() * 1 + 0);

  public int getRandomSign() {
    Random rand = new Random();
    if(rand.nextBoolean()){
        return -1;
    }
    else{
        return 1;
    }
  }

  public int check(int number){
  	if (number < 0) return 19;
	else return (number%20);
  }

	//uses if conditional statments to determine where wumpus can move to adjacent caves based on where player is
	//method to determine if wumpus dies or whether it is disturbed and moves
	public void detect(int [][] board){
		if(player.shoots(board)){
			if(alive){
				int wumpus_x = getX() - 1;
				int wumpus_y = getY() - 1;
				if (wumpus_x == -1) wumpus_x = 19;
				if (wumpus_y == -1) wumpus_y = 19;

					if(board[wumpus_x][getY()] == 9){
						x2 = getX() + adjacentMoveX;
						y2 = getY() + adjacentMoveY * getRandomSign();
						board[check(x2)][check(y2)] = 3;
							
					}
					if(board[(getX()+1) % 20][getY()] == 9){
						x2 = getX() - adjacentMoveX;
						y2 = getY() + adjacentMoveY * getRandomSign();
						board[check(x2)][check(y2)] = 3;
					}
					if(board[getX()][wumpus_y] == 9){
						x2 = getX() + adjacentMoveX * getRandomSign();
						y2 = getY() + adjacentMoveY;
						board[check(x2)][check(y2)] = 3;
					}
					if(board[getX()][(getY()+1) % 20] == 9){
						x2 = getX() + adjacentMoveX * getRandomSign();
						y2 = getY() - adjacentMoveY;
						board[check(x2)][check(y2)] = 3;
					}			
				}		
			else{
					for(int row = 0; row < 20; row++){
						for(int col = 0; col < 20; col++){
							if(board[row][col] == 3){
								board[row][col] = 0;
							}
						}
					}
					System.out.println("CONGRATULATIONS,YOU KILLED THE WUMPUS!!!");
				}
	    }
	}    
}
