// test
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {

            Game game = new Game();
            game.start();

            String answer;
            while (true) {
                System.out.println("Play again? (y/n): ");
                answer = scanner.next();

                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
                    break;
                }

                System.out.println("Please enter y or n.");
            }

            if (answer.equalsIgnoreCase("n")) {
                playAgain = false;
            }
        }
        System.out.println("Thanks for playing!");
    }
}
