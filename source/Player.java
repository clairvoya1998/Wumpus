public class Player{

	private int x;
	private int y;
	private boolean alive;
	private int arrows;
	private boolean treasurecheck;
	private boolean blind;
	private boolean smell;
	private int record;
	//Extension

	private int countMove;
	private Treasure treasure;
	private Wumpus wumpus;
	private Pits pits;
	private Status status;
	private Superbats superbats;
	private Exit exit;

	public Player() {
	}

	public Player(int x, int y, boolean alive, int arrows, boolean treasurecheck, boolean blind, boolean smell, int countMove, Superbats superbats, Treasure treasure, Status status, Wumpus wumpus, Pits pits, Exit exit) {
		this.x = x;
		this.y = y;
		this.alive = alive;
		this.arrows = arrows;
		this.treasurecheck = treasurecheck;
		this.blind = blind;
		this.smell = smell;
		this.countMove = countMove;
		this.treasure = treasure;
		this.superbats = superbats;
		this.status = status;
		this.wumpus = wumpus;
		this.pits = pits;
		this.exit = exit;
		superbats.setPlayer(this);
		pits.setPlayer(this);
		treasure.setPlayer(this);
		status.setPlayer(this);
		wumpus.setPlayer(this);
		exit.setPlayer(this);
		exit.setWumpus(wumpus);
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
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

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getArrows() {
		return arrows;
	}

	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public boolean isTreasurecheck() {
		return treasurecheck;
	}

	public void setTreasurecheck(boolean treasurecheck) {
		this.treasurecheck = treasurecheck;
	}

	public boolean isBlind() {
		return blind;
	}

	public void setBlind(boolean blind) {
		this.blind = blind;
	}

	public boolean isSmell() {
		return smell;
	}

	public void setSmell(boolean smell) {
		this.smell = smell;
	}

	public int getCountMove() {
		return countMove;
	}

	public void setCountMove(int countMove) {
		this.countMove = countMove;
	}

	// all the getters and setters

	public void setLocation(int[][] board) {
		boolean flag = true;
		while (flag) {
			setX((int) (Math.random() * 19 + 0));
			setY((int) (Math.random() * 19 + 0));
			if (board[getX()][getY()] == 0) {
				flag = false;
				board[getX()][getY()] = 9;
			}
			// if the board is empty, put the player in
		}
	}

	//method to allow player to decide if they would like to shoot an arrow or move in a direction
	public void shootormove(int[][] board) {
		System.out.println("Shoot or Move(press 's' for shoot, 'm' for move)");
		char command_sm = EasyIn.getChar();
		boolean flag = true;
		while (flag) {
			if (command_sm == 's') {
				shoots(board);
				flag = false;

			} else if (command_sm == 'm') {
				makemove(board);
				flag = false;
			} else{
				System.out.println("Incorrect input, please try again!");
				command_sm = EasyIn.getChar();
			}

		}
	}
	// detect shoot or move

	//method that runs the shootormove() method as long as the player still has moves left
	public void run(int[][] board) {
		while (getCountMove() < 100) {
			shootormove(board);
		}
		System.out.println("You've used up your moves, game over...");
		setAlive(false);
		System.exit(0);

	}

	//method that checks that user input is viable
	public boolean inputvalidity(String command) {
		if (command.equals("u") || command.equals("d") || command.equals("l") || command.equals("r") || command.equals("q"))
			return true;
		else {
			System.out.println("Invalid input, try again.");//else it asks the the user to input again
			return false;
		}
	}

	//method that prompts user to enter command
	public String enterCommand() {
		String command;
		do {
			System.out.println("enter the command(u,d,l,r or q)");
			command = EasyIn.getString();
		} while (!(inputvalidity(command)));//as long as input command is correct, code will run
		return command;
	}

	//part of extension
	//methods adds another element to the game by creating a loss of percepts by halting notifications
	public int checkSmell(int smellcount) {
		if (smellcount < 3) {// if the player can smell then it will be notified by adjacemt elements
			if (smellcount > 0)
				System.out.println("You will still lose your sense of smell for the next " + (3 - smellcount) + " moves.");
			smellcount++;
		} else {
			smellcount = 0;
			setSmell(false);
			System.out.println("U've got your sense of smell back.");
		}
		return smellcount;
	}

	public int checkBlind(int blindcount) {// if the player is not blind then they will be notfied by adjacent elements
		if (blindcount < 3) {
			if (blindcount > 0)
				System.out.println("You will still be blind for the next " + (3 - blindcount) + " moves.");
			blindcount++;
		} else {
			blindcount = 0;
			setBlind(false);
			System.out.println("U've got your view back.");
		}
		return blindcount;
	}


	public void makemove(int[][] board) {
		int smellcount = 0;
		int blindcount = 0;
		// part of extension, which adds a time limit in the form of how many moves the player has to find the treasure
			String command = enterCommand();
			// conditionals to move the player on the torus based on their command
			if (command.equals("u")) moveUp(board);
			else if (command.equals("d")) moveDown(board);
			else if (command.equals("l")) moveLeft(board);
			else if (command.equals("r")) moveRight(board);
			else if (command.equals("q")) {
				System.out.println("U've quit the game.");
				System.exit(0);
			}
			collect();
			superbatsdrop(board);
			exit.setLocation(board);
			exitGame();

			if (!(isBlind())) {
				System.out.println("Your current location: " + (getX() + 1) + "," + (getY() + 1));
				treasure.notify(board);
			} else blindcount = checkBlind(blindcount);
			if (!(isSmell())) {
				wumpus.notify(board);
			} else smellcount = checkSmell(smellcount);
			status.generatestatus();
			superbats.notify(board);
			pits.notify(board);
			setCountMove(getCountMove() + 1);

	}

	// move methods which move the player up,down, right, or left
	public void moveDown(int[][] board) {
		if (board[(getX() + 1) % 20][getY()] == 3 || board[(getX() + 1) % 20][getY()] == 1) {//if the player runs into the wumpus or pit, GAME OVER.
			setAlive(false);
			System.out.println("GAME OVER.");
			System.exit(0);
		} else {
			setRecord(board[(getX() + 1) % 20][getY()]);
			board[getX()][getY()] = 0;// sets location to 0
			board[(getX() + 1) % 20][getY()] = 9;// sets location north of previous location to new location
			setX((getX() + 1) % 20);// use modulus to help cave be continous without edges
		}
	}

	public void moveUp(int[][] board) {
		int x_move = getX() - 1;
		if (x_move < 0) x_move = 19;
		if (board[x_move % 20][getY()] == 3 || board[x_move % 20][getY()] == 1) {
			setAlive(false);
			System.out.println("GAME OVER.");
			System.exit(0);
		} else {
			setRecord(board[x_move % 20][getY()]);
			board[getX()][getY()] = 0;
			board[x_move % 20][getY()] = 9;
			setX(x_move);
		}
	}

	public void moveLeft(int[][] board) {
		int y_move = getY() - 1;
		if (y_move < 0) y_move = 19;
		if (board[getX()][y_move % 20] == 3 || board[getX()][y_move % 20] == 1) {
			setAlive(false);
			System.out.println("GAME OVER.");
			System.exit(0);
		} else {
			setRecord(board[getX()][y_move % 20]);
			board[getX()][getY()] = 0;
			board[getX()][y_move % 20] = 9;
			setY(y_move);
		}
	}

	public void moveRight(int[][] board) {
		if (board[getX()][(getY() + 1) % 20] == 3 || board[getX()][(getY() + 1) % 20] == 1) {
			setAlive(false);
			System.out.println("GAME OVER.");
			System.exit(0);
		} else {
			setRecord(board[getX()][(getY() + 1) % 20]);
			board[getX()][getY()] = 0;
			board[getX()][(getY() + 1) % 20] = 9;
			setY((getY() + 1) % 20);
		}
	}

	//method to shoot the wumpus
	public boolean shoots(int[][] board) {
		if (getArrows() > 0) {
			// player has limited number of arrows
			System.out.println("You have enough arrows to shoot, please select a direction(u, d, l or r):");
			char makeshoot = EasyIn.getChar();
			int shoot_x;
			int shoot_y;
			switch (makeshoot) {
				//switch statement to determine where the arrow will go: up, down, left or right
				case 'u':
					setArrows(getArrows() - 1);
					if (getY() == 0) shoot_y = 19;
					else shoot_y = getY() - 1;
					if (board[getX()][shoot_y] == 3) return true;
					else {
						System.out.println("Ouch, missed.");
						//if player misses, wumpus will move to adjacent cave if disturbed
						return false;
					}
				case 'd':
					setArrows(getArrows() - 1);
					if (getY() == 19) shoot_y = 0;
					else shoot_y = getY() + 1;
					if (board[getX()][shoot_y] == 3) return true;
					else {
						System.out.println("Ouch, missed.");
						return false;
					}
				case 'l':
					setArrows(getArrows() - 1);
					if (getX() == 0) shoot_x = 19;
					else shoot_x = getX() - 1;
					if (board[shoot_x][getY()] == 3) return true;
					else {
						System.out.println("Ouch, missed.");
						return false;
					}
				case 'r':
					setArrows(getArrows() - 1);
					if (getX() == 19) shoot_x = 0;
					else shoot_x = getX() + 1;
					if (board[shoot_x][getY()] == 3) return true;
					else {
						System.out.println("Ouch, missed.");
						return false;
					}
				default:// default statement to monitor if the input is valid
					System.out.println("Incorrect input!");
					return false;
			}
		} else return false;
	}

	//method that returns true if palyer collects treasure
	public void collect() {
		if (getRecord() == 7) {
			setTreasurecheck(true);
			System.out.println("Well done! You found the treasure! Now the exit is open, time to leave!");
		}
	}

	public void superbatsdrop(int[][] board) {
		if (getRecord() == 5) {
			System.out.println("The superbat has changed your place.");
			setLocation(board);
		}
	}

	//emethod to tell if the player has reached the exit and won the game
	public void exitGame() {
		if(getRecord() == 11) {
			System.out.println("You have reached the exit! CONGRATULATIONS, you have won Hunt the Wumpus!");
			System.exit(0);
		}
	}
}









	
	
	
	
	
	
