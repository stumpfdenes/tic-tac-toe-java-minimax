package player;

import game.TicTacToe;

import java.util.Random;

/**
 *
 * @param letter
 */
public record RandomPlayer(char letter) implements Player {

    @Override
    public int getMove(TicTacToe game) {
        Random random = new Random();
        int randomIndex = random.nextInt(game.availableMoves().size()); // Choose a random index
        return game.availableMoves().get(randomIndex); // Return the move based on the index of an available move
    }
}
