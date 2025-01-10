package game;

import player.HumanPlayer;
import player.PerfectPlayer;
import player.Player;
import player.RandomPlayer;

import java.util.Scanner;

public class Main {
    public static void play(TicTacToe game, Player xPlayer, Player oPlayer, char startingLetter, boolean printGame) {
        if (printGame) {
            TicTacToe.printBoardNums(); // Print the board numbers (indices)
        }

        char letter = startingLetter; // Start with chosen letter

        while (game.emptySquares()) {
            Player currentPlayer = (letter == 'O') ? oPlayer : xPlayer;
            int square = currentPlayer.getMove(game);

            if (game.makeMove(square, letter)) {
                if (printGame) {
                    System.out.println(letter + " makes a move to square " + (square + 1));
                    game.printBoard();
                    System.out.println();
                }

                if (game.getCurrentWinner() != null) {
                        System.out.println(letter + " wins!");
                    return; // Game ends
                }

                // Switch player
                letter = (letter == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again."); // Defensive check, should rarely occur
            }

            try {
                //noinspection BusyWait
                Thread.sleep(800); // Pause for 0.8 seconds to simulate a delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (printGame) {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player xPlayer = null;
        System.out.println("Do you want 'X' to be Human, Random, or Perfect player? [h/r/p]");

        while (xPlayer == null) {
            String input = sc.nextLine().toLowerCase(); // Read input once and normalize to lowercase
            switch (input) {
                case "h" -> xPlayer = new HumanPlayer('X');
                case "r" -> xPlayer = new RandomPlayer('X');
                case "p" -> xPlayer = new PerfectPlayer('X');
                default -> System.out.println("Invalid character. Please try again. [h/r/p]");
            }
        }

        Player oPlayer = null;
        System.out.println("How about 'O'? [h/r/p]");

        while (oPlayer == null) {
            String input = sc.nextLine().toLowerCase(); // Read input once and normalize to lowercase
            switch (input) {
                case "h" -> oPlayer = new HumanPlayer('O');
                case "r" -> oPlayer = new RandomPlayer('O');
                case "p" -> oPlayer = new PerfectPlayer('O');
                default -> System.out.println("Invalid character. Please try again. [h/r/p]");
            }
        }

        char starter = ' ';
        System.out.println("Which symbol do you want to be the starter, 'X' or 'O'? [x/o]");

        while (starter == ' ') {
            String input = sc.nextLine().toLowerCase(); // Read input once and normalize to lowercase
            switch (input) {
                case "x" -> starter = 'X';
                case "o" -> starter = 'O';
                default -> System.out.println("Invalid character. Please try again. [x/o]");
            }
        }

        TicTacToe game = new TicTacToe();

        play(game, xPlayer, oPlayer, starter, true);
    }
}
