import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public Game() {
        board = new Board();
        player1 = new Player("Player 1", 'X');
        player2 = new Player("Player 2", 'O');
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            board.printBoard();

            System.out.println(currentPlayer.getName() + "'s turn.");

            int input;
            while (true) {
                System.out.println("Choose a column (1-7):");

                try {
                    input = scanner.nextInt();

                    if (input < 1 || input > 7) {
                        System.out.println("Invalid column! Chosse between 1 and 7.");
                        continue;
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Please enter a number!");
                    scanner.next();
                }
            }

            boolean placed = board.dropPiece(input - 1, currentPlayer.getSymbol());

            if (!placed) {
                System.out.println("Column is full!");
                continue;
            }

            if (board.checkWin(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + "'s wins!");
                running = false;
                continue;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                running = false;
                continue;
            }

            switchPlayer();
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
