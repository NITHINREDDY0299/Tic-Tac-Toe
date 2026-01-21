import java.util.Scanner;

/**
 * TicTacToe is a command-line based Tic Tac Toe game for two players.
 * Players can enter their names and take turns to place their marks ('X' or 'O') on a 3x3 grid.
 * The game checks for wins, draws, and allows players to play again.
 */
public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static String playerXName;
    private static String playerOName;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter name for Player X: ");
        playerXName = scanner.nextLine();
        System.out.print("Enter name for Player O: ");
        playerOName = scanner.nextLine();

        System.out.println("Instructions:");
        System.out.println("- The game is played on a 3x3 grid.");
        System.out.println("- Players take turns entering row and column (0-2) to place their mark.");
        System.out.println("- " + playerXName + " (X) goes first.");
        System.out.println("- Enter moves as 'row col' (e.g., '0 1' for top-middle).");
        System.out.println();

        while (playAgain) {
            initializeBoard();
            currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();
                String currentName = (currentPlayer == 'X') ? playerXName : playerOName;
                System.out.println(currentName + " (" + currentPlayer + "), enter your move (row col): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid position. Row and column must be between 0 and 2.");
                } else if (board[row][col] != ' ') {
                    System.out.println("Position already filled. Try again.");
                } else {
                    makeMove(row, col, currentPlayer);
                    if (checkWin(currentPlayer)) {
                        printBoard();
                        System.out.println(currentName + " wins!");
                        gameOver = true;
                    } else if (checkDraw()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("y") || response.equals("yes");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    /**
     * Initializes the Tic Tac Toe board by setting all cells to empty spaces.
     */
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Prints the current state of the Tic Tac Toe board to the console.
     * Displays row and column indices (0-2) for clarity, with a larger visual size.
     */
    private static void printBoard() {
        System.out.println("    0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----------");
        }
        System.out.println();
    }

    /**
     * Checks if a move is valid by ensuring the row and column are within bounds
     * and the cell is empty.
     * @param row the row index (0-2)
     * @param col the column index (0-2)
     * @return true if the move is valid, false otherwise
     */
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    /**
     * Places the player's mark on the specified cell of the board.
     * @param row the row index (0-2)
     * @param col the column index (0-2)
     * @param player the player's mark ('X' or 'O')
     */
    private static void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    /**
     * Checks if the specified player has won the game by having three marks
     * in a row, column, or diagonal.
     * @param player the player's mark ('X' or 'O')
     * @return true if the player has won, false otherwise
     */
    private static boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the game is a draw by verifying if all cells are filled.
     * @return true if the game is a draw, false otherwise
     */
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
