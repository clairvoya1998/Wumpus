/**
 * Created by xw37 on 03/02/17.
 */
import java.util.Random;
public class Pits {
    Player player;

    public void setPlayer(Player player){
        this.player = player;
    }

    boolean check = true;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void generatepits(int[][] board) {
        for (int i = 1; i < 9; i++) {
            int x = (int)(Math.random() * 19 + 0);
            int y = (int)(Math.random() * 19 + 0);
            if (board[x][y] != 1 && board[x][y] != 7 && board[x][y] != 3)
            board[x][y] = 1;
        }
        // generate random number of pits(less than 9)
    }

    public void notify(int[][] board) {
            int x_pit = player.getX() - 1;
            int y_pit = player.getY() - 1;
            if (x_pit < 0) x_pit = 19;
            if (y_pit < 0) y_pit = 19;
            // if the detector reached the boundary, it will move as a torus
            if (board[(x_pit) % 20][player.getY()] == 1 ||
                    board[player.getX()][(player.getY() + 1) % 20] == 1 ||
                    board[(player.getX() + 1) % 20][player.getY()] == 1 ||
                    board[player.getX()][(y_pit) % 20] == 1) {
                // detector for pits
                System.out.println("There is a pit nearby");
            }
    }
}
