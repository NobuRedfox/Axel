public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        board.setMove(0, 0, 'X');
        board.setMove(1, 1, 'O');

        board.printBoard();
    }
}