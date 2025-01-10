package player;

import game.TicTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @param letter
 */
public record PerfectPlayer(char letter) implements Player {

    @Override
    public int getMove(TicTacToe game) {
        int move;
        if (game.availableMoves().size() == 9) {
            Random random = new Random();
            int randomIndex = random.nextInt(game.availableMoves().size()); // Choose a random index
            move = game.availableMoves().get(randomIndex); // Return the move
        } else {
            move = this.minimax(game, this.letter()).get("position"); // Return the move
        }
        return move;
    }

    public Map<String, Integer> minimax(TicTacToe state, char player) {
        char maxPlayer = this.letter();
        char otherPlayer = player == 'O' ? 'X' : 'O';
        Map<String, Integer> result = new HashMap<>();

        // Check terminal results
        if (Objects.equals(state.getCurrentWinner(), otherPlayer)) {
            // Let score be negative or positive based on who won, multiplied by the number of remaining moves
            int score = (otherPlayer == maxPlayer ? 1 : -1) * (state.numEmptySquares() + 1);
            result.put("position", -1);
            result.put("score", score);
            return result;
        } else if (!state.emptySquares()) {
            result.put("position", -1);
            result.put("score", 0);
            return result;
        }

        // Initialize bestScore and bestPosition
        int bestPosition = -1;
        int bestScore = player == maxPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int possibleMove : state.availableMoves()) {
            // Make a move
            state.makeMove(possibleMove, player);

            // Simulate the opponent's move, alternating recursively until a terminal state
            Map<String, Integer> simulatedResult;
            simulatedResult = minimax(state, player == 'X' ? 'O' : 'X');

            // Undo the moves and return to the original state
            state.undoMove(possibleMove);

            simulatedResult.put("position", possibleMove);

            if (player == maxPlayer) {
                if (simulatedResult.get("score") > bestScore) {
                    bestPosition = possibleMove;
                    bestScore = simulatedResult.get("score");
                }
            } else {
                if (simulatedResult.get("score") < bestScore) {
                    bestPosition = possibleMove;
                    bestScore = simulatedResult.get("score");
                }
            }
        }

        result.put("position", bestPosition);
        result.put("score", bestScore);
        return result;
    }

}
