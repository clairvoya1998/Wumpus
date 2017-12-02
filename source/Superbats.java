/**
 * Created by xw37 on 03/02/17.
 */
public class Superbats {
    Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    public void generatesuperbats(int[][] board) {
        for (int i = 1; i < 14; i++) {
            int x = (int)(Math.random() * 19 + 0);
            int y = (int)(Math.random() * 19 + 0);
            if (board[x][y] != 5 && board[x][y] != 3 && board[x][y] != 7 && board[x][y] != 1)
                board[x][y] = 5;
        }
        // generate random number of superbats(less than 14)
    }

    public void notify(int[][] board) {
        int x_bat = player.getX() - 1;
        int y_bat = player.getY() - 1;
        if (x_bat < 0) x_bat = 19;
        if (y_bat < 0) y_bat = 19;
        // if the detector reached the boundary, it will move as a torus
        if (board[(x_bat) % 20][player.getY()] == 5 ||
                board[player.getX()][(player.getY() + 1) % 20] == 5 ||
                board[(player.getX() + 1) % 20][player.getY()] == 5 ||
                board[player.getX()][(y_bat) % 20] == 5) {
            // detector for bats
            System.out.println("There is at least one bat nearby");
        }
    }
}
