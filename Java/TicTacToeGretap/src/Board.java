public class Board {

    private char[][] board = new char[3][3];

    public Board() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("    1   2   3");
        System.out.println("  ┌───┬───┬───┐");

        for (int row = 0; row < 3; row++) {
            System.out.printf("%d │", row + 1);

            for (int col = 0; col < 3; col++) {
                System.out.printf(" %c │", board[row][col]);
            }

            System.out.println();

            if (row < 2) {
                System.out.println("  ├───┼───┼───┤");
            }
        }

        System.out.println("  └───┴───┴───┘");
    }

    public boolean setMove(int row, int col, char symbol) {
        if (!isEmpty(row, col)) {
            return false;
        }

        board[row][col] = symbol;
        return true;
    }
}