import java.util.Random;

public class Treasure{

	Player player;
	public void setPlayer(Player player){
		this.player = player;
	}

	//how to randomly generate location of treasure
	int x = (int)(Math.random() * 19 + 0);
	int y = (int)(Math.random() * 19 + 0);

	public void setLocation(int[][] board){
		//makes sure treasure is placed in an empty cave
		while(board[x][y] == 3){
			x = (int)(Math.random() * 19 + 0);
			y = (int)(Math.random() * 19 + 0);
		}
		board[x][y] = 7;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//use Player location and test
	public void notify(int [][] board){
		int player_x = player.getX() - 1;
		int player_y = player.getY() - 1;
		if (player_x == -1) player_x = 19;
		if (player_y == -1) player_y = 19;

		if(board[player_x][player.getY()] == 7 ||
		   board[player.getX()][(player.getY()+1) % 20] == 7 ||
		   board[(player.getX()+1) % 20][player.getY()] == 7 ||
		   board[player.getX()][player_y] == 7){
		   	   System.out.println("There is a shiny glitteringness");		
	   }
   }
  
}