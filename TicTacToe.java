import java.util.Scanner;
public class tictactoe {
    private char[][] board; // 3x3 grid for Tic Tac Toe
    private char currentPlayerMark; // X or O for current player
    private boolean gameActive; // flag to track if game is still active

    // Constructor to initialize the game
    public tictactoe() {
        board = new char[3][3];
        currentPlayerMark = 'X'; // X starts the game
        gameActive = true;
        initializeBoard();
        displayBoard();
        System.out.println("Welcome to Tic Tac Toe! Player 1 (X) goes first. Enter row and column numbers to make your move.");
    }

    // Initialize board with empty spaces
    private void initializeBoard() {
        // Fill the board with empty spaces (representing no move)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Display the current board state
    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Method to switch player turns
    public void changePlayer() {
        currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
    }

    // Method to check if a player has won
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Helper methods to check rows, columns, and diagonals for a win
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    // Helper method to check three cells
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Method to check if the board is full (tie condition)
    public boolean checkBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // Method to handle player's move
    public void makeMove(int row, int col) {
        // Check if the cell is empty
        if (board[row][col] == '-') {
            board[row][col] = currentPlayerMark;
            // Check for win
            if (checkForWin()) {
                gameActive = false;
                displayBoard();
                System.out.println("Congratulations! Player " + currentPlayerMark + " wins!");
            } else if (checkBoardFull()) {
                gameActive = false;
                displayBoard();
                System.out.println("It's a tie!");
            } else {
                changePlayer(); // Switch to the other player's turn
                displayBoard();
            }
        } else {
            System.out.println("That cell is already taken! Please try again.");
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        Scanner scanner = new Scanner(System.in);

        // Game loop
        while (game.gameActive) {
            System.out.print("Player " + game.currentPlayerMark + ", enter your move (row[1-3] column[1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            // Validate input
            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                game.makeMove(row, col);
            } else {
                System.out.println("Invalid input! Row and column numbers must be between 1 and 3.");
            }
        }
        scanner.close();
        System.out.println("Game over.");
    }
}