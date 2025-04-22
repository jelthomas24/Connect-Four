//Jerry Thomas
import java.util.*;

public class ConnectFour {
	final String R_TEXT = "\033[91mR\033[0m";
	final String Y_TEXT = "\033[93mY\033[0m";

	String[][] board;
	public ConnectFour() {
		this.board = new String[6][7];
	}

	public static void main(String [] args) {
		ConnectFour c = new ConnectFour();
		c.printBoard();
		while(true) {
			c.dropRed();
			c.printBoard();
			boolean winner = c.checkRed();
			if(winner) {
				System.out.println("Red Wins!");
				break;
			}
			else {
				boolean tie = c.checkTie();
				if(tie) {
					System.out.println("Nobody won!");
					break;
				}
				c.dropYellow();
				c.printBoard();
				winner = c.checkYellow();
				if(winner) {
					System.out.println("Yellow Wins!");
					break;
				}
				else {
					tie = c.checkTie();
					if(tie) {
						System.out.println("Nobody won!");
						break;
					}
				}
			}
		}
	}

	public void printBoard() {
		for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
            	if(j == 6 & board[i][j] == null) {
            		System.out.print("|   |");
            	}
            	else if(j == 6) {
            		System.out.print("| " + board[i][j] + " |");
            	}
            	else if(board[i][j] == null) {
            		System.out.print("|   ");
            	}	
            	else {
            		System.out.print("| " + board[i][j] + " ");
            	}
            }
            System.out.println();
        }
        System.out.println(".............................");
	}
	public void dropRed() {
		Scanner input = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Drop a red disk at column (0 - 6): ");
				int rlocation = input.nextInt();
				int count = 5;
				while(count >= 0) {
					if(board[count][rlocation] != null) {
						count--;
					}
					else if(board[count][rlocation] == null) {
						board[count][rlocation] = R_TEXT;
						break;
					}
				}
				if(count < 0) {
					System.out.println("Column is full");
				}
				else {
					break;
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Please enter an actual number...");
				input.next();
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				System.out.println("That is not a valid index number...");
			}
		}
	}

	public void dropYellow() {
		Scanner input = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Drop a yellow disk at column (0 - 6): ");
				int ylocation = input.nextInt();
				int count = 5;
				while(count >= 0){
					if(board[count][ylocation] != null) {
						count--;
					}
					else if(board[count][ylocation] == null){
						board[count][ylocation] = Y_TEXT;
						break;
					}
				}
				if(count < 0) {
					System.out.println("Column is full");
				}
				else {
					break;
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Please enter an actual number...");
				input.next();
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				System.out.println("That is not a valid index number...");
			}
		}
	}
	public boolean checkRed() {
		ArrayList<Integer> column = new ArrayList<>();
		ArrayList<Integer> row = new ArrayList<>();
		for(int i = board.length-1; i >= 0; i--) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null && board[i][j].equals(R_TEXT)) {
					column.add(j);
					row.add(i);
				}
			}
		}

		return isWinner(column, row);
	}

	public boolean isWinner(ArrayList<Integer> column, ArrayList<Integer> row) {
		return checkRight(column,row) || checkLeft(column,row) || checkDown(column,row) || checkLeftDownDiag(column,row) || checkRightDownDiag(column,row) || checkLeftUpDiag(column,row) || checkRightUpDiag(column,row);
	}

	public boolean checkYellow() {
		ArrayList<Integer> column = new ArrayList<>();
		ArrayList<Integer> row = new ArrayList<>();
		for(int i = board.length-1; i >= 0; i--) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null && board[i][j].equals(Y_TEXT)) {
					column.add(j);
					row.add(i);
				}
			}
		}
		return isWinner(column, row);
	}
	public boolean checkRight(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(column.get(j)<= 3) {
					if(board[row.get(i)][column.get(j)+1] == null || board[row.get(i)][column.get(j)+2] == null || board[row.get(i)][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)][column.get(j)+3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkLeft(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(column.get(j)>= 3) {
					if(board[row.get(i)][column.get(j)-1] == null || board[row.get(i)][column.get(j)-2] == null || board[row.get(i)][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)][column.get(j)-3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkDown(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2) {
					if(board[row.get(i)+1][column.get(j)] == null || board[row.get(i)+2][column.get(j)] == null || board[row.get(i)+3][column.get(j)] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkLeftDownDiag(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2 && column.get(j) >= 3) {
					if(board[row.get(i)+1][column.get(j)-1] == null || board[row.get(i)+2][column.get(j)-2] == null || board[row.get(i)+3][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)-3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkRightDownDiag(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2 && column.get(j) <= 3) {
					if(board[row.get(i)+1][column.get(j)+1] == null || board[row.get(i)+2][column.get(j)+2] == null || board[row.get(i)+3][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)+3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkLeftUpDiag(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i) >= 3 && column.get(j) >= 3) {
					if(board[row.get(i)-1][column.get(j)-1] == null || board[row.get(i)-2][column.get(j)-2] == null || board[row.get(i)-3][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)-1][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)-2][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)-3][column.get(j)-3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkRightUpDiag(ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i) >= 3 && column.get(j) <= 3) {
					if(board[row.get(i)-1][column.get(j)+1] == null || board[row.get(i)-2][column.get(j)+2] == null || board[row.get(i)-3][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)-1][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)-2][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)-3][column.get(j)+3].equals(board[row.get(i)][column.get(j)])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkTie() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null) {
					// There exists an empty space, therefore there cannot be a tie yet
					return false;
				}
			}
		}

        return !checkRed() && !checkYellow();
	}
}
