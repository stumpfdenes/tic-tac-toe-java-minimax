package game;

import player.Player;

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
        // Create players using the PlayerFactory
        Player xPlayer = PlayerFactory.getPlayer('X');
        Player oPlayer = PlayerFactory.getPlayer('O');

        // Get the starting symbol
        char starter = PlayerFactory.getStarter();

        // Create the TicTacToe game
        TicTacToe game = new TicTacToe();

        // Start the game
        play(game, xPlayer, oPlayer, starter, true);
    }
}
