package player;

import game.TicTacToe;

/**
 * @author stumpfdenes
 */
public interface Player {
    char letter();
    int getMove(TicTacToe game);
}
