package game;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private char[] board;
    private Character currentWinner;

    // Constructor
    public TicTacToe() {
        this.board = makeBoard();
        this.currentWinner = null;
    }

    // Getter for currentWinner
    public Character getCurrentWinner() {
        return currentWinner;
    }

    // Create a new board
    private char[] makeBoard() {
        char[] newBoard = new char[9];
        for (int i = 0; i < 9; i++) {
            newBoard[i] = ' ';
        }
        return newBoard;
    }

    // Print the board
    public void printBoard() {
        // ANSI escape codes for gray and reset
        String gray = "\033[90m";
        String reset = "\033[0m";

        for (int i = 0; i < 3; i++) {
            System.out.println("| "
                    + (board[i * 3] == ' ' ? gray + (i * 3 + 1) + reset : board[i * 3]) + " | "
                    + (board[i * 3 + 1] == ' ' ? gray + (i * 3 + 2) + reset : board[i * 3 + 1]) + " | "
                    + (board[i * 3 + 2] == ' ' ? gray + (i * 3 + 3) + reset : board[i * 3 + 2]) + " |");
        }
    }

    // Print board indices
    public static void printBoardNums() {
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + (i * 3 + 1) + " | " + (i * 3 + 2) + " | " + (i * 3 + 3) + " |");
        }
    }

    // Make a move
    public boolean makeMove(int square, char letter) {
        if (board[square] == ' ') {
            board[square] = letter;
            if (winner(square, letter)) {
                currentWinner = letter;
            }
            return true;
        }
        return false;
    }

    public void undoMove(int lastMove) {
        // Create a copy of the current board to undo the move safely.
        char[] newBoard = board.clone(); // Clone the board to prevent reference issues

        // Undo the move by setting the position back to ' ' (empty)
        newBoard[lastMove] = ' ';

        // Set the updated board back using the setter
        this.board = newBoard;

        // Optionally, reset the winner if necessary
        currentWinner = null;  // No winner after undoing the move
    }

    // Check for a winner
    private boolean winner(int square, char letter) {
        int rowIndex = square / 3;
        int colIndex = square % 3;

        // Check row
        if (board[rowIndex * 3] == letter && board[rowIndex * 3 + 1] == letter && board[rowIndex * 3 + 2] == letter) {
            return true;
        }

        // Check column
        if (board[colIndex] == letter && board[colIndex + 3] == letter && board[colIndex + 6] == letter) {
            return true;
        }

        // Check diagonals
        if (square % 2 == 0) {
            if (board[0] == letter && board[4] == letter && board[8] == letter) {
                return true;
            }
            return board[2] == letter && board[4] == letter && board[6] == letter;
        }

        return false;
    }

    // Check if there are empty squares
    public boolean emptySquares() {
        for (char c : board) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }

    // Count the number of empty squares
    public int numEmptySquares() {
        int count = 0;
        for (char c : board) {
            if (c == ' ') {
                count++;
            }
        }
        return count;
    }

    // Get a list of available moves
    public List<Integer> availableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                moves.add(i);
            }
        }
        return moves;
    }

    // Print a list of available moves in a more human way
    public List<Integer> printableMoves() {
        List<Integer> pMoves = new ArrayList<>();
        for (int move : this.availableMoves()) {
            pMoves.add(move+1);
        }
        return pMoves;
    }
}
