//Jerry Thomas
//ID: 111360747
//Lab Section 12

import java.util.*;
public class ConnectFour {
	public static void main(String [] args) {
	String[][] board = new String[6][7];
	ConnectFour c = new ConnectFour();
	boolean winner = false;
	boolean tie = false;
	c.printBoard(board);
	while(!winner) {
		if(!winner){
			c.dropRed(board,c);
			c.printBoard(board);
			winner = c.checkRed(board,c);
			if(winner == true) {
				System.out.println("Red Wins!");
				break;
			}else {
				tie = c.checkTie(board, c);
				if(tie == true) {
					System.out.println("Nobody won!");
					break;
				}
				c.dropYellow(board,c);
				c.printBoard(board);
				winner = c.checkYellow(board,c);
				if(winner == true) {
					System.out.println("Yellow Wins!");
					break;
				}
				else {
					tie = c.checkTie(board, c);
					if(tie == true) {
						System.out.println("Nobody won!");
						break;
					}
				}
			}
		}
	}
	}
	public void printBoard(String[][] board) {
		for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
            	if(j == 6 & board[i][j] == null) {
            		System.out.print("|   |");
            	}
            	else if(j == 6) {
            		System.out.print("| " + board[i][j] + " |");
            	}
            	else if(board[i][j] == null){
            		System.out.print("|   ");
            	}	
            	else{
            		System.out.print("| " + board[i][j] + " ");
            	}
            }
            System.out.println();
        }
        System.out.println(".............................");
	}
	public void dropRed(String[][] board, ConnectFour c) {
		Scanner input = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Drop a red disk at column (0 - 6): ");
				int rlocation = input.nextInt();
				int count = 5;
				while(count >= 0){
					if(board[count][rlocation] != null) {
						count--;
					}
					else if(board[count][rlocation] == null){
						board[count][rlocation] = "R";
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
	public void dropYellow(String[][] board, ConnectFour c) {
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
						board[count][ylocation] = "Y";
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
	public boolean checkRed(String[][] board, ConnectFour c) {
		ArrayList<Integer> column = new ArrayList<Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i = board.length-1; i >= 0; i--) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null) {
					continue;
				}else if(board[i][j].equals("R")) {
					column.add(j);
					row.add(i);
				}
			}
		}
		boolean won = false;
		while(!won) {
			if(checkRight(board,column,row)) {
				return true;
			}
			if(checkLeft(board,column,row)) {
				return true;
			}
			if(checkDown(board,column,row)) {
				return true;
			}
			if(checkLeftDownDiag(board,column,row)) {
				return true;
			}
			if(checkRightDownDiag(board,column,row)) {
				return true;
			}
			if(checkLeftUpDiag(board,column,row)) {
				return true;
			}
			if(checkRightUpDiag(board,column,row)) {
				return true;
			}
			won = true;
		}
		return false;
	}
	public boolean checkYellow(String[][] board, ConnectFour c) {
		ArrayList<Integer> column = new ArrayList<Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i = board.length-1; i >= 0; i--) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null) {
					continue;
				}else if(board[i][j].equals("Y")) {
					column.add(j);
					row.add(i);
				}
			}
		}
		boolean won = false;
		while(!won) {
			if(checkRight(board,column,row)) {
				return true;
			}
			if(checkLeft(board,column,row)) {
				return true;
			}
			if(checkDown(board,column,row)) {
				return true;
			}
			if(checkLeftDownDiag(board,column,row)) {
				return true;
			}
			if(checkRightDownDiag(board,column,row)) {
				return true;
			}
			if(checkLeftUpDiag(board,column,row)) {
				return true;
			}
			if(checkRightUpDiag(board,column,row)) {
				return true;
			}
			won = true;
		}
		return false;
	}
	public boolean checkRight(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(column.get(j)<= 3) {
					if(board[row.get(i)][column.get(j)+1] == null || board[row.get(i)][column.get(j)+2] == null || board[row.get(i)][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)][column.get(j)+3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkLeft(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(column.get(j)>= 3) {
					if(board[row.get(i)][column.get(j)-1] == null || board[row.get(i)][column.get(j)-2] == null || board[row.get(i)][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)][column.get(j)-3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkUp(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)>= 3) {
					if(board[row.get(i)-1][column.get(j)] == null || board[row.get(i)-2][column.get(j)] == null || board[row.get(i)-3][column.get(j)] == null) {
						continue;
					}
					if(board[row.get(i)-1][column.get(j)].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)-2][column.get(j)].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)-3][column.get(j)].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkDown(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2) {
					if(board[row.get(i)+1][column.get(j)] == null || board[row.get(i)+2][column.get(j)] == null || board[row.get(i)+3][column.get(j)] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkLeftDownDiag(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2 && column.get(j) >= 3) {
					if(board[row.get(i)+1][column.get(j)-1] == null || board[row.get(i)+2][column.get(j)-2] == null || board[row.get(i)+3][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)-3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkRightDownDiag(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i)<= 2 && column.get(j) <= 3) {
					if(board[row.get(i)+1][column.get(j)+1] == null || board[row.get(i)+2][column.get(j)+2] == null || board[row.get(i)+3][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)+1][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)+2][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)+3][column.get(j)+3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkLeftUpDiag(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i) >= 3 && column.get(j) >= 3) {
					if(board[row.get(i)-1][column.get(j)-1] == null || board[row.get(i)-2][column.get(j)-2] == null || board[row.get(i)-3][column.get(j)-3] == null) {
						continue;
					}
					if(board[row.get(i)-1][column.get(j)-1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)-2][column.get(j)-2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)-3][column.get(j)-3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkRightUpDiag(String[][] board, ArrayList<Integer> column, ArrayList<Integer> row) {
		for(int i= 0; i < row.size(); i++) {
			for(int j = 0; j< column.size();j++) {
				if(row.get(i) >= 3 && column.get(j) <= 3) {
					if(board[row.get(i)-1][column.get(j)+1] == null || board[row.get(i)-2][column.get(j)+2] == null || board[row.get(i)-3][column.get(j)+3] == null) {
						continue;
					}
					if(board[row.get(i)-1][column.get(j)+1].equals(board[row.get(i)][column.get(j)]) && board[row.get(i)-2][column.get(j)+2].equals(board[row.get(i)][column.get(j)]) &&
							board[row.get(i)-3][column.get(j)+3].equals(board[row.get(i)][column.get(j)])){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkTie(String[][] board, ConnectFour c) {
		int counter = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == null) {
					counter++;
				}
			}
		}
		if(counter == 0) {
			if(checkRed(board,c) == false && checkYellow(board,c) == false) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
