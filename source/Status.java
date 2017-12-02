/**
 * Created by xw37 on 08/02/17.
 */

import java.util.Random;
public class Status {
    Player player;
    public void setPlayer(Player player){
        this.player = player;
    }

    public void dropstatus(int[][] board){
        int x = (int) (Math.random() * 19 + 0);
        int y = (int) (Math.random() * 19 + 0);
        if (board[x][y] != 5 && board[x][y] != 3 && board[x][y] != 7 && board[x][y] != 1 && board[x][y] != 20)
            board[x][y] = 20;
    }
    public void generatestatus() {
        boolean flag = false;
        if (player.getRecord() == 20) flag = true;
        while (flag) {
            int choosestatus = (int) (Math.random()*4 + 0);
            flag = false;
            if (choosestatus == 1){
                System.out.println("You have found an extra arrow!!!");
                player.setArrows(player.getArrows() + 1);

            }
            if (choosestatus == 2){
                player.setBlind(true);
                System.out.println("Blind for the next 3 moves!!!");

            }
            if (choosestatus == 3){
                System.out.println("Loss of sense of smell for 3 moves!!!");
                player.setSmell(true);
            }
            if (choosestatus == 4){
                System.out.println("You found a new timer! Now you can move 10 moves further!");
                player.setCountMove(player.getCountMove() - 10);
            }

        }
    }
}
