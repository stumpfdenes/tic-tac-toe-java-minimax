package player;

import game.TicTacToe;

public interface Player {
    char getLetter();
    int getMove(TicTacToe game);
}
