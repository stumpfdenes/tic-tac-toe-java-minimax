package game;

import player.HumanPlayer;
import player.PerfectPlayer;
import player.Player;
import player.RandomPlayer;

import java.util.Scanner;

public class PlayerFactory {

    public static Player getPlayer(char letter) {
        Scanner sc = new Scanner(System.in);
        Player player = null;

        System.out.println("Do you want '" + letter + "' to be Human, Random, or Perfect player? [h/r/p]");

        while (player == null) {
            String input = sc.nextLine().toLowerCase(); // Read input once and normalize to lowercase
            switch (input) {
                case "h" -> player = new HumanPlayer(letter);
                case "r" -> player = new RandomPlayer(letter);
                case "p" -> player = new PerfectPlayer(letter);
                default -> System.out.println("Invalid character. Please try again. [h/r/p]");
            }
        }
        return player;
    }

    public static char getStarter() {
        Scanner sc = new Scanner(System.in);
        char starter = ' ';

        System.out.println("Which symbol do you want to be the starter, 'X' or 'O'? [x/o]");

        while (starter == ' ') {
            String input = sc.nextLine().toLowerCase();
            switch (input) {
                case "x" -> starter = 'X';
                case "o" -> starter = 'O';
                default -> System.out.println("Invalid character. Please try again. [x/o]");
            }
        }
        return starter;
    }
}
