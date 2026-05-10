import java.util.Scanner;
import java.util.Random;

public class Game {
    private Player player;
    private Player computer;

    public Game() {
        player = new Player("Spieler");
        computer = new Player("Computer");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        computer.getBoard().placeShip(0, 0, 4, true);
        computer.getBoard().placeShip(3, 3, 4, false);

        player.getBoard().placeShip(1, 1, 4, true);
        player.getBoard().placeShip(5, 5, 3, false);

        while (true) {

            System.out.println("Gegnerisches Feld:");
            computer.getBoard().printVisible();

            System.out.print("Reihe: ");
            int row = scanner.nextInt();

            System.out.print("Spalte: ");
            int col = scanner.nextInt();

            if (row < 1 || row > 10 || col < 1 || col > 10) {
                System.out.println("Ungültige Eingabe. Eingabe 1 - 10.");
                continue;
            }

            computer.getBoard().shoot(row - 1, col - 1);

            if (!computer.getBoard().hasShipsLeft()) {

                System.out.println("Gegnerisches Feld:");
                computer.getBoard().printVisible();

                System.out.println("Alle Schiffe zerstört! Spiel gewonnen!");
                break;
            }

            int aiRow = random.nextInt(10);
            int aiCol = random.nextInt(10);

            // System.out.println("Computer schießt auf: " + (aiRow + 1) + "/" + (aiCol + 1));
            System.out.printf("Computer schießt auf: %d/%d%n", aiRow + 1, aiCol + 1);

            player.getBoard().shoot(aiRow, aiCol);

            if (!player.getBoard().hasShipsLeft()) {

                System.out.println("Dein Feld:");
                player.getBoard().printVisible();

                System.out.println("Alle Schiffe zerstört! Der Computer hat das Spiel gewonnen!");
                break;
            }

            System.out.println("Dein Feld:");
            player.getBoard().printBoard();
        }
    }
}
