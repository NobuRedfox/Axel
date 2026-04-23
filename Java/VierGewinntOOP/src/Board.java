public class Board {
    private char[][] board;

    public Board() {
        board = new char[6][7];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                board[row][col] = '.';
            }
        }
    }

    public void printBoard() {
        System.out.println("     1   2   3   4   5   6   7");
        System.out.println("   ┌───┬───┬───┬───┬───┬───┬───┐");

        for (int row = 0; row < 6; row++) {
            System.out.printf(" %d │", (row + 1));

            for (int col = 0; col < 7; col++) {
                System.out.printf(" %c │", board[row][col]);
            }
            System.out.println();

            if (row < 5) {
                System.out.println("   ├───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("   └───┴───┴───┴───┴───┴───┴───┘");
    }

    public boolean dropPiece(int column, char symbol) {
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == '.') {
                board[row][column] = symbol;
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < 7; col++) {
            if (board[0][col] == '.') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(char symbol) {
        return checkHorizontal(symbol)
                || checkVertical(symbol)
                || checkDiagonalDownRight(symbol)
                || checkDiagonalUpRight(symbol);
    }

    private boolean checkHorizontal(char symbol) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol &&
                        board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol &&
                        board[row][col + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char symbol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == symbol &&
                        board[row + 1][col] == symbol &&
                        board[row + 2][col] == symbol &&
                        board[row + 3][col] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalDownRight(char symbol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol &&
                        board[row + 1][col + 1] == symbol &&
                        board[row + 2][col + 2] == symbol &&
                        board[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalUpRight(char symbol) {
        for (int row = 3; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol &&
                        board[row - 1][col + 1] == symbol &&
                        board[row - 2][col + 2] == symbol &&
                        board[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }
}
