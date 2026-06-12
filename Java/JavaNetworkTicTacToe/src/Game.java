import java.util.Scanner;

public class Game {

    private Board board = new Board();
    private Scanner scanner = new Scanner(System.in);

    public void start() {

        char currentPlayer = 'X';

        while (true) {

            board.printBoard();

            System.out.println("Player " + currentPlayer);

            System.out.print("Row (1-3)");
            int row = scanner.nextInt();

            System.out.print("Col (1-3)");
            int col = scanner.nextInt();

            if (!board.isValid(row - 1, col - 1)) {
                System.out.println("Invalid position!");
                continue;
            }

            boolean success = board.setMove(row - 1, col - 1, currentPlayer);

            if (!success) {
                System.out.println("Field is already taken!");
                continue;
            }

            if (board.hasWinner(currentPlayer)) {
                board.printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Draw!");
                break;
            }

            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }
    }
}
