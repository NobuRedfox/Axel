package org.example.tictactoefx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        final char[] currentPlayer = {'X'};
        Button[][] buttons = new Button[3][3];

        GridPane grid = new GridPane();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                Button button = new Button(" ");
                button.setPrefSize(100, 100);

                buttons[row][col] = button;

                button.setOnAction(e -> {
                    if (button.getText().equals(" ")) {
                        button.setText(String.valueOf(currentPlayer[0]));

                        if (checkWinner(buttons, currentPlayer[0])) {
                            showWinner(currentPlayer[0]);
                            disableBoard(buttons);
                            return;
                        }

                        if (isBoardFull(buttons)) {
                            showDraw();
                            disableBoard(buttons);
                            return;
                        }

                        if (currentPlayer[0] == 'X') {
                            currentPlayer[0] = 'O';
                        } else {
                            currentPlayer[0] = 'X';
                        }
                    }
                });

                grid.add(button, col, row);
            }
        }

        Button resetButton = new Button("Neues Spiel");
        resetButton.setOnAction(e -> resetBoard(buttons, currentPlayer));

        VBox root = new VBox(10);
        root.getChildren().addAll(grid, resetButton);

        Scene scene = new Scene(root, 300, 350);

        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static boolean checkWinner(Button[][] buttons, char player) {
        String symbol = String.valueOf(player);

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(symbol)
                    && buttons[i][1].getText().equals(symbol)
                    && buttons[i][2].getText().equals(symbol)) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(symbol)
                    && buttons[1][i].getText().equals(symbol)
                    && buttons[2][i].getText().equals(symbol)) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(symbol)
                && buttons[1][1].getText().equals(symbol)
                && buttons[2][2].getText().equals(symbol)) {
            return true;
        }

        return buttons[0][2].getText().equals(symbol)
                && buttons[1][1].getText().equals(symbol)
                && buttons[2][0].getText().equals(symbol);
    }

    public static void showWinner(char player) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spiel vorbei");
        alert.setHeaderText(null);
        alert.setContentText("Spieler " + player + " hat gewonnen!");
        alert.showAndWait();
    }

    public static void showDraw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spiel vorbei");
        alert.setHeaderText(null);
        alert.setContentText("Unentschieden!");
        alert.showAndWait();
    }

    public static void disableBoard(Button[][] buttons) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    public static void resetBoard(Button[][] buttons, char[] currentPlayer) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
                buttons[row][col].setDisable(false);
            }
        }
        currentPlayer[0] = 'X';
    }

    public static boolean isBoardFull(Button[][] buttons) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch();
    }
}