public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    public void start() {
        board.placeShip(0, 0, 4, true);
        board.placeShip(2, 3, 3, false);

        board.printBoard();
        board.printVisible();
    }
}
