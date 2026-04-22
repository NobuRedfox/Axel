import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hallo Tic Tac Toe!");

        Scanner scanner = new Scanner(System.in);  //Objekt zum Einlesen
        Random rand = new Random();
        int gameMode = readNumber(scanner, "Spielmodus wählen: 1 = Spieler gegen Spieler, 2 = Spieler gegen Computer", 1, 2);
        char[][] board = new char[3][3];    //3x3 Spielfeld
        char currentPlayer = 'X';   // Startspieler
        boolean hasWinner = false;  // Speichert ob jemand gewonnen hat

        initBoard(board);   // Speilfeld wird mit Leerzeichen gefüllt

        for (int turn = 0; turn < 9; turn++) {  // max Züge = 9, 3x3 Spielfeld
            printBoard(board);  // Spielfeld anzeigen

            if (gameMode == 2 && currentPlayer == 'O'){
                System.out.println("Computer ist dran.");
            } else {
                System.out.println("Spieler " + currentPlayer + " ist dran.");
            }

            if (gameMode == 1 || currentPlayer == 'X') {
                int row = readNumber(scanner, "Zeile eingeben (1-3):", 1, 3) - 1;
                int col = readNumber(scanner, "Spalte eingeben (1-3):", 1, 3) - 1;

                if (isValidMove(board, row, col)) { // prüft ob Zug erlaubt ist
                    board[row][col] = currentPlayer;
                } else {
                    System.out.println("Ungültiger Zug!");
                    turn--; //  Zug wird nicht gezählt, also nochmal
                    continue;
                }
            } else {
                makeComputerMove(board, rand);
            }

            if (checkWinner(board, currentPlayer)) { // prüft ob dieser Spieler gewonnen hat
                System.out.println("Spieler " + currentPlayer + " hat gewonnen!");
                hasWinner = true;
                break;
            }
            currentPlayer = switchPlayer(currentPlayer); // Spieler wechsel
        }
        printBoard(board); // Spielfeld soll nochmal am Ende angezeigt werden

        if (!hasWinner) { // niemand hat gewonnen
            System.out.println("Unentschieden!");
        }
        scanner.close();
    }

    // Spielfeld wird mit Leerzeichen initialisiert
    public static void initBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {   // Zeilen
            for (int j = 0; j < 3; j++) {   // Spalten
                board[i][j] = ' ';
            }
        }
    }

    public static boolean checkWinner(char[][] board, char player) {

        // Reihe checken
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Spalten checken
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Diagonal checken
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        // anderes Diagonal checken
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    // Spieler wechseln
    public static char switchPlayer(char currentPlayer) {
        return currentPlayer == 'X' ? 'O' : 'X';
    }

    // Spielfeld anzeigen
    public static void printBoard(char[][] board) {
        System.out.println("    1   2   3");
        System.out.println("  ┌───┬───┬───┐");
        for (int i = 0; i < 3; i++) {
            //System.out.println((i + 1) + "  " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            System.out.printf("%d │ %c │ %c │ %c │ \n", (i + 1), board[i][0], board[i][1], board[i][2]);
            if (i < 2) {
                // System.out.println("  -----------");
                System.out.println("  ├───┼───┼───┤");
            }
        }
        System.out.println("  └───┴───┴───┘");
    }

    // prüft, ob ein Zug gültig ist
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3){
            return board[row][col] == ' ';
        }
        return false;
    }

    public static int readNumber(Scanner scanner, String text, int min, int max){
        while (true) {
            System.out.println(text);

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();

                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Bitte eine Zahl zwischen " + min + " und " + max + " eingeben!");
                }

            } else {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl eingeben.");
                scanner.next(); // falsche Eingabe aus dem Scanner entfernen
            }
        }
    }

    public static void makeComputerMove(char[][] board, Random rand) {
        if (findCriticalMove(board, 'O')){
            return;
        }
        if (findCriticalMove(board, 'X')){
            return;
        }

        int row;
        int col;

        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != ' ');

        board[row][col] = 'O';
        System.out.println("Computer hat gesetzt: " + (row + 1) + ", " + (col + 1));
    }

    public static boolean findCriticalMove(char[][] board, char player) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == ' ') {
                    board[row][col] = player;

                    if (checkWinner(board, player)) {
                        if (player == 'X') {
                            board[row][col] = 'O';
                        }
                        return true;
                    }
                    board[row][col] = ' ';
                }
            }
        }
        return false;
    }
}