package player;

import game.TicTacToe;

import java.util.Random;

public class RandomPlayer implements Player {
    private final char letter;

    public RandomPlayer(char letter) {
        this.letter = letter;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public int getMove(TicTacToe game) {
        Random random = new Random();
        int randomIndex = random.nextInt(game.availableMoves().size()); // Choose a random index
        return game.availableMoves().get(randomIndex); // Return the move based on the index of an available move
    }
}
