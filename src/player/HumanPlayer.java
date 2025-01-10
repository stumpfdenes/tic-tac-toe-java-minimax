package player;

import game.TicTacToe;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final char letter;

    public HumanPlayer(char letter) {
        this.letter = letter;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public int getMove(TicTacToe game) {
        Scanner scanner = new Scanner(System.in);
        boolean validSquare = false;
        int move = -1;

        while (!validSquare) {
            System.out.print(letter + "'s turn. Available moves: " + game.printableMoves() + ". Your move: ");
            try {
                move = Integer.parseInt(scanner.nextLine()) - 1;
                if (!game.availableMoves().contains(move)) {
                    throw new IllegalArgumentException();
                }
                validSquare = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid square. Try again.");
            }
        }
        return move;
    }
}
