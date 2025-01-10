package player;

import game.TicTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class PerfectPlayer implements Player {
    private final char letter;

    public PerfectPlayer(char letter) {
        this.letter = letter;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public int getMove(TicTacToe game) {
        int move;
        if (game.availableMoves().size() == 9) {
            Random random = new Random();
            int randomIndex = random.nextInt(game.availableMoves().size()); // Choose a random index
            move = game.availableMoves().get(randomIndex); // Return the move
        } else {
            move = this.minimax(game, this.getLetter()).get("position"); // Return the move
        }
        return move;
    }

    public Map<String, Integer> minimax(TicTacToe state, char player) {
        char maxPlayer = this.getLetter();
        char otherPlayer = player == 'O' ? 'X' : 'O';
        Map<String, Integer> result = new HashMap<>();

        // Check terminal results
        if (Objects.equals(state.getCurrentWinner(), otherPlayer)) {
            // Let final score be negative or positive based on who won, multiplied by the number of
            // remaining moves for reward scaling, leading to prioritization of quicker wins if possible
            int score = (otherPlayer == maxPlayer ? 1 : -1) * (state.numEmptySquares() + 1);
            result.put("position", -1);
            result.put("score", score);
            return result;
        } else if (!state.emptySquares()) {
            // Set final score to 0 if it's a tie
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

            // Simulated position added to simulated result map
            simulatedResult.put("position", possibleMove);

            // Comparing the scores of simulated results with the best ones and swapping when appropriate
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

        // Adding the position and score of the best result to the result map and returning it
        result.put("position", bestPosition);
        result.put("score", bestScore);
        return result;
    }

}
