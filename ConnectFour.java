//Jerry Thomas

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour {
    final String R_TEXT = "\033[91mR\033[0m";
    final String Y_TEXT = "\033[93mY\033[0m";

    String[][] board;

    public ConnectFour() {
        this.board = new String[6][7];
    }

    public void playGame() {
        printBoard();
        while (true) {
            dropRed();
            printBoard();
            boolean winner = isWinner(R_TEXT);
            if (winner) {
                System.out.println("Red Wins!");
                break;
            } else {
                boolean tie = checkTie();
                if (tie) {
                    System.out.println("Nobody won!");
                    break;
                }
                dropYellow();
                printBoard();
                winner = isWinner(Y_TEXT);
                if (winner) {
                    System.out.println("Yellow Wins!");
                    break;
                } else {
                    tie = checkTie();
                    if (tie) {
                        System.out.println("Nobody won!");
                        break;
                    }
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 6 & board[i][j] == null) {
                    System.out.print("|   |");
                } else if (j == 6) {
                    System.out.print("| " + board[i][j] + " |");
                } else if (board[i][j] == null) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| " + board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println(".............................");
    }

    public void dropRed() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Drop a red disk at column (0 - 6): ");
                int rlocation = input.nextInt();
                int count = 5;
                while (count >= 0) {
                    if (board[count][rlocation] != null) {
                        count--;
                    } else if (board[count][rlocation] == null) {
                        board[count][rlocation] = R_TEXT;
                        break;
                    }
                }
                if (count < 0) {
                    System.out.println("Column is full");
                } else {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an actual number...");
                input.next();
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("That is not a valid index number...");
            }
        }
    }

    public void dropYellow() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Drop a yellow disk at column (0 - 6): ");
                int ylocation = input.nextInt();
                int count = 5;
                while (count >= 0) {
                    if (board[count][ylocation] != null) {
                        count--;
                    } else if (board[count][ylocation] == null) {
                        board[count][ylocation] = Y_TEXT;
                        break;
                    }
                }
                if (count < 0) {
                    System.out.println("Column is full");
                } else {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an actual number...");
                input.next();
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("That is not a valid index number...");
            }
        }
    }

    public boolean isWinner(String tokenColor) {
        // Indicates the column and row indexes that contain the given token
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        // Loop through the board starting with the bottom row
        for (int row_index = board.length - 1; row_index >= 0; row_index--) {
            for (int col_index = 0; col_index < board[row_index].length; col_index++) {
                String currentValue = board[row_index][col_index];
                if (currentValue != null && currentValue.equals(tokenColor)) {
                    coordinates.add(new Coordinate(row_index, col_index));
                }
            }
        }
        return checkRight(coordinates) || checkLeft(coordinates) || checkDown(coordinates) || checkLeftDownDiag(coordinates) || checkRightDownDiag(coordinates) || checkLeftUpDiag(coordinates) || checkRightUpDiag(coordinates);
    }

    public boolean checkRight(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the column is greater than 3, then it's impossible to connect 4 going right from the current value
            if (col_index <= 3) {
                String currentValue = board[row_index][col_index];
                String rightValue1 = board[row_index][col_index + 1];
                String rightValue2 = board[row_index][col_index + 2];
                String rightValue3 = board[row_index][col_index + 3];
                if (rightValue1 == null || rightValue2 == null || rightValue3 == null) {
                    continue;
                }
                if (rightValue1.equals(currentValue) && rightValue2.equals(currentValue) && rightValue3.equals(currentValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLeft(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the column is less than 3, then it's impossible to connect 4 going left from the current value
            if (col_index >= 3) {
                String currentValue = board[row_index][col_index];
                String leftValue1 = board[row_index][col_index - 1];
                String leftValue2 = board[row_index][col_index - 2];
                String leftValue3 = board[row_index][col_index - 3];
                if (leftValue1 == null || leftValue2 == null || leftValue3 == null) {
                    continue;
                }
                if (leftValue1.equals(currentValue) && leftValue2.equals(currentValue) && leftValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkDown(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the row is greater than 2, then it's impossible to connect 4 going down from the current value
            if (row_index <= 2) {
                String currentValue = board[row_index][col_index];
                String downValue1 = board[row_index + 1][col_index];
                String downValue2 = board[row_index + 2][col_index];
                String downValue3 = board[row_index + 3][col_index];

                if (downValue1 == null || downValue2 == null || downValue3 == null) {
                    continue;
                }
                if (downValue1.equals(currentValue) && downValue2.equals(currentValue) && downValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkLeftDownDiag(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the row is greater than 2 and the column is less than 3, then it's impossible to connect 4 going down and to the left diagonally from the current value
            if (row_index <= 2 && col_index >= 3) {
                String currentValue = board[row_index][col_index];
                String leftDownDiagonalValue1 = board[row_index + 1][col_index - 1];
                String leftDownDiagonalValue2 = board[row_index + 2][col_index - 2];
                String leftDownDiagonalValue3 = board[row_index + 3][col_index - 3];

                if (leftDownDiagonalValue1 == null || leftDownDiagonalValue2 == null || leftDownDiagonalValue3 == null) {
                    continue;
                }
                if (leftDownDiagonalValue1.equals(currentValue) && leftDownDiagonalValue2.equals(currentValue) && leftDownDiagonalValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkRightDownDiag(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the row is greater than 2 and the column is greater than 3, then it's impossible to connect 4 going down and to the right diagonally from the current value
            if (row_index <= 2 && col_index <= 3) {
                String currentValue = board[row_index][col_index];
                String rightDownDiagonalValue1 = board[row_index + 1][col_index + 1];
                String rightDownDiagonalValue2 = board[row_index + 2][col_index + 2];
                String rightDownDiagonalValue3 = board[row_index + 3][col_index + 3];

                if (rightDownDiagonalValue1 == null || rightDownDiagonalValue2 == null || rightDownDiagonalValue3 == null) {
                    continue;
                }
                if (rightDownDiagonalValue1.equals(currentValue) && rightDownDiagonalValue2.equals(currentValue) && rightDownDiagonalValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkLeftUpDiag(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the row is less than 3 and the column is less than 3, then it's impossible to connect 4 going up and to the left diagonally from the current value
            if (row_index >= 3 && col_index >= 3) {
                String currentValue = board[row_index][col_index];
                String leftUpDiagonalValue1 = board[row_index - 1][col_index - 1];
                String leftUpDiagonalValue2 = board[row_index - 2][col_index - 2];
                String leftUpDiagonalValue3 = board[row_index - 3][col_index - 3];

                if (leftUpDiagonalValue1 == null || leftUpDiagonalValue2 == null || leftUpDiagonalValue3 == null) {
                    continue;
                }
                if (leftUpDiagonalValue1.equals(currentValue) && leftUpDiagonalValue2.equals(currentValue) && leftUpDiagonalValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkRightUpDiag(ArrayList<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            int row_index = coordinate.rowIndex();
            int col_index = coordinate.columnIndex();

            // If the row is less than 3 and the column is greater than 3, then it's impossible to connect 4 going up and to the right diagonally from the current value
            if (row_index >= 3 && col_index <= 3) {
                String currentValue = board[row_index][col_index];
                String rightUpDiagonalValue1 = board[row_index - 1][col_index + 1];
                String rightUpDiagonalValue2 = board[row_index - 2][col_index + 2];
                String rightUpDiagonalValue3 = board[row_index - 3][col_index + 3];

                if (rightUpDiagonalValue1 == null || rightUpDiagonalValue2 == null || rightUpDiagonalValue3 == null) {
                    continue;
                }
                if (rightUpDiagonalValue1.equals(currentValue) && rightUpDiagonalValue2.equals(currentValue) && rightUpDiagonalValue3.equals(currentValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkTie() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    // There exists an empty space, therefore there cannot be a tie yet
                    return false;
                }
            }
        }

        return !isWinner(R_TEXT) && !isWinner(Y_TEXT);
    }
}
