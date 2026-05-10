public class Board {
    private char[][] board;
    private char[][] visible;
    private static final int SIZE = 10;

    public Board() {
        board = new char[SIZE][SIZE];
        visible = new char[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++){
               board[row][col] = ' ';
               visible[row][col] = ' ';

            }
        }
    }

    public void printBoard() {
        System.out.println("      1   2   3   4   5   6   7   8   9   10");
        System.out.println("    ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int row = 0; row < SIZE; row++) {
            System.out.printf(" %2d │", (row + 1));
            for (int col = 0; col < SIZE; col++) {
                System.out.printf(" %c │", board[row][col]);
            }
            System.out.println();

            if (row < SIZE - 1) {
                System.out.println("    ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("    └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘");
    }

    public void printVisible() {
        System.out.println("      1   2   3   4   5   6   7   8   9   10");
        System.out.println("    ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int row = 0; row < SIZE; row++) {
            System.out.printf(" %2d │", (row + 1));
            for (int col = 0; col < SIZE; col++) {
                System.out.printf(" %c │", visible[row][col]);
            }
            System.out.println();

            if (row < SIZE - 1) {
                System.out.println("    ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("    └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘");

    /*  System.out.printf("%2d\n", 1); rechtsbündig mit 2 zeichen =  1
        System.out.printf("%2d\n", 10);                           = 10
        System.out.printf("%-2d\n", 1); linksbündig mit 2 zeichen = 1
        System.out.printf("%-2d\n", 10);                          = 10
     */
    }

    public void placeShip(int row, int col, int length, boolean horizontal) {
        if (horizontal) {
            if (col + length > SIZE) {
                System.out.println("Schiff passt nicht horizontal rein!");
                return;
            }

            for (int i = 0; i < length; i++) {
                if (board[row][col + i] == 'S') {
                    System.out.println("Da liegt schon ein Schiff!");
                    return;
                }
            }

            for (int i = 0; i < length; i++) {
                board[row][col +  i] = 'S';
            }

        } else {
            if (row + length > SIZE) {
                System.out.println("Schiff passt nicht vertikal rein!");
                return;
            }

            for (int i = 0; i < length; i++) {
                if (board[row + i][col] == 'S') {
                    System.out.println("Da liegt schon ein Schiff!");
                    return;
                }
            }

            for (int i = 0; i < length; i++) {
                board[row + i][col] = 'S';
            }
        }
    }

    public void shoot(int row, int col) {
        if (visible[row][col] != ' ') {
            System.out.println("Auf dieses Feld wurde bereits geschossen!");
            return;
        }

        if (board[row][col] == 'S') {
            visible[row][col] = 'X';
            System.out.println("Treffer!");
        } else {
            visible[row][col] = 'O';
            System.out.println("Nicht getroffen!");
        }
    }

    public boolean hasShipsLeft() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 'S' && visible[row][col] != 'X') {
                    return true;
                }
            }
        }
        return false;
    }
}

