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

    public boolean isEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean setMove(int row, int col, char symbol) {
        if (!isEmpty(row, col)) {
            return false;
        }

        board[row][col] = symbol;
        return true;
    }

    public boolean hasWinner(char symbol) {

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbol &&
                board[row][1] == symbol &&
                board[row][2] == symbol) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol &&
                board[1][col] == symbol &&
                board[2][col] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol &&
            board[1][1] == symbol &&
            board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol &&
            board[1][1] == symbol &&
            board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid (int row, int col) {

        return row >= 0 &&
               row <  3 &&
               col >= 0 &&
               col <  3;
    }

    public String getBoardAsString() {

        String result = "";

        result += "    1   2   3\n";
        result += "  ┌───┬───┬───┐\n";

        for (int row = 0; row < 3; row++) {

            result += (row + 1) + " │";

            for (int col = 0; col < 3; col++) {
                result += " " + board[row][col] + " │";
            }

            result += "\n";

            if (row < 2) {
                result += "  ├───┼───┼───┤\n";
            }
        }

        result += "  └───┴───┴───┘";

        return result;
    }
}