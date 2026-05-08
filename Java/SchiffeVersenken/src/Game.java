import java.util.Scanner;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        board.placeShip(0, 0, 4, true);
        board.placeShip(3, 3, 3, false);

        while (true) {

            System.out.println("Gegnerisches Feld:");
            board.printVisible();

            System.out.print("Reihe: ");
            int row = scanner.nextInt();

            System.out.print("Spalte: ");
            int col = scanner.nextInt();

            if (row < 1 || row > 10 || col < 1 || col > 10) {
                System.out.println("Ungültige Eingabe. Eingabe 1 - 10.");
                continue;
            }

            board.shoot(row - 1, col - 1);

            System.out.println("Dein Feld:");
            board.printBoard();
        }
    }
}
